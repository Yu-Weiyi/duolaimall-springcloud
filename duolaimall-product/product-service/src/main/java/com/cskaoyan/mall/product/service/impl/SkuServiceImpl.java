package com.cskaoyan.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cskaoyan.mall.product.converter.dto.SkuInfoPageConverter;
import com.cskaoyan.mall.product.converter.param.SkuInfoParamConverter;
import com.cskaoyan.mall.product.dto.PlatformAttributeInfoDTO;
import com.cskaoyan.mall.product.dto.SkuInfoDTO;
import com.cskaoyan.mall.product.dto.SkuInfoPageDTO;
import com.cskaoyan.mall.product.dto.SpuSaleAttributeInfoDTO;
import com.cskaoyan.mall.product.mapper.SkuImageMapper;
import com.cskaoyan.mall.product.mapper.SkuInfoMapper;
import com.cskaoyan.mall.product.mapper.SkuPlatformAttrValueMapper;
import com.cskaoyan.mall.product.mapper.SkuSaleAttrValueMapper;
import com.cskaoyan.mall.product.model.SkuInfo;
import com.cskaoyan.mall.product.query.SkuInfoParam;
import com.cskaoyan.mall.product.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.service.impl
 * @name SkuServiceImpl
 * @description
 * @since 2024-09-23 16:41
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuPlatformAttrValueMapper skuPlatformAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    private SkuInfoParamConverter skuInfoParamConverter;
    @Autowired
    private SkuInfoPageConverter skuInfoPageConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSkuInfo(SkuInfoParam skuInfoParam) {

        SkuInfo skuInfo = skuInfoParamConverter.SkuInfoParam2Info(skuInfoParam);
        skuInfoMapper.insert(skuInfo);
        skuInfoParamConverter.skuImageParams2Images(skuInfoParam.getSkuImageList())
                .forEach(skuImage -> {
                    skuImage.setSkuId(skuInfo.getId());
                    skuImageMapper.insert(skuImage);
                });
        skuInfoParamConverter.skuPlatformAttributeValueParams2Values(skuInfoParam.getSkuAttrValueList())
                .forEach(skuPlatformAttributeValue -> {
                    skuPlatformAttributeValue.setSkuId(skuInfo.getId());
                    skuPlatformAttrValueMapper.insert(skuPlatformAttributeValue);
                });
        skuInfoParamConverter.skuSaleAttributeValueParams2Values(skuInfoParam.getSkuSaleAttrValueList())
                .forEach(skuSaleAttributeValue -> {
                    skuSaleAttributeValue.setSkuId(skuInfo.getId());
                    skuSaleAttributeValue.setSpuId(skuInfo.getSpuId());
                    skuSaleAttrValueMapper.insert(skuSaleAttributeValue);
                });
    }

    @Override
    public SkuInfoPageDTO getPage(Page<SkuInfo> pageParam) {

        Page<SkuInfo> skuInfoPage = skuInfoMapper.selectPage(pageParam, new LambdaQueryWrapper<SkuInfo>());
        List<Long> skuInfoIdList = skuInfoPage.getRecords().stream().map(SkuInfo::getId).toList();
        if (skuInfoIdList == null || skuInfoIdList.isEmpty()) {
            return null;
        }
        List<SkuInfo> skuInfoList = skuInfoMapper.selectSkuInfoPage(skuInfoIdList);
        skuInfoPage.setRecords(skuInfoList);
        return skuInfoPageConverter.skuInfoPagePO2PageDTO(skuInfoPage);
    }

    @Override
    public void onSale(Long skuId) {

        updateSale(skuId, true);
    }

    @Override
    public void offSale(Long skuId) {

        updateSale(skuId, false);
    }

    private void updateSale(Long skuId, boolean isSale) {

        SkuInfo skuInfoUpdate = new SkuInfo();
        skuInfoUpdate.setId(skuId);
        skuInfoUpdate.setIsSale(isSale ? 1 : 0);
        skuInfoMapper.updateById(skuInfoUpdate);
    }

    @Override
    public SkuInfoDTO getSkuInfo(Long skuId) {
        return null;
    }

    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        return null;
    }

    @Override
    public List<SpuSaleAttributeInfoDTO> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        return List.of();
    }

    @Override
    public List<PlatformAttributeInfoDTO> getPlatformAttrInfoBySku(Long skuId) {
        return List.of();
    }
}
