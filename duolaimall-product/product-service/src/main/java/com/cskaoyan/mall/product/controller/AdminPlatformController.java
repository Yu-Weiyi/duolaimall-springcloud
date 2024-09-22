package com.cskaoyan.mall.product.controller;

import com.cskaoyan.mall.common.result.Result;
import com.cskaoyan.mall.product.dto.PlatformAttributeInfoDTO;
import com.cskaoyan.mall.product.dto.PlatformAttributeValueDTO;
import com.cskaoyan.mall.product.query.PlatformAttributeParam;
import com.cskaoyan.mall.product.service.PlatformAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project duolaimall
 * @package com.cskaoyan.mall.product.controller
 * @name AdminPlatformController
 * @description
 * @since 2024-09-20 20:23
 */
@RestController
@RequestMapping("/admin/product")
public class AdminPlatformController {

    @Autowired
    private PlatformAttributeService platformAttributeService;

    @GetMapping("/attrInfoList/{firstLevelCategoryId}/{secondLevelCategoryId}/{thirdLevelCategoryId}")
    public Result<List<PlatformAttributeInfoDTO>> getPlatformAttributeInfoList(
            @PathVariable Long firstLevelCategoryId,
            @PathVariable Long secondLevelCategoryId,
            @PathVariable Long thirdLevelCategoryId
    ) {

        List<PlatformAttributeInfoDTO> platformAttributeInfoDTOList = platformAttributeService.getPlatformAttrInfoList(firstLevelCategoryId, secondLevelCategoryId, thirdLevelCategoryId);

        return Result.ok(platformAttributeInfoDTOList);
    }

    @PostMapping("/saveAttrInfo")
    public Result<Void> savePlatformAttributeInfo(@RequestBody PlatformAttributeParam platformAttributeParam) {

        platformAttributeService.savePlatformAttrInfo(platformAttributeParam);

        return Result.ok();
    }

    @GetMapping("/getAttrValueList/{platformAttributeId}")
    public Result<List<PlatformAttributeValueDTO>> getPlatformAttributeValueList(@PathVariable Long platformAttributeId) {

        List<PlatformAttributeValueDTO> platformAttrInfoList = platformAttributeService.getPlatformAttrValueList(platformAttributeId);

        return Result.ok(platformAttrInfoList);
    }
}
