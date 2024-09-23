package com.cskaoyan.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cskaoyan.mall.product.converter.dto.CategoryConverter;
import com.cskaoyan.mall.product.converter.dto.TrademarkConverter;
import com.cskaoyan.mall.product.dto.*;
import com.cskaoyan.mall.product.mapper.CategoryTrademarkMapper;
import com.cskaoyan.mall.product.mapper.FirstLevelCategoryMapper;
import com.cskaoyan.mall.product.mapper.SecondLevelCategoryMapper;
import com.cskaoyan.mall.product.mapper.ThirdLevelCategoryMapper;
import com.cskaoyan.mall.product.model.*;
import com.cskaoyan.mall.product.query.CategoryTrademarkParam;
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
    private FirstLevelCategoryMapper firstLevelCategoryMapper;
    @Autowired
    private SecondLevelCategoryMapper secondLevelCategoryMapper;
    @Autowired
    private ThirdLevelCategoryMapper thirdLevelCategoryMapper;
    @Autowired
    private CategoryTrademarkMapper categoryTrademarkMapper;
    @Autowired
    private CategoryConverter categoryConverter;
    @Autowired
    private TrademarkConverter trademarkConverter;

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

    /**
     * 根据三级分类获取品牌
     *
     * @param category3Id
     */
    @Override
    public List<TrademarkDTO> findTrademarkList(Long category3Id) {

        List<Trademark> trademarkList = categoryTrademarkMapper.selectTrademarkListByThirdLevelCategoryId(category3Id);
        return trademarkConverter.trademarkPOs2DTOs(trademarkList);
    }

    /**
     * 保存分类与品牌关联
     *
     * @param categoryTrademarkParam
     */
    @Override
    public void save(CategoryTrademarkParam categoryTrademarkParam) {

        categoryTrademarkParam.getTrademarkIdList().forEach(tradeMarkId -> {
            categoryTrademarkMapper.insert(new CategoryTrademark(categoryTrademarkParam.getCategory3Id(), tradeMarkId, null));
        });
    }

    /**
     * 获取当前未被三级分类关联的所有品牌
     *
     * @param thirdLevelCategoryId
     */
    @Override
    public List<TrademarkDTO> findUnLinkedTrademarkList(Long thirdLevelCategoryId) {

        List<Trademark> trademarkList = categoryTrademarkMapper.selectTrademarkListExcludedByThirdLevelCategoryId(thirdLevelCategoryId);
        return trademarkConverter.trademarkPOs2DTOs(trademarkList);
    }

    /**
     * 删除关联
     *
     * @param thirdLevelCategoryId
     * @param trademarkId
     */
    @Override
    public void remove(Long thirdLevelCategoryId, Long trademarkId) {

        LambdaUpdateWrapper<CategoryTrademark> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .eq(CategoryTrademark::getThirdLevelCategoryId, thirdLevelCategoryId)
                .eq(CategoryTrademark::getTrademarkId, trademarkId);
        categoryTrademarkMapper.delete(lambdaUpdateWrapper);
    }

    @Override
    public CategoryHierarchyDTO getCategoryViewByCategoryId(Long thirdLevelCategoryId) {
        return null;
    }

    @Override
    public List<FirstLevelCategoryNodeDTO> getCategoryTreeList() {
        return List.of();
    }
}
