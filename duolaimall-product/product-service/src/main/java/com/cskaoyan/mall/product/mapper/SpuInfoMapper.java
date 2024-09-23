package com.cskaoyan.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cskaoyan.mall.product.model.SpuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {

    List<SpuInfo> selectSpuInfoPageByThirdLevelCategoryId(@Param("spuInfoIdList") List<Long> spuInfoIdList);
}
