package com.mactwo.mactwocommandservice.application.command.handler.category;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.CategoryDeletedEvent;
import com.mactwo.mactwocommandservice.application.command.command.category.DeleteCategoryCommand;
import com.mactwo.mactwocommandservice.domain.model.Category;
import com.mactwo.mactwocommandservice.domain.repository.CategoryRepository;
import com.mactwo.mactwocommandservice.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCategoryCommandHandler {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void handle(DeleteCategoryCommand command) {
        // CẬP NHẬT: Lấy entity để kiểm tra
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Kiểm tra xem có danh mục con không
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            throw new IllegalStateException("Cannot delete category: It has sub-categories.");
        }

        if (productRepository.existsByCategoryId(command.getCategoryId())) {
            throw new IllegalStateException("Cannot delete category: It contains products.");
        }

        categoryRepository.deleteById(command.getCategoryId());

        var event = new CategoryDeletedEvent(command.getCategoryId());
        kafkaTemplate.send(TopicConstant.CategoryTopic.CATEGORY_DELETED, event);
    }
}