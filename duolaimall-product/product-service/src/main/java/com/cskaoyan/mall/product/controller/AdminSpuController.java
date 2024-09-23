package com.cskaoyan.mall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cskaoyan.mall.common.result.Result;
import com.cskaoyan.mall.product.dto.SaleAttributeInfoDTO;
import com.cskaoyan.mall.product.dto.SpuImageDTO;
import com.cskaoyan.mall.product.dto.SpuInfoPageDTO;
import com.cskaoyan.mall.product.dto.SpuSaleAttributeInfoDTO;
import com.cskaoyan.mall.product.model.SpuInfo;
import com.cskaoyan.mall.product.query.SpuInfoParam;
import com.cskaoyan.mall.product.service.SalesAttributeService;
import com.cskaoyan.mall.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.controller
 * @name AdminSpuController
 * @description
 * @since 2024-09-23 09:15
 */
@RestController
@RequestMapping("/admin/product")
public class AdminSpuController {

    @Autowired
    private SpuService spuService;
    @Autowired
    private SalesAttributeService salesAttributeService;

    @GetMapping("/{page}/{size}")
    public Result<SpuInfoPageDTO> findSpuInfoPage(@PathVariable Integer page, @PathVariable Integer size, @RequestParam Long category3Id) {

        Page<SpuInfo> pageParam = new Page<>(page, size);
        SpuInfoPageDTO spuInfoPageDTO = spuService.getSpuInfoPage(pageParam, category3Id);
        return Result.ok(spuInfoPageDTO);
    }

    @GetMapping("/baseSaleAttrList")
    public Result<List<SaleAttributeInfoDTO>> getBaseSaleAttrList() {

        List<SaleAttributeInfoDTO> saleAttributeInfoDTOList = salesAttributeService.getSaleAttrInfoList();
        return Result.ok(saleAttributeInfoDTOList);
    }

    @PostMapping("/saveSpuInfo")
    public Result<Void> saveSpuInfo(@RequestBody SpuInfoParam spuInfoParam) {

        spuService.saveSpuInfo(spuInfoParam);
        return Result.ok();
    }

    @GetMapping("/spuImageList/{spuId}")
    public Result<List<SpuImageDTO>> getSpuImageList(@PathVariable Long spuId) {

        List<SpuImageDTO> spuImageDTOList = spuService.getSpuImageList(spuId);
        return Result.ok(spuImageDTOList);
    }

    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result<List<SpuSaleAttributeInfoDTO>> getSaleAttributeInfoList(@PathVariable Long spuId) {

        List<SpuSaleAttributeInfoDTO> spuSaleAttributeInfoDTOList = spuService.getSpuSaleAttrList(spuId);
        return Result.ok(spuSaleAttributeInfoDTOList);
    }
}
