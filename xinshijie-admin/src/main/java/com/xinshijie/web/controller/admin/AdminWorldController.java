package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.config.XinshijieConfig;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.enums.TypeEnums;
import com.xinshijie.common.enums.WorldStatusEnums;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.common.utils.file.AliOSS;
import com.xinshijie.common.utils.file.FileUploadUtils;
import com.xinshijie.common.utils.file.FileUtils;
import com.xinshijie.framework.config.ServerConfig;
import com.xinshijie.wiki.domain.World;
import com.xinshijie.wiki.dto.ImageDto;
import com.xinshijie.wiki.dto.WorldAddDto;
import com.xinshijie.wiki.dto.WorldFindDto;
import com.xinshijie.wiki.service.IImageService;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.service.IWorldService;
import com.xinshijie.wiki.vo.ImageVo;
import com.xinshijie.wiki.vo.ManageVo;
import com.xinshijie.wiki.vo.ResuImageVo;
import com.xinshijie.wiki.vo.WorldVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 世界Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Slf4j
@RestController
@Tag(name = "AdminWorldController", description = "后台-世界")
@RequestMapping("/admin/world")
public class AdminWorldController extends BaseController {
    @Autowired
    private IWorldService worldService;

    @Autowired
    private IManageService manageService;

    @Autowired
    private IImageService imageService;
    @Autowired
    private ServerConfig serverConfig;

    /**
     * 查询世界列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<WorldVo>> list(WorldFindDto dto) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        dto.setUserId(userid);
        startPage();
        List<WorldVo> list = worldService.findByManage(dto);
        return getDataTable(list);
    }

    /**
     * 新增世界
     */
    @Log(title = "世界", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<World> add(@RequestBody WorldAddDto addDto) {
        World world = new World();
        BeanUtils.copyProperties(addDto, world);
        world.setStatus(WorldStatusEnums.DRAFT.getCode());
        return Result.success(worldService.insertWorld(world));
    }

    /**
     * 修改世界
     */
    @Log(title = "世界", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Result<String> edit(@RequestBody WorldAddDto addDto) {
        World world = new World();
        BeanUtils.copyProperties(addDto, world);
        return toAjax(worldService.updateWorld(world));
    }

    /**
     * 删除世界
     */
//    @WorldVelidate
    @Log(title = "世界", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{id}")
    public Result<String> remove(@PathVariable Integer id) {
        return toAjax(worldService.deleteWorldById(id));
    }

    /**
     * 头像
     */
    @PostMapping("/uploadImage")
    public ResuImageVo uploadEditFile(@RequestParam("upload") MultipartFile file) {
        try {
            //获取上传文件的MD5,如果文件已经存在,直接返回路径
            String md5 = DigestUtils.md5Hex(file.getInputStream());
            ImageVo imageVo = imageService.getInfoByMd5(md5);
            if (imageVo != null) {
                ResuImageVo ajax = new ResuImageVo();
                ajax.setId(imageVo.getId());
                ajax.setUrl(imageVo.getFileUrl() + imageVo.getFileName());
                ajax.setFileName(imageVo.getFileName());
                ajax.setUploaded(true);
                ajax.setOriginalFilename(file.getOriginalFilename());
                ajax.setNewFileName(FileUtils.getName(imageVo.getFileName()));
                return ajax;
            }
            // 上传文件路径
            String filePath = XinshijieConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.uploadImageOss(filePath, file);
            ImageDto image = new ImageDto();
            image.setFileName(fileName);
            image.setFileUrl(AliOSS.URL);
            image.setFileSize(file.getSize());
            image.setFileType(FileUploadUtils.getExtension(file));
            image.setMd5(md5);
            image.setOriginalFilename(file.getOriginalFilename());
            imageService.add(image);

            String url = serverConfig.getUrl() + fileName;
            System.out.println("打印配置文件上传路径：" + url);
            ResuImageVo ajax = new ResuImageVo();
            ajax.setId(image.getId());
            ajax.setUrl(image.getFileUrl() + image.getFileName());
            ajax.setFileName(fileName);
            ajax.setUploaded(true);
            ajax.setOriginalFilename(file.getOriginalFilename());
            ajax.setNewFileName(FileUtils.getName(fileName));
            return ajax;
        } catch (Exception e) {
            throw new ServiceException(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), e.getMessage());
        }
    }

    @Log(title = "发布世界", businessType = BusinessType.UPDATE)
    @GetMapping("/issue")
    public Result<String> issue(@RequestParam("wid") Integer wid) {
        return toAjax(worldService.issue(wid));
    }

    /**
     * 获取世界详细信息
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result<WorldVo> getInfo(@PathVariable("id") Integer id) {
        Long userid = SecurityUtils.getUserId();
        ManageVo manageVo = manageService.isCheck(id);
        WorldVo world = worldService.getInfo(id);
        world.setAdminType(manageVo.getTypes());
        world.setTypeName(TypeEnums.getNameByCode(world.getTypes()));

        return Result.success(world);
    }

    @GetMapping(value = "/getWorld")
    public Result<WorldVo> selectByName(@RequestParam("name") String name) {
        WorldVo world = worldService.selectByName(name);
        return Result.success(world);
    }

}
