package com.mactwo.mactwocommandservice.application.command.handler.product;

import com.mactwo.contants.TopicConstant;
import com.mactwo.dto.response.ProductResponse;
import com.mactwo.event.ProductCreatedEvent;
import com.mactwo.mactwocommandservice.application.command.command.product.CreateProductCommand;
import com.mactwo.mactwocommandservice.domain.model.Category;
import com.mactwo.mactwocommandservice.domain.model.Product;
import com.mactwo.mactwocommandservice.domain.model.ProductVariant;
import com.mactwo.mactwocommandservice.domain.repository.CategoryRepository;
import com.mactwo.mactwocommandservice.domain.repository.ProductRepository;
import com.mactwo.mactwocommandservice.infrastructure.mapper.ProductEventMapper;
import com.mactwo.mactwocommandservice.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProductCommandHandler {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductEventMapper productEventMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public ProductResponse handle(CreateProductCommand command) {
        // 1. Tìm category
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 2. Tạo mới product
        Product product = new Product();
        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setImageUrl(command.getImageUrl());
        product.setCategory(category);

        // 3. Gán variant
        List<ProductVariant> variants = productMapper.toProductVariantList(command.getVariants());
        variants.forEach(variant -> variant.setProduct(product));
        product.setVariants(variants);

        // 4. Lưu DB
        Product savedProduct = productRepository.save(product);

        // 5. Gửi Kafka Event
        ProductCreatedEvent event = productEventMapper.toProductCreatedEvent(savedProduct);
        kafkaTemplate.send(TopicConstant.ProductTopic.PRODUCT_CREATED, event);

        // 6. Trả về DTO response
        return productMapper.toProductResponse(savedProduct);
    }
}
