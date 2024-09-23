package com.cskaoyan.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cskaoyan.mall.product.model.CategoryTrademark;
import com.cskaoyan.mall.product.model.Trademark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryTrademarkMapper extends BaseMapper<CategoryTrademark> {

    List<Trademark> selectTrademarkListByThirdLevelCategoryId(@Param("thirdLevelCategoryId") Long thirdLevelCategoryId);

    List<Trademark> selectTrademarkListExcludedByThirdLevelCategoryId(@Param("thirdLevelCategoryId") Long thirdLevelCategoryId);
}
