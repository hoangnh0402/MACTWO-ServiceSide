package com.mactwo.mactwocommandservice.application.controller.category;

import com.mactwo.dto.request.category.CategoryRequest;
import com.mactwo.mactwocommandservice.application.command.command.category.CreateCategoryCommand;
import com.mactwo.mactwocommandservice.application.command.command.category.DeleteCategoryCommand;
import com.mactwo.mactwocommandservice.application.command.command.category.UpdateCategoryCommand;
import com.mactwo.mactwocommandservice.application.command.handler.category.CreateCategoryCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.category.DeleteCategoryCommandHandler;
import com.mactwo.mactwocommandservice.application.command.handler.category.UpdateCategoryCommandHandler;
import com.mactwo.mactwocommandservice.application.controller.base.RestApiV1;
import com.mactwo.mactwocommandservice.application.controller.base.RestData;
import com.mactwo.mactwocommandservice.application.controller.base.VsResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
@RequestMapping("/admin/categories")
@Tag(name = "5. Category Management (Admin)")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryCommandHandler createCategoryCommandHandler;
    private final UpdateCategoryCommandHandler updateCategoryCommandHandler;
    private final DeleteCategoryCommandHandler deleteCategoryCommandHandler;

    @PostMapping("/create")
    public ResponseEntity<RestData<?>> createCategory(@RequestBody CategoryRequest request) {
        var command = new CreateCategoryCommand(request);
        var category = createCategoryCommandHandler.handle(command);
        return VsResponseUtil.ok(HttpStatus.CREATED, category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<RestData<?>> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest request) {
        var command = new UpdateCategoryCommand(categoryId, request);
        var category = updateCategoryCommandHandler.handle(command);
        return VsResponseUtil.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<RestData<?>> deleteCategory(@PathVariable Long categoryId) {
        var command = new DeleteCategoryCommand(categoryId);
        deleteCategoryCommandHandler.handle(command);
        return VsResponseUtil.ok("Category deleted successfully.");
    }
}
