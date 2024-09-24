package com.cskaoyan.mall.product.converter.param;

import com.cskaoyan.mall.product.model.SkuImage;
import com.cskaoyan.mall.product.model.SkuInfo;
import com.cskaoyan.mall.product.model.SkuPlatformAttributeValue;
import com.cskaoyan.mall.product.model.SkuSaleAttributeValue;
import com.cskaoyan.mall.product.query.SkuImageParam;
import com.cskaoyan.mall.product.query.SkuInfoParam;
import com.cskaoyan.mall.product.query.SkuPlatformAttributeValueParam;
import com.cskaoyan.mall.product.query.SkuSaleAttributeValueParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkuInfoParamConverter {

    @Mapping(source = "category3Id", target = "thirdLevelCategoryId")
    @Mapping(source = "skuAttrValueList", target = "skuPlatformAttributeValueList")
    @Mapping(source = "skuSaleAttrValueList", target = "skuSaleAttributeValueList")
    SkuInfo SkuInfoParam2Info(SkuInfoParam skuInfoParam);

    SkuImage skuImageParam2Image(SkuImageParam skuImageParam);
    List<SkuImage> skuImageParams2Images(List<SkuImageParam> skuImageParamList);

    SkuPlatformAttributeValue skuPlatformAttributeValueParam2Value(SkuPlatformAttributeValueParam param);
    List<SkuPlatformAttributeValue> skuPlatformAttributeValueParams2Values(List<SkuPlatformAttributeValueParam> param);

    @Mapping(source = "saleAttrValueId", target = "spuSaleAttrValueId")
    SkuSaleAttributeValue skuSaleAttributeValueParam2Value(SkuSaleAttributeValueParam param);
    List<SkuSaleAttributeValue> skuSaleAttributeValueParams2Values(List<SkuSaleAttributeValueParam> param);
}
