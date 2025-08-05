package com.mactwo.mactwocommandservice.application.controller.product;

import com.mactwo.dto.request.product.CreateProductRequest;
import com.mactwo.dto.request.product.UpdateProductRequest;
import com.mactwo.dto.request.product.UpdateStockRequest;
import com.mactwo.mactwocommandservice.application.command.command.product.CreateProductCommand;
import com.mactwo.mactwocommandservice.application.command.command.product.DeleteProductCommand;
import com.mactwo.mactwocommandservice.application.command.command.product.UpdateProductCommand;
import com.mactwo.mactwocommandservice.application.command.command.product.UpdateStockCommand;
import com.mactwo.mactwocommandservice.application.command.handler.product.CreateProductCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.product.DeleteProductCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.product.UpdateProductCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.product.UpdateStockCommandHandler;
import com.mactwo.mactwocommandservice.application.controller.base.RestApiV1;
import com.mactwo.mactwocommandservice.application.controller.base.RestData;
import com.mactwo.mactwocommandservice.application.controller.base.VsResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductCommandHandler createProductCommandHandler;
    private final UpdateProductCommandHandler updateProductCommandHandler;
    private final DeleteProductCommandHandler deleteProductCommandHandler;
    private final UpdateStockCommandHandler updateStockCommandHandler;


    @Tag(name = "product-api-admin")
    @Operation(summary = "Tạo sản phẩm (chỉ Admin)")
    @PostMapping("/create")
    public ResponseEntity<RestData<?>> createProduct(@RequestBody CreateProductRequest request) {
        var command = new CreateProductCommand(request);
        var product = createProductCommandHandler.handle(command);
        return VsResponseUtil.ok(HttpStatus.CREATED, product);
    }

    @Tag(name = "product-api-admin")
    @Operation(summary = "Sửa thông tin sản phẩm (chỉ Admin)")
    @PutMapping("/{productId}")
    public ResponseEntity<RestData<?>> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
        var command = new UpdateProductCommand(productId, request);
        var product = updateProductCommandHandler.handle(command);
        return VsResponseUtil.ok(product);
    }

    @Tag(name = "product-api-admin")
    @Operation(summary = "Xóa sản phẩm (chỉ Admin)")
    @DeleteMapping("/{productId}")
    public ResponseEntity<RestData<?>> deleteProduct(@PathVariable Long productId) {
        var command = new DeleteProductCommand(productId);
        deleteProductCommandHandler.handle(command);
        return VsResponseUtil.ok("Product deleted successfully.");
    }

    @Tag(name = "product-api-admin")
    @Operation(summary = "Cập nhật số sản phẩm tồn kho (chỉ Admin)")
    @PatchMapping("/variants/{variantId}/stock")
    public ResponseEntity<RestData<?>> updateStock(
            @PathVariable Long variantId,
            @RequestBody UpdateStockRequest request) {
        var command = new UpdateStockCommand(variantId, request.getStockQuantity());
        updateStockCommandHandler.handle(command);
        return VsResponseUtil.ok("Stock updated successfully for variant " + variantId);
    }
}
