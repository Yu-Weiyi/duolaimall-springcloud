package com.cskaoyan.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cskaoyan.mall.product.model.SkuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {

    List<SkuInfo> selectSkuInfoPage(@Param("skuInfoIdList") List<Long> skuInfoIdList);
}
