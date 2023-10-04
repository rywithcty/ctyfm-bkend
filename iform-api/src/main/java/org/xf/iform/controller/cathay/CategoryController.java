package org.xf.iform.controller.cathay;


import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xf.iform.core.common.BaseResponse;
import org.xf.iform.core.entity.cathay.CategoryEntity;
import org.xf.iform.service.services.cathay.CategoryService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/iform")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    @Operation(summary = "取得category")
    public ResponseEntity<?> getCategoryList(@RequestParam(required = false) Integer catId,
                                              @RequestParam(required = false) String catTitle) {

        log.info("comList: catId=" + catId + ", catTitle=" + catTitle);
        BaseResponse<List<CategoryEntity>> respDto = new BaseResponse<>();
        respDto.setData(categoryService.getCategoryList(catId, catTitle));

        return ResponseEntity.ok(respDto);
    }

    @GetMapping("/category/{catId}")
    @Operation(summary = "從ID取得category")
    public ResponseEntity<?> getCategory(@PathVariable int catId) {

        log.info("category:catId=" + catId);
        BaseResponse<CategoryEntity> respDto = new BaseResponse<>();
        respDto.setData(categoryService.getCategory(catId));

        return ResponseEntity.ok(respDto);
    }

    @PostMapping("/category")
    @Operation(summary = "新增category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryEntity categoryEntity) {

        log.info("categoryAdd:=" + categoryEntity.toString());

        BaseResponse<Integer> respDto = new BaseResponse<>();
        respDto.setData(categoryService.addCategory(categoryEntity));

        return ResponseEntity.ok(respDto);
    }
    @PutMapping("/category")
    @Operation(summary = "更新category")
    public ResponseEntity<?> editCategory(@RequestBody CategoryEntity categoryEntity) {

        log.info("categoryEdit:=" + categoryEntity.toString());
        categoryService.editCategory(categoryEntity);
        BaseResponse<String> respDto = new BaseResponse<>();
        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }

    @DeleteMapping("/category")
    @Operation(summary = "刪除category")
    public ResponseEntity<?> deleteCategory(@RequestParam int catId) {

        log.info("categoryDelete:=" + catId);
        BaseResponse<String> respDto = new BaseResponse<>();
        categoryService.deleteCategory(catId);

        respDto.setData("success");

        return ResponseEntity.ok(respDto);
    }
}
