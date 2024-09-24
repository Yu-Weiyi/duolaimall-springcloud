package com.cskaoyan.mall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cskaoyan.mall.common.result.Result;
import com.cskaoyan.mall.product.dto.SkuInfoPageDTO;
import com.cskaoyan.mall.product.model.SkuInfo;
import com.cskaoyan.mall.product.query.SkuInfoParam;
import com.cskaoyan.mall.product.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.controller
 * @name AdminSkuController
 * @description
 * @since 2024-09-23 16:34
 */
@RestController
@RequestMapping("/admin/product")
public class AdminSkuController {

    @Autowired
    private SkuService skuService;

    @PostMapping("/saveSkuInfo")
    public Result<Void> saveSkuInfo(@RequestBody SkuInfoParam skuInfoParam) {

        skuService.saveSkuInfo(skuInfoParam);
        return Result.ok();
    }

    @GetMapping("/list/{page}/{limit}")
    public Result<SkuInfoPageDTO> getPage(@PathVariable Integer page, @PathVariable Integer limit) {

        Page<SkuInfo> pageParam = new Page<>(page, limit);
        SkuInfoPageDTO skuInfoPageDTO = skuService.getPage(pageParam);
        return Result.ok(skuInfoPageDTO);
    }

    @GetMapping("/onSale/{skuId}")
    public Result<Void> onSale(@PathVariable Long skuId) {

        skuService.onSale(skuId);
        return Result.ok();
    }

    @GetMapping("/cancelSale/{skuId}")
    public Result<Void> cancelSale(@PathVariable Long skuId) {

        skuService.offSale(skuId);
        return Result.ok();
    }
}
