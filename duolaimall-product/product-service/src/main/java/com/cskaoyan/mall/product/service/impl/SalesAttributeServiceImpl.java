package com.cskaoyan.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cskaoyan.mall.product.converter.dto.SaleAttributeInfoConverter;
import com.cskaoyan.mall.product.dto.SaleAttributeInfoDTO;
import com.cskaoyan.mall.product.mapper.SaleAttrInfoMapper;
import com.cskaoyan.mall.product.model.SaleAttributeInfo;
import com.cskaoyan.mall.product.service.SalesAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.service.impl
 * @name SalesAttributeServiceIMpl
 * @description
 * @since 2024-09-23 14:20
 */
@Service
public class SalesAttributeServiceImpl implements SalesAttributeService {

    @Autowired
    private SaleAttrInfoMapper saleAttrInfoMapper;
    @Autowired
    private SaleAttributeInfoConverter saleAttributeInfoConverter;

    @Override
    public List<SaleAttributeInfoDTO> getSaleAttrInfoList() {

        LambdaQueryWrapper<SaleAttributeInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SaleAttributeInfo::getIsDeleted, 0);
        List<SaleAttributeInfo> saleAttributeInfoList = saleAttrInfoMapper.selectList(lambdaQueryWrapper);
        return saleAttributeInfoConverter.saleAttributeInfoPOs2DTOs(saleAttributeInfoList);
    }
}
