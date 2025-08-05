package com.mactwo.mactwocommandservice.application.command.handler.category;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.CategoryCreatedEvent;
import com.mactwo.mactwocommandservice.application.command.command.category.CreateCategoryCommand;
import com.mactwo.mactwocommandservice.domain.model.Category;
import com.mactwo.mactwocommandservice.domain.repository.CategoryRepository;
import com.mactwo.mactwocommandservice.infrastructure.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCategoryCommandHandler {
    private final CategoryRepository categoryRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CategoryMapper categoryMapper; // Cập nhật

    @Transactional
    public Category handle(CreateCategoryCommand command) {
        Category category = categoryMapper.toCategory(command); // Cập nhật

        if (command.getParentId() != null) {
            Category parent = categoryRepository.findById(command.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        Category savedCategory = categoryRepository.save(category);

        var event = new CategoryCreatedEvent(
                savedCategory.getId(),
                savedCategory.getCategoryName(),
                savedCategory.getParent() != null ? savedCategory.getParent().getId() : null
        );
        kafkaTemplate.send(TopicConstant.CategoryTopic.CATEGORY_CREATED, event);

        return savedCategory;
    }
}
