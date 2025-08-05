package com.mactwo.event;

import com.mactwo.event.base.EventBase;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryUpdatedEvent extends EventBase {
    private Long categoryId;
    private String categoryName;
    private Long parentId;
}
