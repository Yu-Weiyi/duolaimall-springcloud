package com.cskaoyan.mall.product.controller;

import com.cskaoyan.mall.common.result.Result;
import com.cskaoyan.mall.product.dto.TrademarkDTO;
import com.cskaoyan.mall.product.query.CategoryTrademarkParam;
import com.cskaoyan.mall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.controller
 * @name AdminBaseCategoryTrademarkController
 * @description
 * @since 2024-09-23 01:19
 */
@RestController
@RequestMapping("/admin/product/baseCategoryTrademark")
public class AdminBaseCategoryTrademarkController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findTrademarkList/{thirdLevelCategoryId}")
    public Result<List<TrademarkDTO>> findTrademarkList(@PathVariable Long thirdLevelCategoryId) {

        List<TrademarkDTO> trademarkDTOList = categoryService.findTrademarkList(thirdLevelCategoryId);
        return Result.ok(trademarkDTOList);
    }

    @GetMapping("/findCurrentTrademarkList/{thirdLevelCategoryId}")
    public Result<List<TrademarkDTO>> findCurrentTrademarkList(@PathVariable Long thirdLevelCategoryId) {

        List<TrademarkDTO> trademarkDTOList = categoryService.findUnLinkedTrademarkList(thirdLevelCategoryId);
        return Result.ok(trademarkDTOList);
    }

    @PostMapping("/save")
    public Result<Void> save(@RequestBody CategoryTrademarkParam categoryTrademarkParam) {

        categoryService.save(categoryTrademarkParam);
        return Result.ok();
    }

    @DeleteMapping("/remove/{thirdLevelCategoryId}/{trademarkId}")
    public Result<Void> remove(@PathVariable Long thirdLevelCategoryId, @PathVariable Long trademarkId) {

        categoryService.remove(thirdLevelCategoryId, trademarkId);
        return Result.ok();
    }
}
