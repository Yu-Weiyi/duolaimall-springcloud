package com.cskaoyan.mall.product.converter.param;

import com.cskaoyan.mall.product.model.*;
import com.cskaoyan.mall.product.query.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpuInfoParamConverter {

    @Mapping(source = "category3Id", target = "thirdLevelCategoryId")
    @Mapping(source = "spuSaleAttrList", target = "spuSaleAttributeInfoList")
    SpuInfo spuInfoParam2Info(SpuInfoParam spuInfo);

    SpuImage spuImageParam2Image(SpuImageParam spuImage);
    List<SpuImage> spuImageParams2Images(List<SpuImageParam> spuImages);

    SpuPoster spuPosterParam2Poster(SpuPosterParam spuPoster);
    List<SpuPoster> spuPosterParams2Posters(List<SpuPosterParam> spuPosters);

    @Mapping(source = "baseSaleAttrId", target = "saleAttrId")
    SpuSaleAttributeInfo spuSaleAttributeParam2Info(SpuSaleAttributeInfoParam spuSaleAttributeInfo);
    List<SpuSaleAttributeInfo> spuSaleAttributeParams2Infos(List<SpuSaleAttributeInfoParam> spuSaleAttributeInfos);
    @Mapping(source = "saleAttrValueName", target = "spuSaleAttrValueName")
    @Mapping(source = "baseSaleAttrId", target = "spuSaleAttrId")
    SpuSaleAttributeValue spuSaleAttributeValueParam2Value (SpuSaleAttributeValueParam spuSaleAttributeValue);
    List<SpuSaleAttributeValue> spuSaleAttributeValueParams2Values (List<SpuSaleAttributeValueParam> spuSaleAttributeValues);
}
