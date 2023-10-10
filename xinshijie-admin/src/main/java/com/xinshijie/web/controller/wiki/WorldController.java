package com.xinshijie.web.controller.wiki;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.enums.StatusEnums;
import com.xinshijie.common.enums.TypeEnums;
import com.xinshijie.common.enums.WorldStatusEnums;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.dto.WorldFindDto;
import com.xinshijie.wiki.service.IWorldService;
import com.xinshijie.wiki.vo.WorldVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 世界Controller
 *
 * @author xinshijie
 * @date 2022-07-25
 */
@Slf4j
@RestController
@Tag(name = "WorldController", description = "世界")
@RequestMapping("/wiki/world")
public class WorldController extends BaseController {
    @Autowired
    private IWorldService worldService;

    /**
     * 查询世界列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询世界列表", description = "查询世界列表")
    public TableDataInfo<List<WorldVo>> list(WorldFindDto dto) {
        startPage();
        dto.setStatus(WorldStatusEnums.NORMAL.getCode());
        dto.setIsPrivate(StatusEnums.NO.getCode());
        List<WorldVo> list = worldService.selectWorldList(dto);
        return getDataTable(list);
    }


    /**
     * 获取世界详细信息
     */
    @Operation(summary = "获取世界详细信息", description = "获取世界详细信息")
    @GetMapping(value = "/getInfo/{id}")
    public Result<WorldVo> getInfo(@PathVariable("id") Integer id) {
        WorldVo world = worldService.getInfo(id);
        if (world == null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST );
        }
        if (world.getStatus() != WorldStatusEnums.NORMAL.getCode()) {
            throw new ServiceException(ResultCodeEnum.ABNORMAL_STATUS);
        }
        if (world.getIsPrivate() != StatusEnums.NO.getCode()) {
            throw new ServiceException(ResultCodeEnum.WORLD_IS_PRIVE);
        }
        world.setTypeName(TypeEnums.getNameByCode(world.getTypes()));

        return Result.success(world);
    }

    @Operation(summary = "getType", description = "getType")
    @GetMapping(value = "/getType")
    public Result<TypeEnums[]> getType() {
        TypeEnums[] arr = TypeEnums.values();
        return Result.success(arr);
    }


}
