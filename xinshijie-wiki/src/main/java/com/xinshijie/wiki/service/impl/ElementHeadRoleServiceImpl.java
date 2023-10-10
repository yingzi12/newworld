package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementHeadRole;
import com.xinshijie.wiki.mapper.ElementHeadRoleMapper;
import com.xinshijie.wiki.service.IElementHeadRoleService;
import com.xinshijie.wiki.dto.ElementHeadRoleDto;
import com.xinshijie.wiki.vo.ElementHeadForcesVo;
import com.xinshijie.wiki.vo.ElementHeadRoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色元素头,不同的元素模板对应不同的head  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ElementHeadRoleServiceImpl extends ServiceImpl<ElementHeadRoleMapper, ElementHeadRole> implements IElementHeadRoleService {

    @Autowired
    private ElementHeadRoleMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ElementHeadRoleVo> selectElementHeadRoleList(ElementHeadRoleDto dto) {
        return mapper.selectListElementHeadRole(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadRoleVo> selectPageElementHeadRole(ElementHeadRoleDto dto) {
        Page<ElementHeadRoleVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return mapper.selectPageElementHeadRole(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementHeadRoleVo> getPageElementHeadRole(ElementHeadRoleDto dto) {
        Page<ElementHeadRoleVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementHeadRoleVo> qw = new QueryWrapper<>();
        return mapper.getPageElementHeadRole(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementHeadRole add(ElementHeadRoleDto dto) {
        ElementHeadRole value = new ElementHeadRole();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementHeadRoleDto dto) {
        return mapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return mapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public ElementHeadRoleVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWidEid(Long id, Integer wid, Long eid) {
        return mapper.delByIdWidEid(id,wid,eid);
    }

    @Override
    public ElementHeadRoleVo selectByWidEid(Integer wid, Long eid) {
        return mapper.selectByWidEid(wid,eid);
    }

    @Override
    public int insertSelect(Integer wid, Long deid) {
        return mapper.insertSelect(wid,deid);
    }
}
