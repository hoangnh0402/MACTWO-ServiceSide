package com.mactwo.query.listener;

import com.mactwo.document.constant.TopicConstant;
import com.mactwo.document.event.product.ProductCreatedEvent;
import com.mactwo.document.event.product.ProductDeletedEvent;
import com.mactwo.document.event.product.ProductUpdatedEvent;
import com.mactwo.document.event.product.StockUpdatedEvent;
import com.mactwo.query.document.ProductDocument;
import com.mactwo.query.repository.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductEventListener {

    private final ProductSearchRepository productSearchRepository;

    @KafkaListener(topics = TopicConstant.ProductTopic.PRODUCT_CREATED, groupId = "query-service-group")
    public void handleProductCreated(ProductCreatedEvent event) {
        log.info("Received ProductCreatedEvent for product ID: {}", event.getProductId());
        ProductDocument doc = mapEventToDocument(event);
        productSearchRepository.save(doc);
        log.info("Saved product {} to Elasticsearch", doc.getProductId());
    }

    @KafkaListener(topics = TopicConstant.ProductTopic.PRODUCT_UPDATED, groupId = "query-service-group")
    public void handleProductUpdated(ProductUpdatedEvent event) {
        log.info("Received ProductUpdatedEvent for product ID: {}", event.getProductId());
        // Để đơn giản và đảm bảo tính nhất quán, chúng ta sẽ ghi đè lại toàn bộ document
        ProductDocument doc = mapEventToDocument(event);
        productSearchRepository.save(doc);
        log.info("Updated product {} in Elasticsearch", doc.getProductId());
    }

    @KafkaListener(topics = TopicConstant.ProductTopic.STOCK_UPDATED, groupId = "query-service-group")
    public void handleStockUpdated(StockUpdatedEvent event) {
        log.info("Received StockUpdatedEvent for variant ID: {}", event.getVariantId());
        productSearchRepository.findByVariants_VariantId(event.getVariantId()).ifPresent(doc -> {
            doc.getVariants().forEach(variant -> {
                if (variant.getVariantId().equals(event.getVariantId())) {
                    variant.setStockQuantity(event.getNewStockQuantity());
                }
            });
            productSearchRepository.save(doc);
            log.info("Updated stock for variant {} in Elasticsearch", event.getVariantId());
        });
    }

    @KafkaListener(topics = TopicConstant.ProductTopic.PRODUCT_DELETED, groupId = "query-service-group")
    public void handleProductDeleted(ProductDeletedEvent event) {
        log.info("Received ProductDeletedEvent for product ID: {}", event.getProductId());
        productSearchRepository.deleteById(event.getProductId());
        log.info("Deleted product {} from Elasticsearch", event.getProductId());
    }

    // --- Helper Methods ---

    private ProductDocument mapEventToDocument(ProductCreatedEvent event) {
        ProductDocument doc = new ProductDocument();
        doc.setProductId(event.getProductId());
        doc.setName(event.getName());
        doc.setBrand(event.getBrand());
        doc.setDescription(event.getDescription());
        doc.setCategoryId(event.getCategoryId());
        doc.setImageUrl(event.getImageUrl());

        List<ProductDocument.Variant> variants = event.getVariants().stream().map(v -> {
            ProductDocument.Variant variantDoc = new ProductDocument.Variant();
            variantDoc.setVariantId(v.getVariantId());
            variantDoc.setColor(v.getColor());
            variantDoc.setStorage(v.getStorage());
            variantDoc.setRam(v.getRam());
            variantDoc.setPrice(v.getPrice());
            variantDoc.setStockQuantity(v.getStockQuantity());
            variantDoc.setImageUrls(v.getImageUrls());
            return variantDoc;
        }).collect(Collectors.toList());
        doc.setVariants(variants);
        return doc;
    }

    private ProductDocument mapEventToDocument(ProductUpdatedEvent event) {
        // Logic tương tự như trên, chỉ khác kiểu event đầu vào
        ProductDocument doc = new ProductDocument();
        doc.setProductId(event.getProductId());
        doc.setName(event.getName());
        doc.setBrand(event.getBrand());
        doc.setDescription(event.getDescription());
        doc.setCategoryId(event.getCategoryId());
        doc.setImageUrl(event.getImageUrl());

        List<ProductDocument.Variant> variants = event.getVariants().stream().map(v -> {
            ProductDocument.Variant variantDoc = new ProductDocument.Variant();
            variantDoc.setVariantId(v.getVariantId());
            variantDoc.setColor(v.getColor());
            variantDoc.setStorage(v.getStorage());
            variantDoc.setRam(v.getRam());
            variantDoc.setPrice(v.getPrice());
            variantDoc.setStockQuantity(v.getStockQuantity());
            variantDoc.setImageUrls(v.getImageUrls());
            return variantDoc;
        }).collect(Collectors.toList());
        doc.setVariants(variants);
        return doc;
    }
}
