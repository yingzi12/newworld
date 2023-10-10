package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.DateUtils;
import com.xinshijie.wiki.domain.Category;
import com.xinshijie.wiki.dto.CategoryDto;
import com.xinshijie.wiki.dto.CategoryFindDto;
import com.xinshijie.wiki.mapper.CategoryMapper;
import com.xinshijie.wiki.service.ICategoryService;
import com.xinshijie.wiki.vo.CategorySimplyVo;
import com.xinshijie.wiki.vo.CategoryTreeVo;
import com.xinshijie.wiki.vo.CategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 元素分类 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //最大可建多少个分类
    @Value("${category.max.count:9999}")
    private Integer maxCount;

    //最大可建多少级
    @Value("${category.max.tier:6}")
    private Integer maxTier;

    //可建最大子类
    @Value("${category.max.child:18}")
    private Integer maxChild;

    /**
     * 查询元素分类
     */
    @Override
    public List<CategoryVo> selectCategoryList(CategoryFindDto dto) {
        return categoryMapper.selectCategoryList(dto);
    }

    @Override
    public List<CategorySimplyVo> selectSinplyCategoryList(Integer wid) {
        return categoryMapper.selectSinplyCategoryList(wid);
    }

    /**
     * 分页查询元素分类
     */
    @Override
    public Page<CategoryVo> selectPageCategory(CategoryFindDto dto) {
        Page<CategoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return categoryMapper.selectPageCategory(page, dto);
    }

    /**
     * 分页查询元素分类
     */
    @Override
    public Page<CategoryVo> getPageCategory(CategoryFindDto dto) {
        Page<CategoryVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<CategoryVo> qw = new QueryWrapper<>();
        return categoryMapper.getPageCategory(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Category add(CategoryDto dto) {
        Category value = new Category();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        categoryMapper.insert(value);
        return value;

    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(CategoryDto dto) {
        return categoryMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return categoryMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public CategoryVo getInfo(Long id) {
        return categoryMapper.getInfo(id);
    }

    @Override
    public List<CategoryTreeVo> selectCategoryByWid(Integer wid) {
        List<CategoryTreeVo> categoryList = categoryMapper.selectByWid(wid);
        return categoryList;
    }

    @Override
    public List<CategoryTreeVo> selectByWidTier(Integer wid, Integer tier) {
        List<CategoryTreeVo> categoryList = categoryMapper.selectByWidTier(wid, tier);
        return categoryList;
    }

    @Override
    public Category insertCategory(Category category) {
        //判断是否存在同名分类
        //判断code是否已经存在
        category.setCreateTime(DateUtils.getNowDate());
        Integer count = categoryMapper.countByWidLabel(category.getWid(), category.getPid(), category.getLabel());
        if (count > 0) {
            throw new ServiceException(ResultCodeEnum.CATEGORY_ALREADY_EXISTS_WITH_THIS_NAME );
        }
        Integer wCount = categoryMapper.countByWidLabel(category.getWid(), null, null);
        if (maxCount > wCount) {
            throw new ServiceException(ResultCodeEnum.EXCEED_MAX_BUILDABLE_CLASSIFICATION_COUNT);
        }
        Integer tierCount = categoryMapper.countByWidLabel(category.getWid(), category.getPid(), null);
        if (maxChild > tierCount) {
            throw new ServiceException(ResultCodeEnum.EXCEED_CURRENT_LEVEL_MAX_CLASSIFICATION_COUNT);
        }
        if (category.getPid() != 0) {
            //查询上级分类
            CategoryVo pCategoryVo = categoryMapper.getInfo(category.getPid());
            if (pCategoryVo == null) {
                throw new ServiceException(ResultCodeEnum.PARENT_CATEGORY_DOES_NOT_EXIST);
            }
            if (maxTier >= pCategoryVo.getTier()) {
                throw new ServiceException(ResultCodeEnum.EXCEED_MAX_CLASSIFICATION_LEVEL);
            }
            category.setPcode(pCategoryVo.getPcode());
            category.setPidpath(pCategoryVo.getPidpath() + "," + category.getId());
            category.setTier(pCategoryVo.getTier() + 1);
        } else {
            category.setPcode("");
            category.setPidpath(String.valueOf(category.getId()));
            category.setTier(0);
        }
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public Long countCategory(Integer wid) {
        QueryWrapper<Category> qwc = new QueryWrapper<>();
        qwc.eq("wid", wid);
        return categoryMapper.selectCount(qwc);
    }

    @Override
    public List<String> findAllNameList(List<Long> ids) {
        List<String> pidpathList = categoryMapper.findPidpathByIds(ids);
        Set<Long> idSet = new HashSet<>();
        for (String str : pidpathList) {
            String[] arr = str.split(",");
            for (String pid : arr) {
                idSet.add(Long.getLong(pid));
            }
        }
        return findNameAllByIds(idSet);
    }

    @Override
    public List<String> findNameAllByIds(Set<Long> ids) {
//        List<Long> arr=new ArrayList<>(ids);
        return categoryMapper.findNameAllByIds(ids);
    }

    @Override
    public List<String> findPidpathByIds(List<Long> ids) {
        return categoryMapper.findPidpathByIds(ids);
    }

    //删除


    //移动

}
