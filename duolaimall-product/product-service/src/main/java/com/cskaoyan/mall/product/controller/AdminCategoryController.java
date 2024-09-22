package com.cskaoyan.mall.product.controller;

import com.cskaoyan.mall.common.result.Result;
import com.cskaoyan.mall.product.dto.FirstLevelCategoryDTO;
import com.cskaoyan.mall.product.dto.SecondLevelCategoryDTO;
import com.cskaoyan.mall.product.dto.ThirdLevelCategoryDTO;
import com.cskaoyan.mall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.controller
 * @name AdminCategoryController
 * @description
 * @since 2024-09-20 19:12
 */
@RestController
@RequestMapping("/admin/product")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategory1")
    public Result<List<FirstLevelCategoryDTO>> getCategory1() {

        List<FirstLevelCategoryDTO> firstLevelCategoryDTOList = categoryService.getFirstLevelCategory();

        return Result.ok(firstLevelCategoryDTOList);
    }

    @GetMapping("/getCategory2/{firstLevelCategoryId}")
    public Result<List<SecondLevelCategoryDTO>> getCategory2(@PathVariable Long firstLevelCategoryId) {

        List<SecondLevelCategoryDTO> secondLevelCategoryDTOList = categoryService.getSecondLevelCategory(firstLevelCategoryId);

        return Result.ok(secondLevelCategoryDTOList);
    }

    @GetMapping("/getCategory3/{secondLevelCategoryId}")
    public Result<List<ThirdLevelCategoryDTO>> getCategory3(@PathVariable Long secondLevelCategoryId) {

        List<ThirdLevelCategoryDTO> thirdLevelCategoryDTOList = categoryService.getThirdLevelCategory(secondLevelCategoryId);

        return Result.ok(thirdLevelCategoryDTOList);
    }
}
