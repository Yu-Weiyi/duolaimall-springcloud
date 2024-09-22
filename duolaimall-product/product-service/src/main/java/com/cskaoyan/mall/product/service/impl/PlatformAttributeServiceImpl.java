package com.cskaoyan.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cskaoyan.mall.product.converter.dto.PlatformAttributeInfoConverter;
import com.cskaoyan.mall.product.converter.param.PlatformAttributeInfoParamConverter;
import com.cskaoyan.mall.product.dto.PlatformAttributeInfoDTO;
import com.cskaoyan.mall.product.dto.PlatformAttributeValueDTO;
import com.cskaoyan.mall.product.mapper.PlatformAttrInfoMapper;
import com.cskaoyan.mall.product.mapper.PlatformAttrValueMapper;
import com.cskaoyan.mall.product.model.PlatformAttributeInfo;
import com.cskaoyan.mall.product.model.PlatformAttributeValue;
import com.cskaoyan.mall.product.query.PlatformAttributeParam;
import com.cskaoyan.mall.product.service.PlatformAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.service.impl
 * @name PlatformAttributeServiceImpl
 * @description
 * @since 2024-09-20 20:28
 */
@Service
public class PlatformAttributeServiceImpl implements PlatformAttributeService {

    @Autowired
    private PlatformAttrInfoMapper platformAttrInfoMapper;
    @Autowired
    private PlatformAttrValueMapper platformAttrValueMapper;
    @Autowired
    private PlatformAttributeInfoConverter platformAttributeInfoConverter;
    @Autowired
    private PlatformAttributeInfoParamConverter platformAttributeInfoParamConverter;

    /**
     * 根据分类Id 获取平台属性数据
     * 接口说明：
     * 1，平台属性可以挂在一级分类、二级分类和三级分类
     * 2，查询一级分类下面的平台属性，传：firstlevelCatogoryId，0，0；   取出该分类的平台属性
     * 3，查询二级分类下面的平台属性，传：firstlevelCatogoryId，category2Id，0；
     * 取出对应一级分类下面的平台属性与二级分类对应的平台属性
     * 4，查询三级分类下面的平台属性，传：firstlevelCatogoryId，category2Id，category3Id；
     * 取出对应一级分类、二级分类与三级分类对应的平台属性
     *
     * @param firstLevelCategoryId
     * @param secondLevelCategoryId
     * @param thirdLevelCategoryId
     */
    @Override
    public List<PlatformAttributeInfoDTO> getPlatformAttrInfoList(Long firstLevelCategoryId, Long secondLevelCategoryId, Long thirdLevelCategoryId) {

        List<PlatformAttributeInfo> platformAttributeInfoList = platformAttrInfoMapper.selectPlatFormAttrInfoList(firstLevelCategoryId, secondLevelCategoryId, thirdLevelCategoryId);

        return platformAttributeInfoConverter.platformAttributeInfoPOs2DTOs(platformAttributeInfoList);
    }

    @Override
    public void savePlatformAttrInfo(PlatformAttributeParam platformAttributeParam) {

        Long platformAttributeId = platformAttributeParam.getId();
        PlatformAttributeInfo platformAttributeInfo = platformAttributeInfoParamConverter.attributeInfoParam2Info(platformAttributeParam);
        if (platformAttributeId != null) {
            // update info
            platformAttrInfoMapper.updateById(platformAttributeInfo);
            // delete old value list
            LambdaQueryWrapper<PlatformAttributeValue> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper
                    .eq(PlatformAttributeValue::getAttrId, platformAttributeId);
            platformAttrValueMapper.delete(lambdaQueryWrapper);
        } else {
            platformAttrInfoMapper.insert(platformAttributeInfo);
            platformAttributeId = platformAttributeInfo.getId();
        }

        // insert new value list
        Long finalPlatformAttributeId = platformAttributeId;
        platformAttributeParam.getAttrValueList().forEach(platformAttributeValueParam -> {
            PlatformAttributeValue platformAttributeValue = platformAttributeInfoParamConverter.attributeValueParam2AttributeValue(platformAttributeValueParam);
            platformAttributeValue.setId(null);
            platformAttributeValue.setAttrId(finalPlatformAttributeId);
            platformAttrValueMapper.insert(platformAttributeValue);
        });
    }

    @Override
    public List<PlatformAttributeValueDTO> getPlatformAttrValueList(Long attrId) {

        LambdaQueryWrapper<PlatformAttributeValue> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(PlatformAttributeValue::getAttrId, attrId);

        List<PlatformAttributeValue> platformAttributeValueList = platformAttrValueMapper.selectList(lambdaQueryWrapper);

        return platformAttributeInfoConverter.platformAttributeValuePOs2DTOs(platformAttributeValueList);
    }


}
