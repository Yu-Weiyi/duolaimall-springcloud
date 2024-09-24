package com.cskaoyan.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cskaoyan.mall.product.converter.dto.SpuInfoConverter;
import com.cskaoyan.mall.product.converter.dto.SpuInfoPageConverter;
import com.cskaoyan.mall.product.converter.param.SpuInfoParamConverter;
import com.cskaoyan.mall.product.dto.SpuImageDTO;
import com.cskaoyan.mall.product.dto.SpuInfoPageDTO;
import com.cskaoyan.mall.product.dto.SpuPosterDTO;
import com.cskaoyan.mall.product.dto.SpuSaleAttributeInfoDTO;
import com.cskaoyan.mall.product.mapper.*;
import com.cskaoyan.mall.product.model.SpuImage;
import com.cskaoyan.mall.product.model.SpuInfo;
import com.cskaoyan.mall.product.model.SpuSaleAttributeInfo;
import com.cskaoyan.mall.product.query.SpuInfoParam;
import com.cskaoyan.mall.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.service.impl
 * @name SpuServiceImpl
 * @description
 * @since 2024-09-23 09:26
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private SpuSaleAttrInfoMapper spuSaleAttrInfoMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuPosterMapper spuPosterMapper;
    @Autowired
    private SpuInfoPageConverter spuInfoPageConverter;
    @Autowired
    private SpuInfoParamConverter spuInfoParamConverter;
    @Autowired
    private SpuInfoConverter spuInfoConverter;

    @Override
    public SpuInfoPageDTO getSpuInfoPage(Page<SpuInfo> pageParam, Long category3Id) {

        LambdaQueryWrapper<SpuInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SpuInfo::getThirdLevelCategoryId, category3Id);
        Page<SpuInfo> spuInfoPage = spuInfoMapper.selectPage(pageParam, lambdaQueryWrapper);
        List<Long> spuInfoIdList = spuInfoPage.getRecords().stream().map(SpuInfo::getId).toList();
        if (spuInfoIdList == null || spuInfoIdList.isEmpty()) {
            return null;
        }
        List<SpuInfo> spuInfoList = spuInfoMapper.selectSpuInfoPageByThirdLevelCategoryId(spuInfoIdList);
        spuInfoPage.setRecords(spuInfoList);
        return spuInfoPageConverter.spuInfoPage2PageDTO(spuInfoPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuInfoParam spuInfoParam) {

        SpuInfo spuInfo = spuInfoParamConverter.spuInfoParam2Info(spuInfoParam);
        spuInfoMapper.insert(spuInfo);
        spuInfoParamConverter.spuSaleAttributeParams2Infos(spuInfoParam.getSpuSaleAttrList())
                .forEach(spuSaleAttrInfo -> {
                    spuSaleAttrInfo.setSpuId(spuInfo.getId());
                    spuSaleAttrInfoMapper.insert(spuSaleAttrInfo);
                    spuSaleAttrInfo.getSpuSaleAttrValueList().forEach(spuSaleAttributeValue -> {
                        spuSaleAttributeValue.setSpuId(spuInfo.getId());
                        spuSaleAttributeValue.setSpuSaleAttrId(spuSaleAttrInfo.getId());
                        spuSaleAttrValueMapper.insert(spuSaleAttributeValue);
                    });
                });
        spuInfoParamConverter.spuImageParams2Images(spuInfoParam.getSpuImageList())
                .forEach(spuImage -> {
                    spuImage.setSpuId(spuInfo.getId());
                    spuImageMapper.insert(spuImage);
                });
        spuInfoParamConverter.spuPosterParams2Posters(spuInfoParam.getSpuPosterList())
                .forEach(spuPoster -> {
                    spuPoster.setSpuId(spuInfo.getId());
                    spuPosterMapper.insert(spuPoster);
                });
    }

    @Override
    public List<SpuImageDTO> getSpuImageList(Long spuId) {

        LambdaQueryWrapper<SpuImage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SpuImage::getSpuId, spuId);
        List<SpuImage> spuImageList = spuImageMapper.selectList(lambdaQueryWrapper);
        List<SpuImageDTO> spuImageDTOList = spuInfoConverter.spuImagePOs2DTOs(spuImageList);
        return spuImageDTOList;
    }

    @Override
    public List<SpuSaleAttributeInfoDTO> getSpuSaleAttrList(Long spuId) {

        List<SpuSaleAttributeInfo> spuSaleAttributeInfoList = spuSaleAttrInfoMapper.selectSpuSaleAttrList(spuId);
        return spuInfoConverter.spuSaleAttributeInfoPOs2DTOs(spuSaleAttributeInfoList);
    }

    @Override
    public List<SpuPosterDTO> findSpuPosterBySpuId(Long spuId) {
        return List.of();
    }

    @Override
    public Map<String, Long> getSkuValueIdsMap(Long spuId) {
        return Map.of();
    }
}
