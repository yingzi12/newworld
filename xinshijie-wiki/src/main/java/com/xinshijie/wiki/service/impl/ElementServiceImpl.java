package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xinshijie.common.enums.ContentStatusEnums;
import com.xinshijie.common.enums.ElementStatusEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.DateUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.*;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.mapper.ElementMapper;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.xinshijie.common.utils.PageUtils.startPage;

/**
 * <p>
 * 元素 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class ElementServiceImpl extends ServiceImpl<ElementMapper, Element> implements IElementService {

    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private IElementCategoryService elementCategoryService;
    @Autowired
    private IElementContentService elementContentService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IWorldService worldService;
    @Autowired
    private IManageService manageService;

    @Autowired
    private IDraftElementService draftElementService;
    @Autowired
    private IRedidentService redidentService;

    /**
     * 查询元素
     */
    @Override
    public List<ElementVo> selectElementList(ElementFindDto dto) {
        if (dto.getTypes() != null) {
            PageHelper.clearPage();
            Category categoryVo = categoryService.getById(dto.getTypes());
            dto.setTypeCode(categoryVo.getCode());
        }
        startPage();
        return elementMapper.selectElementList(dto);
    }

    /**
     * 分页查询元素
     */
    @Override
    public Page<ElementVo> selectPageElement(ElementFindDto dto) {
        Page<ElementVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return elementMapper.selectPageElement(page, dto);
    }

    @Override
    public ElementVo selectElement(Integer wid, Long eid) {
        ElementVo element = elementMapper.selectByIdWid(wid, eid);
        if (element == null) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        List<ElementContentVo> contentVoList = elementContentService.selectByWidEid(wid, eid);
        List<CategoryVo> categoryVoList = elementCategoryService.selectCategoryLab(wid, eid);
        Set<Long> cidList = new HashSet<>();
        for (CategoryVo categoryVo : categoryVoList) {
            String[] arr = categoryVo.getPidpath().split(",");
            for (String pid : arr) {
                cidList.add(Long.parseLong(pid));
            }
        }
        List<String> nameList = categoryService.findNameAllByIds(cidList);
        element.setCnameList(nameList);
        element.setContentList(contentVoList);
        element.setCategoryList(categoryVoList);
        return element;
    }

    @Override
    public ElementVo getSingle(Integer wid, Long eid) {
        ElementVo element = elementMapper.selectByIdWid(wid, eid);
        if (element == null) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        return element;
    }

    @Override
    public int insertBatch(List<ElementDto> elements) {
        return elementMapper.insertBatch(elements);
    }


    @Override
    public Integer see(Long id, Long userid) {
        ElementDto dto = new ElementDto();
        dto.setId(id);
        dto.setCountSee(1);
        return elementMapper.updateCount(dto);
    }

    @Override
    public Integer edit(Long id, Long userid) {
        ElementDto dto = new ElementDto();
        dto.setId(id);
        dto.setCountEdit(1);
        return elementMapper.updateCount(dto);
    }

    @Override
    public List<ElementVo> selectElementByCidName(List<Long> ids, String title) {
        return elementMapper.selectElementByCidName(ids, title);
    }

    @Override
    public Integer unlock(Integer wid, Long eid) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        ElementDto elemenDto = new ElementDto();
        elemenDto.setId(eid);
        elemenDto.setWid(wid);
        elemenDto.setStatus(ElementStatusEnums.NORMAL.getCode());
        elemenDto.setUpdateId(userid);
        elemenDto.setUpdateName(username);
        elemenDto.setUpdateTime(DateUtils.getNowDate());
        return elementMapper.updateStatus(elemenDto);
    }

    @Override
    public Integer lock(Integer wid, Long eid) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        ElementDto elemenDto = new ElementDto();
        elemenDto.setId(eid);
        elemenDto.setWid(wid);
        elemenDto.setStatus(ElementStatusEnums.LOCK.getCode());
        elemenDto.setUpdateId(userid);
        elemenDto.setUpdateName(username);
        elemenDto.setUpdateTime(DateUtils.getNowDate());
        return elementMapper.updateStatus(elemenDto);
    }

    @Override
    public ElementVo previousElement(Integer wid, Long eid) {
        return elementMapper.previousElement(wid, eid);
    }

    @Override
    public ElementVo nextElement(Integer wid, Long eid) {
        return elementMapper.nextElement(wid, eid);
    }

    /**
     * 分页查询元素
     */
    @Override
    public Page<ElementVo> getPageElement(ElementFindDto dto) {
        Page<ElementVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementVo> qw = new QueryWrapper<>();
        return elementMapper.getPageElement(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Element add(ElementDto dto) {
        Element value = new Element();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        elementMapper.insert(value);
        return value;
    }

    @Override
    public Integer edit(ElementDto dto) {
        return elementMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return elementMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public ElementVo getInfo(Long id) {
        return elementMapper.getInfo(id);
    }

    @Override
    public SimpleElementVo insertElement(ElementAddDto addDto) { // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        WorldVo worldVo = worldService.getInfo(addDto.getWid());
        if (worldVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST );
        }
//        ManageVo manage = manageService.selectByWidUserId(addDto.getWid(),userid);
        SimpleElementVo vo = new SimpleElementVo();
        vo.setWid(addDto.getWid());
        vo.setWname(worldVo.getName());
        addDto.setUserId(userid);
        addDto.setWname(worldVo.getName());
        DraftElement element = draftElementService.add(addDto);
        vo.setTypes(1);
        vo.setId(element.getId());

        redidentService.add(addDto.getWid());

//        countService.updateCount(dto.getWid(),userid, UserOptionEnums.ADDDISSCUS.getCode());
        return vo;
    }

    public Element addElementDto(ElementAddDto addDto) {
        WorldVo world = worldService.getInfo(addDto.getWid());
        if (world == null) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (addDto.getCategoryList() == null || addDto.getCategoryList().size() == 0) {
            throw new ServiceException( ResultCodeEnum.CATEGORY_MUST_BE_SELECTED);
        }
        if (addDto.getContentList() == null || addDto.getContentList().size() == 0) {
            throw new ServiceException(ResultCodeEnum.MUST_HAVE_CONTENT);
        }
        // 获取当前用户名
        String username = SecurityUtils.getUsername();

        Element element = new Element();
        element.setIntro(addDto.getIntro());
        element.setTitle(addDto.getTitle());
        element.setStatus(ElementStatusEnums.NORMAL.getCode());
        element.setVersion(0);
        element.setPageHtml(addDto.getPageHtml());
        element.setTags(addDto.getTags());
        element.setWid(addDto.getWid());
        element.setWname(addDto.getWname());
        element.setSofttype(addDto.getSofttype());
        element.setCreateName(username);
        element.setCreateId(addDto.getUserId());
        element.setUpdateId(addDto.getUserId());
        element.setUpdateName(username);
        element.setCreateTime(LocalDateTime.now());
        element.setUpdateTime(LocalDateTime.now());
        elementMapper.insert(element);
        List<ElementCategoryDto> categoryList = new ArrayList<>();
        for (Long cid : addDto.getCategoryList()) {
            ElementCategoryDto category = new ElementCategoryDto();
            category.setWid(addDto.getWid());
            category.setEid(element.getId());
            category.setCid(cid);
            category.setCreateName(username);
            category.setCreateId(addDto.getUserId());
            category.setUpdateId(addDto.getUserId());
            category.setUpdateName(username);
            category.setCreateTime(LocalDateTime.now());
            category.setUpdateTime(LocalDateTime.now());

            categoryList.add(category);
        }
        elementCategoryService.insetList(categoryList);

        List<ElementContentDto> contentList = new ArrayList<>();
        for (ElementContentDto dto : addDto.getContentList()) {
            ElementContentDto content = new ElementContentDto();
            content.setWid(addDto.getWid());
            content.setEid(element.getId());
            content.setContent(dto.getContent());
            content.setTitle(dto.getTitle());
            content.setStatus(ContentStatusEnums.NORMAL.getCode());
            content.setCreateName(username);
            content.setCreateId(addDto.getUserId());
            content.setUpdateId(addDto.getUserId());
            content.setUpdateName(username);
            content.setCreateTime(LocalDateTime.now());
            content.setUpdateTime(LocalDateTime.now());
            contentList.add(content);
        }
        elementContentService.insetList(contentList);
        return element;
    }

    /**
     * 修改元素
     *
     * @param dto 元素
     * @return 结果
     */
    @Override
    public SimpleElementVo updateElement(ElementUpdateDto dto) {
//        String lockKey = ElementConstant.LOCK + dto.getId();
//        if (redisCache.hasKey(lockKey)) {
//            throw new ServiceException("元素已被锁定，正在修改中");
//        }else {
//            redisCache.setCacheObject(lockKey, 1, Constants.ELEMENT_LOCK_EXPIRATION, TimeUnit.HOURS);
//        }
//        String captcha = redisCache.getCacheObject(lockKey);
        WorldVo world = worldService.getInfo(dto.getWid());
        if (world == null) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        SimpleElementVo vo = new SimpleElementVo();
        vo.setWid(dto.getWid());
        dto.setUserId(userid);
        Boolean ok = draftElementService.isEdit(dto.getWid(), dto.getId());
        if (!ok) {
            throw new ServiceException(ResultCodeEnum.THERE_ARE_UNHANDLED_MODIFICATIONS_TO_THIS_ELEMENT);
        }
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        ElementDto elemenDto = new ElementDto();
        elemenDto.setWname(world.getName());
        elemenDto.setId(dto.getId());
        elemenDto.setWid(dto.getWid());
        elemenDto.setStatus(ElementStatusEnums.LOCK.getCode());
        elemenDto.setUpdateId(userid);
        elemenDto.setUpdateName(username);
        elemenDto.setUpdateTime(DateUtils.getNowDate());
        elementMapper.updateStatus(elemenDto);


        redidentService.add(dto.getWid());

        DraftElement element = draftElementService.addEdit(dto);
        vo.setTypes(1);
        vo.setId(element.getId());
        return vo;
    }

    /**
     * 修改元素
     *
     * @param dto 元素
     * @return 结果
     */
    public int updateElementDto(ElementUpdateDto dto) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
//        isCheck(dto.getId(),userid);
//        manageService.isCheck(userid,dto.getWid());

        Element element = new Element();
        BeanUtils.copyProperties(dto, element);

        element.setUpdateId(userid);
        element.setUpdateName(username);
        element.setUpdateTime(LocalDateTime.now());

        QueryWrapper<ElementCategory> ecQu = new QueryWrapper<>();
        ecQu.ge("wid", element.getWid());
        elementCategoryService.remove(ecQu);
        List<ElementCategoryDto> categoryList = new ArrayList<>();
        for (Long cid : dto.getCategoryList()) {
            ElementCategoryDto category = new ElementCategoryDto();
            category.setWid(dto.getWid());
            category.setEid(element.getId());
            category.setCid(cid);
            category.setCreateName(username);
            category.setCreateId(userid);
            category.setUpdateId(userid);
            category.setUpdateName(username);
            category.setCreateTime(LocalDateTime.now());
            category.setUpdateTime(LocalDateTime.now());

            categoryList.add(category);
        }
        if (categoryList.size() > 0) {
            elementCategoryService.insetList(categoryList);
        }

        List<ElementContentDto> contentAddList = new ArrayList<>();

        for (ElementContentUpdateDto c : dto.getContentList()) {
            ElementContentDto content = new ElementContentDto();
            content.setWid(dto.getWid());
            content.setEid(element.getId());
            content.setContent(c.getContent());
            content.setTitle(c.getTitle());
            content.setStatus(ContentStatusEnums.NORMAL.getCode());
            content.setUpdateId(userid);
            content.setUpdateName(username);
            content.setUpdateTime(LocalDateTime.now());
            if (c.getId() != null && c.getIsUpdate() == 1) {
                content.setId(c.getId());
                elementContentService.updateByIdWidEid(content);
            } else {
                if (c.getId() == null && !Strings.isEmpty(c.getContent())) {
                    content.setCreateName(username);
                    content.setCreateId(userid);
                    content.setUpdateId(userid);
                    content.setUpdateName(username);
                    content.setCreateTime(LocalDateTime.now());
                    content.setUpdateTime(LocalDateTime.now());
                    contentAddList.add(content);
                }
            }
        }
        if (contentAddList.size() > 0) {
            elementContentService.insetList(contentAddList);
        }
        if (dto.getContentIdList() != null && dto.getContentIdList().size() > 0) {
            elementContentService.removeByIds(dto.getContentIdList());
        }
        QueryWrapper<Element> eQW = new QueryWrapper<>();
        eQW.eq("id", dto.getId());
        eQW.eq("wid", dto.getWid());
        elementMapper.update(element, eQW);
        return elementMapper.update(element, eQW);
    }

    @Override
    public int updateStatus(ElementUpdateDto updateDto) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        manageService.isCheck(updateDto.getWid());
        ElementDto elemenDto = new ElementDto();
        elemenDto.setId(updateDto.getId());
        elemenDto.setWid(updateDto.getWid());
        elemenDto.setStatus(updateDto.getStatus());
        elemenDto.setUpdateId(userid);
        elemenDto.setUpdateName(username);
        elemenDto.setUpdateTime(DateUtils.getNowDate());
        return elementMapper.updateStatus(elemenDto);
    }


    /**
     * 删除元素信息
     *
     * @param wid 元素主键
     * @return 结果
     */
    @Override
    public int deleteElementByIdWid(Integer wid, Long eid) {

        Long userid = SecurityUtils.getUserId();
        manageService.isCheck(wid);
        QueryWrapper<ElementCategory> qwEC = new QueryWrapper<>();
        qwEC.eq("wid", wid);
        qwEC.eq("eid", eid);
        elementCategoryService.remove(qwEC);

        QueryWrapper<ElementContent> qwECon = new QueryWrapper<>();
        qwECon.eq("wid", wid);
        qwECon.eq("eid", eid);
        elementContentService.remove(qwECon);

        return elementMapper.deleteElementByIdWid(wid, eid);
    }

    public void isCheck(Long id, Long userId) {
        Element el = elementMapper.selectById(id);
        if (el == null) {
            throw new ServiceException(ResultCodeEnum.THE_ELEMENT_DOES_NOT_EXIST);
        }
        if (userId.equals(el.getCreateId())) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
    }
}
