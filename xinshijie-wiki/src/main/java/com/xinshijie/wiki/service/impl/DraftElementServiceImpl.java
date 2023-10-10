package com.xinshijie.wiki.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.constant.ElementConstant;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.enums.*;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.*;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.mapper.DraftElementMapper;
import com.xinshijie.wiki.service.*;
import com.xinshijie.wiki.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 元素草稿 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class DraftElementServiceImpl extends ServiceImpl<DraftElementMapper, DraftElement> implements IDraftElementService {

    @Autowired
    private DraftElementMapper draftelementMapper;
    @Autowired
    private IDraftCategoryService draftCategoryService;
    @Autowired
    private IDraftContentService draftContentService;
    @Autowired
    private IWorldService worldService;
    @Autowired
    private IManageService manageService;
    @Autowired
    private IElementService elementService;
    @Autowired
    private IElementCategoryService elementCategoryService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IElementContentService elementContentService;

    @Autowired
    private IOperationCountService countService;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IDraftHeadForcesService forcesService;
    @Autowired
    private IDraftHeadGeographyService geographyService;
    @Autowired
    private IDraftHeadOrganismService organismService;
    @Autowired
    private IDraftHeadRaceService raceService;
    @Autowired
    private IDraftHeadRoleService roleService;
    @Autowired
    private IDraftHeadThingService thingService;

    @Autowired
    private IRedidentService redidentService;

    /**
     * 查询元素草稿
     */
    @Override
    public List<DraftElementVo> selectDraftElementList(DraftElementFindDto dto) {
        return draftelementMapper.selectDraftElementList(dto);
    }

    /**
     * 分页查询元素草稿
     */
    @Override
    public Page<DraftElementVo> selectPageDraftElement(DraftElementDto dto) {
        Page<DraftElementVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return draftelementMapper.selectPageDraftElement(page, dto);
    }

    /**
     * 分页查询元素草稿
     */
    @Override
    public Page<DraftElementVo> getPageDraftElement(DraftElementDto dto) {
        Page<DraftElementVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<DraftElementVo> qw = new QueryWrapper<>();
        return draftelementMapper.getPageDraftElement(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public DraftElement add(ElementAddDto addDto) {
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
        if (addDto.getContentList().size() > 10) {
            throw new ServiceException(    ResultCodeEnum.MAXIMUM_ALLOWED_CONTENT_SECTIONS_EXCEEDED);
        }
        for (ElementContentDto content : addDto.getContentList()) {
            if (content.getContent().length() > 20000) {
                throw new ServiceException(ResultCodeEnum.MAXIMUM_ALLOWED_CONTENT_SECTIONS_EXCEEDED.getCode(),"标题《" + content.getTitle() + "》字数为" + content.getContent().length() + ",超出最大许可的内容许可字数");
            }
        }
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();

        DraftElement element = new DraftElement();
        element.setIntro(addDto.getIntro());
        element.setTitle(addDto.getTitle());
        element.setStatus(DraftStatusEnums.DRAFT.getCode());
        element.setVersion(0);
        element.setIsNew(1);
        element.setTags(addDto.getTags());
        element.setWid(addDto.getWid());
        element.setWname(world.getName());
        element.setSofttype(addDto.getSofttype());
        element.setExtra(addDto.getExtra());
        element.setCauseContent("新增");
        element.setCauseNumber(0);
        element.setCreateName(username);
        element.setCreateId(userid);
        element.setUpdateId(userid);
        element.setUpdateName(username);
        element.setCreateTime(LocalDateTime.now());
        element.setUpdateTime(LocalDateTime.now());
        element.setEndTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.DAYS));

        draftelementMapper.insert(element);
        addDraftElementHead(addDto);
        List<DraftCategoryDto> categoryList = new ArrayList<>();
        for (Long cid : addDto.getCategoryList()) {
            DraftCategoryDto category = new DraftCategoryDto();
            category.setWid(addDto.getWid());
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
        draftCategoryService.insetList(categoryList);

        List<DraftContentDto> contentList = new ArrayList<>();
        for (ElementContentDto dto : addDto.getContentList()) {
            DraftContentDto content = new DraftContentDto();
            content.setWid(addDto.getWid());
            content.setEid(element.getId());
            content.setContent(dto.getContent());
            content.setTitle(dto.getTitle());
            content.setStatus(dto.getStatus());
            content.setIsNew(1);
            content.setIsDelete(0);
            content.setIsUpdate(0);
            content.setCreateName(username);
            content.setCreateId(userid);
            content.setUpdateId(userid);
            content.setUpdateName(username);
            content.setCreateTime(LocalDateTime.now());
            content.setUpdateTime(LocalDateTime.now());
            contentList.add(content);
        }
        draftContentService.insetList(contentList);
        return element;
    }

    public void addDraftElementHead(ElementAddDto addDto){
        SofttypeEnums softtypeEnums=SofttypeEnums.getByCode(addDto.getSofttype());
        if(softtypeEnums==null){
            return ;
        }
        switch (softtypeEnums){
            case FORCES ->forcesService.add(addDto.getForces());
            case GEOGRAPHY ->geographyService.add(addDto.getGeography());
            case ORGANISM ->organismService.add(addDto.getOrganism());
            case RACE ->raceService.add(addDto.getRace());
            case ROLE ->roleService.add(addDto.getRole());
            case THING ->thingService.add(addDto.getThing());
            default ->{}
        }
    }
    /**
     * 新增元素的修改草稿记录
     */
    @Override
    public DraftElement addEdit(ElementUpdateDto dto) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        DraftElement draftElement = new DraftElement();
        draftElement.setWid(dto.getWid());
        draftElement.setWname(dto.getWname());
        draftElement.setTitle(dto.getTitle());
        draftElement.setIntro(dto.getIntro());
        draftElement.setSofttype(dto.getSofttype());
        draftElement.setExtra(dto.getExtra());
        draftElement.setSourceEid(dto.getId());
        draftElement.setStatus(DraftStatusEnums.DRAFT.getCode());
        draftElement.setIsNew(0);
        draftElement.setCreateId(userid);
        draftElement.setCreateName(username);
        draftElement.setCreateTime(LocalDateTime.now());
        draftElement.setUpdateId(userid);
        draftElement.setUpdateName(username);
        draftElement.setUpdateTime(LocalDateTime.now());
        draftElement.setCauseContent(dto.getCauseContent());
        draftElement.setCauseNumber(dto.getCauseNumber());
        draftElement.setEndTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.DAYS));
        draftelementMapper.insert(draftElement);
        editElementHeadAddDraft(dto);
        List<DraftCategoryDto> categoryList = new ArrayList<>();
        for (Long cid : dto.getCategoryList()) {
            DraftCategoryDto category = new DraftCategoryDto();
            category.setWid(dto.getWid());
            category.setEid(draftElement.getId());
            category.setCid(cid);
            category.setCreateName(username);
            category.setCreateId(userid);
            category.setUpdateId(userid);
            category.setUpdateName(username);
            category.setCreateTime(LocalDateTime.now());
            category.setUpdateTime(LocalDateTime.now());
            category.setSourceEid(dto.getId());
            categoryList.add(category);
        }
        if (categoryList.size() > 0) {
            draftCategoryService.insetList(categoryList);
        }

        List<DraftContentDto> contentAddList = new ArrayList<>();
        for (ElementContentUpdateDto c : dto.getContentList()) {
            DraftContentDto content = new DraftContentDto();
            content.setWid(dto.getWid());
            content.setEid(draftElement.getId());
            //原始信息
            content.setSourceEid(dto.getId());
            content.setSourceEcid(c.getId());
            content.setContent(c.getContent());
            content.setTitle(c.getTitle());
            content.setStatus(c.getStatus());
            content.setIsDelete(c.getIsDelete() == null ? 0 : c.getIsDelete());
            content.setIsNew(c.getIsNew() == null ? 0 : c.getIsNew());
            content.setIsUpdate(c.getIsUpdate() == null ? 0 : c.getIsUpdate());
            content.setUpdateId(userid);
            content.setUpdateName(username);
            content.setUpdateTime(LocalDateTime.now());
            content.setCreateName(username);
            content.setCreateId(userid);
            content.setCreateTime(LocalDateTime.now());
            if (c.getId() != null && c.getIsNew() != 1) {
                content.setIsNew(0);
            } else {
                content.setIsNew(1);
            }
            if (Strings.isNotEmpty(content.getContent())) {
                contentAddList.add(content);
            }
        }
        if (contentAddList.size() > 0) {
            draftContentService.insetList(contentAddList);
        }
        if (dto.getContentIdList() != null && dto.getContentIdList().size() > 0) {
            draftContentService.insetSelectContent(draftElement.getId(), dto.getWid(), dto.getId(), userid, username, dto.getContentIdList());
        }
        return draftElement;
    }
    //编辑element元素时是把编辑结果保存到Darft中
    public void editElementHeadAddDraft(ElementUpdateDto addDto){
        SofttypeEnums softtypeEnums=SofttypeEnums.getByCode(addDto.getSofttype());
        if(softtypeEnums==null){
            return ;
        }
        switch (softtypeEnums){
            case FORCES ->forcesService.add(addDto.getForces());
            case GEOGRAPHY ->geographyService.add(addDto.getGeography());
            case ORGANISM ->organismService.add(addDto.getOrganism());
            case RACE ->raceService.add(addDto.getRace());
            case ROLE ->roleService.add(addDto.getRole());
            case THING ->thingService.add(addDto.getThing());
            default ->{}
        }
    }


    @Override
    public int updateElementPageHtml(DraftElementUpdatePageHtmlDto dto) {
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        SimpleElementVo vo = new SimpleElementVo();
        vo.setWid(dto.getWid());
        dto.setUserId(userid);
        DraftElement element = new DraftElement();
        element.setId(dto.getId());
        element.setPageHtml(dto.getPageHtml());
        element.setStatus(DraftStatusEnums.SAVE.getCode());
        return draftelementMapper.updateById(element);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementUpdateDto dto) {
        if (dto.getContentList().size() > 10) {
            throw new ServiceException(ResultCodeEnum.MAXIMUM_ALLOWED_CONTENT_SECTIONS_EXCEEDED);
        }
        for (ElementContentUpdateDto content : dto.getContentList()) {
            if (content.getContent().length() > 20000) {
                throw new ServiceException(ResultCodeEnum.MAXIMUM_ALLOWED_CONTENT_SECTIONS_EXCEEDED.getCode(),"标题《" + content.getTitle() + "》字数为" + content.getContent().length() + "超出最大许可的内容许可字数");
            }
        }
        WorldVo worldVo = worldService.getInfo(dto.getWid());
        if (worldVo == null) {
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST );
        }
        DraftElementVo draftElement = draftelementMapper.getInfo(dto.getId());
        if (draftElement == null) {
            throw new ServiceException(ResultCodeEnum.DRAFT_DOES_NOT_EXIST);
        }
        if (!(draftElement.getStatus() == DraftStatusEnums.DRAFT.getCode() || draftElement.getStatus() == DraftStatusEnums.SAVE.getCode())) {
            throw new ServiceException(ResultCodeEnum.ABNORMAL_STATUS);
        }
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        DraftElement element = new DraftElement();
        element.setTitle(dto.getTitle());
        element.setIntro(dto.getIntro());
        element.setTags(dto.getTags());
        element.setStatus(DraftStatusEnums.DRAFT.getCode());
        element.setWname(worldVo.getName());
        element.setUpdateId(userid);
        element.setUpdateName(username);
        element.setUpdateTime(LocalDateTime.now());
        QueryWrapper<DraftElement> eQW = new QueryWrapper<>();
        eQW.eq("id", dto.getId());
        eQW.eq("wid", dto.getWid());
        eQW.eq("create_id", userid);
        int i = draftelementMapper.update(element, eQW);
        editDraftElementHead(dto);
        if (i == 0) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        QueryWrapper<DraftCategory> ecQu = new QueryWrapper<>();
        ecQu.ge("wid", dto.getWid());
        draftCategoryService.remove(ecQu);
        List<DraftCategoryDto> categoryList = new ArrayList<>();
        for (Long cid : dto.getCategoryList()) {
            DraftCategoryDto category = new DraftCategoryDto();
            category.setWid(dto.getWid());
            category.setEid(dto.getId());
            category.setCid(cid);
            category.setCreateName(username);
            category.setCreateId(userid);
            category.setUpdateId(userid);
            category.setUpdateName(username);
            category.setCreateTime(LocalDateTime.now());
            category.setUpdateTime(LocalDateTime.now());
            category.setSourceEid(dto.getSourceEid());
            categoryList.add(category);
        }
        if (categoryList.size() > 0) {
            draftCategoryService.insetList(categoryList);
        }

        List<DraftContentDto> contentAddList = new ArrayList<>();
        for (ElementContentUpdateDto c : dto.getContentList()) {
            DraftContentDto content = new DraftContentDto();
            content.setWid(dto.getWid());
            content.setEid(dto.getId());
            content.setContent(c.getContent());
            content.setTitle(c.getTitle());
            content.setStatus(c.getStatus());
            content.setIsDelete(c.getIsDelete() == null ? 0 : c.getIsDelete());
            content.setIsNew(c.getIsNew() == null ? 0 : c.getIsNew());
            content.setIsUpdate(c.getIsUpdate() == null ? 0 : c.getIsUpdate());
            content.setSourceEid(dto.getSourceEid());
            content.setUpdateId(userid);
            content.setUpdateName(username);
            content.setUpdateTime(LocalDateTime.now());
            if (c.getId() != null && ContentStatusEnums.EDIT.getCode().equals(c.getStatus())) {
                content.setId(c.getId());
                draftContentService.updateByIdWidEid(content);
            } else {
                if (c.getId() == null && !Strings.isEmpty(c.getContent())) {
                    content.setCreateName(username);
                    content.setCreateId(userid);
                    content.setCreateTime(LocalDateTime.now());
                    contentAddList.add(content);
                }
            }
        }
        if (contentAddList.size() > 0) {
            draftContentService.insetList(contentAddList);
        }
        if (dto.getContentIdList() != null && dto.getContentIdList().size() > 0) {
            DraftContent draftDto = new DraftContent();
            draftDto.setIsDelete(1);
            draftDto.setStatus(ContentStatusEnums.DEL.getCode());
            draftDto.setUpdateId(userid);
            draftDto.setUpdateName(username);
            draftDto.setUpdateTime(LocalDateTime.now());
            QueryWrapper<DraftContent> qw = new QueryWrapper<>();
            qw.eq("wid", dto.getWid());
            qw.in("id", dto.getContentIdList());
            qw.eq("eid", dto.getId());
            draftContentService.update(draftDto, qw);
        }
        return 1;
    }
    public void editDraftElementHead(ElementUpdateDto draftElement){
        SofttypeEnums softtypeEnums=SofttypeEnums.getByCode(draftElement.getSofttype());
        if(softtypeEnums==null){
            return ;
        }
        switch (softtypeEnums){
            case FORCES ->forcesService.edit(draftElement.getForces());
            case GEOGRAPHY ->geographyService.edit(draftElement.getGeography());
            case ORGANISM ->organismService.edit(draftElement.getOrganism());
            case RACE ->raceService.edit(draftElement.getRace());
            case ROLE ->roleService.edit(draftElement.getRole());
            case THING ->thingService.edit(draftElement.getThing());
            default ->{}
        }
    }

    /**
     * 判断元素是否允许修改
     */
    @Override
    public Boolean isEdit(Integer wid, Long eid) {

//        String lockKey = ElementConstant.LOCK + dto.getId();
//        if (redisCache.hasKey(lockKey)) {
//            throw new ServiceException("元素已被锁定，正在修改中");
//        }else {
//            redisCache.setCacheObject(lockKey, 1, Constants.ELEMENT_LOCK_EXPIRATION, TimeUnit.HOURS);
//        }
//        String captcha = redisCache.getCacheObject(lockKey);

        Long userid = SecurityUtils.getUserId();
//        ManageVo manage = manageService.selectByWidUserId(wid,userid);
        //如果不是管理员,需要判断是否已经存在了该元素的修改记录
//        if(manage ==null){
        QueryWrapper<DraftElement> qw = new QueryWrapper<>();
        qw.eq("wid", wid);
//            qw.eq("create_id",userid);
        qw.in("status", DraftStatusEnums.SAVE.getCode(), DraftStatusEnums.DRAFT.getCode(), DraftStatusEnums.ISSUE.getCode());
        qw.eq("source_eid", eid);
        Long count = draftelementMapper.selectCount(qw);
        return count <= 0;
//        }
    }

    //删除redis锁
    public void delLock(Integer wid, Long deid) {
        QueryWrapper<DraftElement> qw = new QueryWrapper<>();
        qw.eq("wid", wid);
        qw.eq("source_eid", deid);
        DraftElement draftElement = draftelementMapper.selectOne(qw);
        String lockKey = ElementConstant.LOCK + draftElement.getSourceEid();
        redisCache.deleteObject(lockKey);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Integer wid, Long deid) {
        Long userid = SecurityUtils.getUserId();
        int value = draftelementMapper.deleteByIdWidUserId(wid, deid, userid);
        if (value > 0) {
            draftCategoryService.deleteByEidWid(deid, wid);
            draftContentService.deleteByEidWid(deid, wid);
        }
        return value;
    }

    /**
     * 根据id数据
     */
    @Override
    public DraftElementVo getInfo(Integer wid, Long deid, Long userid, Integer isDel) {
        DraftElementVo element = draftelementMapper.selectByIdWidUserId(wid, deid, userid);
        if (element == null) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        List<DraftContentVo> contentVoList = draftContentService.selectByWidEid(wid, deid, isDel);
        List<CategoryVo> categoryVoList = draftCategoryService.selectCategoryLab(wid, deid);
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
    public int updateStatus(DraftElementUpdateDto draftDto) {
        Long userid = SecurityUtils.getUserId();
        String username = SecurityUtils.getUsername();
        draftDto.setEndTime(LocalDateTimeUtil.offset(LocalDateTime.now(), 1, ChronoUnit.DAYS));
        draftDto.setUpdateId(userid);
        draftDto.setUpdateName(username);
        draftDto.setUpdateTime(LocalDateTime.now());
        return draftelementMapper.updateStatus(draftDto);
    }

    @Override
    public int autorAuditYes(Integer wid, Long deid) {
        updateStorage(wid, deid);
        DraftElementUpdateDto draftDto=new DraftElementUpdateDto();
        draftDto.setWid(wid);
        draftDto.setId(deid);
        draftDto.setAuditNumber(0);
        draftDto.setAuditContent(DraftStatusEnums.AUTORAUDIT.getName());
        draftDto.setStatus(DraftStatusEnums.AUTORAUDIT.getCode());
        worldService.updateCountAuditElement(wid,-1);
        updateStatus(draftDto);
        return 0;
    }


    @Override
    public Integer audit(AuditDto dto) {
        Long userid = SecurityUtils.getUserId();
        //判断是否管理员
        manageService.isCheck(dto.getWid());
        DraftElementUpdateDto draftDto = new DraftElementUpdateDto();
        draftDto.setWid(dto.getWid());
        draftDto.setId(dto.getDeid());
        draftDto.setAuditNumber(dto.getAuditNumber());
        draftDto.setAuditContent(dto.getAuditContent());
        //计算审核
        countService.updateCount(dto.getWid(), userid, UserOptionEnums.AUDIT_ELEMENT);
        //1通过
        if (dto.getAuditStatus() == 1) {
            draftDto.setStatus(DraftStatusEnums.AUDITYES.getCode());
            //更新入库
            updateStorage(dto.getWid(), dto.getDeid());
        } else {
//            draftDto.setUserId(userid);
            //更新入库
            draftDto.setStatus(DraftStatusEnums.AUDITNO.getCode());
        }
        //修改审核状态
        updateStatus(draftDto);
        return 0;
    }

    @Override
    public SimpleElementVo issue(Integer wid, Long deid) {
        Long userid = SecurityUtils.getUserId();
        ManageVo manage = manageService.selectByWidUserId(wid, userid);
        DraftElementVo draftElementVo = draftelementMapper.getInfo(deid);
        if (!draftElementVo.getStatus().equals(DraftStatusEnums.SAVE.getCode())) {
            throw new ServiceException(ResultCodeEnum.THE_STATUS_IS_ABNORMAL_AND_MUST_BE_SAVED_BEFORE_PUBLISHING);
        }

        //非管理员 修改状态
        DraftElementUpdateDto draftDto = new DraftElementUpdateDto();
        draftDto.setWid(wid);
        draftDto.setId(deid);

        SimpleElementVo simpleElementVo = new SimpleElementVo();
        simpleElementVo.setWid(wid);
        //管理员直接发布审核成功
        if (manage != null) {
            simpleElementVo.setTypes(1);
            simpleElementVo.setId(updateStorage(wid, deid));
            draftDto.setStatus(DraftStatusEnums.AUDITYES.getCode());
            draftDto.setAuditNumber(99);
            draftDto.setAuditContent("管理员");
            updateStatus(draftDto);
        } else {
            simpleElementVo.setTypes(0);
            draftDto.setUserId(userid);
            draftDto.setStatus(DraftStatusEnums.ISSUE.getCode());
            updateStatus(draftDto);
            simpleElementVo.setId(draftDto.getId());
        }
        worldService.updateCountAuditElement(wid,1);
        return simpleElementVo;
    }

    @Override
    public int issueClose(Integer wid, Long deid) {
        Long userid = SecurityUtils.getUserId();
        DraftElementVo draftElementVo = draftelementMapper.getInfo(deid);
        //TODO　逻辑好像不对
        if (!draftElementVo.getStatus().equals(DraftStatusEnums.ISSUE.getCode())) {
            throw new ServiceException(ResultCodeEnum.ABNORMAL_STATUS);
        }
        //非管理员 修改状态
        DraftElementUpdateDto draftDto = new DraftElementUpdateDto();
        draftDto.setWid(wid);
        draftDto.setId(deid);
        draftDto.setUserId(userid);
        draftDto.setStatus(DraftStatusEnums.SAVE.getCode());
        worldService.updateCountAuditElement(wid,-1);
        return updateStatus(draftDto);
    }

    @Override
    public int autorUpdateIss() {
        Integer count=draftelementMapper.autorUpdateIss(DraftStatusEnums.DRAFT.getCode(), DraftStatusEnums.AUTORISSOR.getCode());
        return count;
    }

    @Override
    public List<DraftElementVo> findOvertimeAudit(Long size) {
        return draftelementMapper.findOvertimeAudit(size);
    }

    /**
     * 更新入库
     *
     * @return
     */
    public Long updateStorage(Integer wid, Long deid) {
        DraftElementVo draftVo = draftelementMapper.getInfo(deid);
        if (draftVo == null && !draftVo.getWid().equals(wid)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }

        if (draftVo.getIsNew() == 0) {
            return updateElement(draftVo);
        } else {
            return insetElement(draftVo);
        }
    }

    public Long insetElement(DraftElementVo draftVo) {
        //修改基础信息
        Element element = new Element();
        BeanUtils.copyProperties(draftVo, element);
        element.setId(null);
        element.setStatus(ElementStatusEnums.NORMAL.getCode());
        elementService.save(element);
        elementCategoryService.insertSelectEid(draftVo.getWid(), draftVo.getId(), element.getId());
        elementContentService.insertSelectEid(draftVo.getWid(), draftVo.getId(), element.getId());
        worldService.updateElement(draftVo.getWid(), draftVo.getTitle(), element.getId());
        // 获取当前用户ID
        Long userid = draftVo.getCreateId();
        countService.updateWorldCount(draftVo.getWid(), UserOptionEnums.ADD_ELEMENT);
        //当前元素
        countService.updateUserCount(element.getCreateId(), UserOptionEnums.ADD_ELEMENT);
        countService.updateRedidentCount(draftVo.getWid(), element.getCreateId(), UserOptionEnums.ADD_ELEMENT);
        countService.updateManageCount(draftVo.getWid(), element.getCreateId(), UserOptionEnums.ADD_ELEMENT);

        return element.getId();
    }

    public Long updateElement(DraftElementVo draftVo) {
        //修改基础信息
        ElementDto element = new ElementDto();
        element.setPageHtml(draftVo.getPageHtml());
        element.setSource(draftVo.getSource());
        element.setId(draftVo.getSourceEid());
        element.setUpdateId(draftVo.getCreateId());
        element.setUpdateName(draftVo.getCreateName());
        element.setUpdateTime(draftVo.getCreateTime());
        element.setTitle(draftVo.getTitle());
        element.setIntro(draftVo.getIntro());
        element.setImageUrls(draftVo.getImageUrls());
        element.setTags(draftVo.getTags());
        element.setStatus(ElementStatusEnums.NORMAL.getCode());
        elementService.edit(element);
        //修改分类信息
        //修改分类信息
        QueryWrapper<ElementCategory> qwEC = new QueryWrapper<>();
        qwEC.eq("wid", draftVo.getWid());
        qwEC.eq("eid", draftVo.getSourceEid());
        elementCategoryService.remove(qwEC);
        elementCategoryService.insertSelect(draftVo.getWid(), draftVo.getId());
        //新增的
        elementContentService.insertSelect(draftVo.getWid(), draftVo.getId());

        //删除或者是修改的
        QueryWrapper<DraftContent> qwDC = new QueryWrapper<>();
        qwDC.eq("wid", draftVo.getWid());
        qwDC.eq("eid", draftVo.getId());
        qwDC.eq("is_new", 0);
        List<DraftContent> draftContentList = draftContentService.list(qwDC);
        for (DraftContent draftContent : draftContentList) {
            if (draftContent.getIsDelete() == null) {
                draftContent.setIsDelete(0);
            }
            if (draftContent.getIsDelete() == 1) {
                elementContentService.delByIdWidEid(draftContent.getSourceEcid(), draftContent.getWid(), draftContent.getSourceEid());
            } else {
                ElementContentDto contentDto = new ElementContentDto();
                contentDto.setWid(draftContent.getWid());
                contentDto.setId(draftContent.getSourceEcid());
                contentDto.setEid(draftContent.getSourceEid());
                contentDto.setContent(draftContent.getContent());
                contentDto.setTitle(draftContent.getTitle());
                contentDto.setUpdateId(draftVo.getCreateId());
                contentDto.setUpdateName(draftVo.getCreateName());
                contentDto.setUpdateTime(draftVo.getCreateTime());
                if (ContentStatusEnums.NEW.getCode().equals(draftContent.getStatus()) || ContentStatusEnums.EDIT.getCode().equals(draftContent.getStatus())) {
                    contentDto.setStatus(ContentStatusEnums.NORMAL.getCode());
                } else {
                    contentDto.setStatus(draftContent.getStatus());
                }
                if (draftContent.getIsUpdate() == 1) {
                    elementContentService.updateByIdWidEid(contentDto);
                }
            }
        }

        // 获取修改者用户ID
        countService.updateWorldCount(draftVo.getWid(), UserOptionEnums.EDIT_ELEMENT);
        //当前元素
        countService.updateUserCount(element.getCreateId(), UserOptionEnums.EDIT_ELEMENT);
        countService.updateRedidentCount(draftVo.getWid(), draftVo.getCreateId(), UserOptionEnums.EDIT_ELEMENT);
        countService.updateManageCount(draftVo.getWid(), draftVo.getCreateId(), UserOptionEnums.EDIT_ELEMENT);

        return element.getId();
    }
}
