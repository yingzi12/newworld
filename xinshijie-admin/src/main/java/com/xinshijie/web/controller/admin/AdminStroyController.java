package com.xinshijie.web.controller.admin;

import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.config.XinshijieConfig;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.enums.TypeEnums;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.common.utils.file.AliOSS;
import com.xinshijie.common.utils.file.FileUploadUtils;
import com.xinshijie.common.utils.file.FileUtils;
import com.xinshijie.framework.config.ServerConfig;
import com.xinshijie.wiki.domain.Story;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.IImageService;
import com.xinshijie.wiki.service.IStoryService;
import com.xinshijie.wiki.vo.AuthorVo;
import com.xinshijie.wiki.vo.ImageVo;
import com.xinshijie.wiki.vo.ResuImageVo;
import com.xinshijie.wiki.vo.StoryVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 小说Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Slf4j
@RestController
@Tag(name = "AdminStroyController", description = "后台-故事")
@RequestMapping("/admin/story")
public class AdminStroyController extends BaseController {
    @Autowired
    private IStoryService storyService;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ServerConfig serverConfig;

    /**
     * 查询小说列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<StoryVo>> list(StoryFindDto dto) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        dto.setUserId(userid);
        startPage();
        List<StoryVo> list = storyService.findByAuthor(dto);
        return getDataTable(list);
    }

    /**
     * 查询小说列表
     */
    @GetMapping("/listAdmin")
    public TableDataInfo<List<StoryVo>> listAdmin(StoryFindDto dto) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        dto.setIsAdmin(1);
        dto.setUserId(userid);
        startPage();
        List<StoryVo> list = storyService.selectStoryList(dto);
        return getDataTable(list);
    }

    /**
     * 新增小说
     */
    @Log(title = "小说", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<Story> add(@RequestBody StoryAddDto addDto) {
        return Result.success(storyService.add(addDto));
    }

    /**
     * 修改小说
     */
    @Log(title = "小说", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Result<String> edit(@RequestBody StoryAddDto addDto) {
        StoryDto story = new StoryDto();
        BeanUtils.copyProperties(addDto, story);
        return toAjax(storyService.edit(story));
    }

    /**
     * 删除小说
     */
//    @StoryVelidate
    @Log(title = "小说", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{sid}")
    public Result<String> remove(@PathVariable Long sid) {
        return toAjax(storyService.deleteStoryById(sid));
    }


    @Log(title = "发布小说", businessType = BusinessType.UPDATE)
    @GetMapping("/issue")
    public Result<String> issue(@RequestParam("sid") Long sid) {
        return toAjax(storyService.issue(sid));
    }

    @Log(title = "审核故事", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public Result<String> audit(@RequestBody AuditDto dto) {
        return toAjax(storyService.audit(dto));
    }

    /**
     * 获取小说详细信息
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result<StoryVo> getInfo(@PathVariable("id") Long id) {
        StoryVo story = storyService.getInfo(id);
        if (story.getCreateId() != getUserId()) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        story.setTypeName(TypeEnums.getNameByCode(story.getTypes()));
        return Result.success(story);
    }

    /**
     * 获取小说详细信息
     */
    @GetMapping(value = "/getInfoAdmin/{id}")
    public Result<StoryVo> getInfoAdmin(@PathVariable("id") Long id) {
        AuthorVo authorVo= authorService.isCheck(id);
        StoryVo story = storyService.getInfo(id);
        story.setAuthorType(authorVo.getTypes());
        story.setTypeName(TypeEnums.getNameByCode(story.getTypes()));
        return Result.success(story);
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
}
