package com.mactwo.command.application.controller;

import com.mactwo.command.application.base.RestApiV1;
import com.mactwo.command.application.base.RestData;
import com.mactwo.command.application.base.VsResponseUtil;
import com.mactwo.command.application.command.promotion.CreatePromotionCommand;
import com.mactwo.command.application.command.promotion.DeletePromotionCommand;
import com.mactwo.command.application.command.promotion.UpdatePromotionCommand;
import com.mactwo.command.application.command.handler.promotion.CreatePromotionCommandHandler;
import com.mactwo.command.application.command.handler.promotion.DeletePromotionCommandHandler;
import com.mactwo.command.application.command.handler.promotion.UpdatePromotionCommandHandler;
import com.mactwo.document.dto.request.promotion.PromotionRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
@RequestMapping("/admin/promotions")
@Tag(name = "9. Promotion Management (Admin)")
@RequiredArgsConstructor
public class PromotionController {

    private final CreatePromotionCommandHandler createPromotionCommandHandler;
    private final UpdatePromotionCommandHandler updatePromotionCommandHandler;
    private final DeletePromotionCommandHandler deletePromotionCommandHandler;

    @PostMapping
    public ResponseEntity<RestData<?>> createPromotion(@RequestBody PromotionRequest request) {
        var command = new CreatePromotionCommand(request);
        var promotion = createPromotionCommandHandler.handle(command);
        return VsResponseUtil.ok(HttpStatus.CREATED, promotion);
    }

    @PutMapping("/{promotionId}")
    public ResponseEntity<RestData<?>> updatePromotion(@PathVariable Long promotionId, @RequestBody PromotionRequest request) {
        var command = new UpdatePromotionCommand(promotionId, request);
        var promotion = updatePromotionCommandHandler.handle(command);
        return VsResponseUtil.ok(promotion);
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<RestData<?>> deletePromotion(@PathVariable Long promotionId) {
        var command = new DeletePromotionCommand(promotionId);
        deletePromotionCommandHandler.handle(command);
        return VsResponseUtil.ok("Promotion deleted successfully.");
    }
}
