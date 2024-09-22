package com.cskaoyan.mall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cskaoyan.mall.common.result.Result;
import com.cskaoyan.mall.product.dto.TrademarkDTO;
import com.cskaoyan.mall.product.dto.TrademarkPageDTO;
import com.cskaoyan.mall.product.model.Trademark;
import com.cskaoyan.mall.product.query.TrademarkParam;
import com.cskaoyan.mall.product.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.controller
 * @name AdminTrademarkController
 * @description
 * @since 2024-09-22 20:06
 */
@RestController
@RequestMapping("/admin/product/baseTrademark")
public class AdminBaseTrademarkController {

    @Autowired
    private TrademarkService trademarkService;

    @GetMapping("/{pageNo}/{pageSize}")
    public Result<TrademarkPageDTO> getPage(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {

        Page<Trademark> pageParam = new Page<>(pageNo, pageSize);
        TrademarkPageDTO trademarkPageDTO = trademarkService.getPage(pageParam);
        return Result.ok(trademarkPageDTO);
    }

    @PostMapping("/save")
    public Result<Void> saveTrademark(@RequestBody TrademarkParam trademarkParam) {

        trademarkService.save(trademarkParam);
        return Result.ok();
    }

    @GetMapping("/get/{id}")
    public Result<TrademarkDTO> getTrademark(@PathVariable Long id) {

        TrademarkDTO trademarkDTO = trademarkService.getTrademarkByTmId(id);
        return Result.ok(trademarkDTO);
    }

    @PutMapping("/update")
    public Result<Void> updateTrademark(@RequestBody TrademarkParam trademarkParam) {

        trademarkService.updateById(trademarkParam);
        return Result.ok();
    }

    @DeleteMapping("/remove/{tradeMarkId}")
    public Result<Void> removeTrademark(@PathVariable Long tradeMarkId) {

        trademarkService.removeById(tradeMarkId);
        return Result.ok();
    }
}
