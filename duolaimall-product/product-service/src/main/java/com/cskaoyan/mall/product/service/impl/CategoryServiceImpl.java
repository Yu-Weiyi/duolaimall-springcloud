package com.cskaoyan.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cskaoyan.mall.product.converter.dto.CategoryConverter;
import com.cskaoyan.mall.product.dto.FirstLevelCategoryDTO;
import com.cskaoyan.mall.product.dto.SecondLevelCategoryDTO;
import com.cskaoyan.mall.product.dto.ThirdLevelCategoryDTO;
import com.cskaoyan.mall.product.mapper.FirstLevelCategoryMapper;
import com.cskaoyan.mall.product.mapper.SecondLevelCategoryMapper;
import com.cskaoyan.mall.product.mapper.ThirdLevelCategoryMapper;
import com.cskaoyan.mall.product.model.FirstLevelCategory;
import com.cskaoyan.mall.product.model.SecondLevelCategory;
import com.cskaoyan.mall.product.model.ThirdLevelCategory;
import com.cskaoyan.mall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.service.impl
 * @name CategoryServiceImpl
 * @description
 * @since 2024-09-20 19:15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    FirstLevelCategoryMapper firstLevelCategoryMapper;
    @Autowired
    SecondLevelCategoryMapper secondLevelCategoryMapper;
    @Autowired
    ThirdLevelCategoryMapper thirdLevelCategoryMapper;
    @Autowired
    CategoryConverter categoryConverter;

    /**
     * 查询所有的一级分类信息
     *
     * @return
     */
    @Override
    public List<FirstLevelCategoryDTO> getFirstLevelCategory() {

        List<FirstLevelCategory> firstLevelCategoryList = firstLevelCategoryMapper.selectList(null);

        return categoryConverter.firstLevelCategoryPOs2DTOs(firstLevelCategoryList);
    }

    /**
     * 根据一级分类Id 查询二级分类数据
     *
     * @param firstLevelCategoryId
     */
    @Override
    public List<SecondLevelCategoryDTO> getSecondLevelCategory(Long firstLevelCategoryId) {

        LambdaQueryWrapper<SecondLevelCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(SecondLevelCategory::getFirstLevelCategoryId, firstLevelCategoryId);

        List<SecondLevelCategory> secondLevelCategoryList = secondLevelCategoryMapper.selectList(lambdaQueryWrapper);

        return categoryConverter.secondLevelCategoryPOs2DTOs(secondLevelCategoryList);
    }

    /**
     * 根据二级分类Id 查询三级分类数据
     *
     * @param secondLevelCategoryId
     */
    @Override
    public List<ThirdLevelCategoryDTO> getThirdLevelCategory(Long secondLevelCategoryId) {

        LambdaQueryWrapper<ThirdLevelCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(ThirdLevelCategory::getSecondLevelCategoryId, secondLevelCategoryId);

        List<ThirdLevelCategory> thirdLevelCategoryList = thirdLevelCategoryMapper.selectList(lambdaQueryWrapper);

        return categoryConverter.thirdLevelCategoryPOs2DTOs(thirdLevelCategoryList);
    }
}
