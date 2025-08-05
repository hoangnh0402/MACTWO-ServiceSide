package com.mactwo.mactwocommandservice.application.command.handler.product;

import com.mactwo.contants.TopicConstant;
import com.mactwo.event.ProductUpdatedEvent;
import com.mactwo.mactwocommandservice.application.command.command.product.UpdateProductCommand;
import com.mactwo.mactwocommandservice.domain.model.Category;
import com.mactwo.mactwocommandservice.domain.model.Product;
import com.mactwo.mactwocommandservice.domain.model.ProductVariant;
import com.mactwo.mactwocommandservice.domain.repository.CategoryRepository;
import com.mactwo.mactwocommandservice.domain.repository.ProductRepository;
import com.mactwo.mactwocommandservice.infrastructure.mapper.ProductEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateProductCommandHandler {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductEventMapper productEventMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Product handle(UpdateProductCommand command) {
        Product product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(command.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(command.getName());
        product.setDescription(command.getDescription());
        product.setCategory(category);
        product.setImageUrl(command.getImageUrl()); // Cập nhật ảnh

        var updatedVariants = command.getVariants().stream().map(dto -> {
            ProductVariant variant = product.getVariants().stream()
                    .filter(v -> v.getId() != null && v.getId().equals(dto.getId()))
                    .findFirst()
                    .orElse(new ProductVariant());
            variant.setColor(dto.getColor());
            variant.setStorage(dto.getStorage());
            variant.setRam(dto.getRam());
            variant.setPrice(dto.getPrice());
            variant.setStockQuantity(dto.getStockQuantity());
            variant.setImageUrls(dto.getImageUrls()); // Cập nhật danh sách ảnh
            variant.setProduct(product);
            return variant;
        }).collect(Collectors.toList());

        product.getVariants().clear();
        product.getVariants().addAll(updatedVariants);

        Product savedProduct = productRepository.save(product);

        ProductUpdatedEvent event = productEventMapper.toProductUpdatedEvent(savedProduct);
        kafkaTemplate.send(TopicConstant.ProductTopic.PRODUCT_UPDATED, event);

        return savedProduct;
    }
}
