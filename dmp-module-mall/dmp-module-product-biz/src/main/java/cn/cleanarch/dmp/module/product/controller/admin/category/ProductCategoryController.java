package cn.cleanarch.dmp.module.product.controller.admin.category;

import cn.cleanarch.dmp.framework.common.pojo.CommonResult;
import cn.cleanarch.dmp.module.product.controller.admin.category.vo.ProductCategoryRespVO;
import cn.cleanarch.dmp.module.product.controller.admin.category.vo.ProductCategoryListReqVO;
import cn.cleanarch.dmp.module.product.controller.admin.category.vo.ProductCategoryCreateReqVO;
import cn.cleanarch.dmp.module.product.controller.admin.category.vo.ProductCategoryUpdateReqVO;
import cn.cleanarch.dmp.module.product.convert.category.ProductCategoryConvert;
import cn.cleanarch.dmp.module.product.dal.dataobject.category.ProductCategoryDO;
import cn.cleanarch.dmp.module.product.service.category.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

import static cn.cleanarch.dmp.framework.common.pojo.CommonResult.success;

@Api(tags = "管理后台 - 商品分类")
@RestController
@RequestMapping("/product/category")
@Validated
public class ProductCategoryController {

    @Resource
    private ProductCategoryService categoryService;

    @PostMapping("/create")
    @ApiOperation("创建商品分类")
    @PreAuthorize("@ss.hasPermission('product:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody ProductCategoryCreateReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新商品分类")
    @PreAuthorize("@ss.hasPermission('product:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody ProductCategoryUpdateReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除商品分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('product:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得商品分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('product:category:query')")
    public CommonResult<ProductCategoryRespVO> getCategory(@RequestParam("id") Long id) {
        ProductCategoryDO category = categoryService.getCategory(id);
        return success(ProductCategoryConvert.INSTANCE.convert(category));
    }

    @GetMapping("/list")
    @ApiOperation("获得商品分类列表")
    @PreAuthorize("@ss.hasPermission('product:category:query')")
    public CommonResult<List<ProductCategoryRespVO>> getCategoryList(@Valid ProductCategoryListReqVO treeListReqVO) {
        List<ProductCategoryDO> list = categoryService.getEnableCategoryList(treeListReqVO);
        list.sort(Comparator.comparing(ProductCategoryDO::getSort));
        return success(ProductCategoryConvert.INSTANCE.convertList(list));
    }

}