package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.domain.RecommendWorld;
import com.xinshijie.wiki.dto.RecommendWorldAddDto;
import com.xinshijie.wiki.mapper.RecommendWorldMapper;
import com.xinshijie.wiki.service.IRecommendWorldService;
import com.xinshijie.wiki.dto.RecommendWorldDto;
import com.xinshijie.wiki.service.IWorldService;
import com.xinshijie.wiki.vo.RecommendWorldVo;
import com.xinshijie.wiki.vo.WorldVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 推荐的世界，首页使用  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class RecommendWorldServiceImpl extends ServiceImpl<RecommendWorldMapper, RecommendWorld> implements IRecommendWorldService {

    @Autowired
    private RecommendWorldMapper mapper;

    @Autowired
    private IWorldService worldService;

    /**
     * 查询图片信息表
     */
    @Override
    public List<RecommendWorldVo> selectRecommendWorldList(RecommendWorldDto dto) {
        return mapper.selectListRecommendWorld(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<RecommendWorldVo> selectPageRecommendWorld(RecommendWorldDto dto) {
        Page<RecommendWorldVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        return mapper.selectPageRecommendWorld(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<RecommendWorldVo> getPageRecommendWorld(RecommendWorldDto dto) {
        Page<RecommendWorldVo> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        QueryWrapper<RecommendWorldVo> qw = new QueryWrapper<>();
        return mapper.getPageRecommendWorld(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public RecommendWorld add(RecommendWorldAddDto dto) {
        RecommendWorld value = new RecommendWorld();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);

        WorldVo worldVo = worldService.getInfo(dto.getWid());
        if(worldVo == null){
            throw new ServiceException(ResultCodeEnum.THE_WORLD_DOES_NOT_EXIST);
        }
        RecommendWorldVo vo= mapper.slectWidRecType(dto.getWid(),dto.getRecType());
        if(vo != null){
            throw new ServiceException(ResultCodeEnum.DATA_IS_WRONG);
        }
        value.setWname(worldVo.getName());
        value.setIntro(worldVo.getIntro());
        value.setWtype(worldVo.getTypeName());
        value.setImgUrl(worldVo.getImgUrl());
        value.setWtag(worldVo.getTags());
        if ("原创".equals(worldVo.getSource())) {
            value.setIsOriginal(1);
        }else {
            value.setIsOriginal(2);
        }
//        value.setWtag();
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(RecommendWorldDto dto) {
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
    public RecommendWorldVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

}
