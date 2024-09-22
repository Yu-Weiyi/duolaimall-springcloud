package com.cskaoyan.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cskaoyan.mall.product.converter.dto.TrademarkConverter;
import com.cskaoyan.mall.product.converter.dto.TrademarkPageConverter;
import com.cskaoyan.mall.product.dto.TrademarkDTO;
import com.cskaoyan.mall.product.dto.TrademarkPageDTO;
import com.cskaoyan.mall.product.mapper.TrademarkMapper;
import com.cskaoyan.mall.product.model.Trademark;
import com.cskaoyan.mall.product.query.TrademarkParam;
import com.cskaoyan.mall.product.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.service.impl
 * @name TrademarkServiceImpl
 * @description
 * @since 2024-09-22 20:07
 */
@Service
public class TrademarkServiceImpl implements TrademarkService {

    @Autowired
    private TrademarkMapper trademarkMapper;
    @Autowired
    private TrademarkPageConverter trademarkPageConverter;
    @Autowired
    private TrademarkConverter trademarkConverter;

    @Override
    public TrademarkDTO getTrademarkByTmId(Long tmId) {

        Trademark trademark = trademarkMapper.selectById(tmId);
        return trademarkConverter.trademarkPO2DTO(trademark);
    }

    /**
     * 根据分页参数，分页查询品牌列表
     *
     * @param pageParam
     */
    @Override
    public TrademarkPageDTO getPage(Page<Trademark> pageParam) {

        Page<Trademark> trademarkPage = trademarkMapper.selectPage(pageParam, null);

        return trademarkPageConverter.tradeMarkPagePO2PageDTO(trademarkPage);
    }

    @Override
    public void save(TrademarkParam trademarkParam) {

        trademarkMapper.insert(trademarkConverter.trademarkParam2Trademark(trademarkParam));
    }

    @Override
    public void updateById(TrademarkParam trademarkParam) {

        trademarkMapper.updateById(trademarkConverter.trademarkParam2Trademark(trademarkParam));
    }

    @Override
    public void removeById(Long id) {

        trademarkMapper.deleteById(id);
    }
}
