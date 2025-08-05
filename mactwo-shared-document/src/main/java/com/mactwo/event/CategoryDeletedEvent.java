package com.mactwo.event;

import com.mactwo.event.base.EventBase;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDeletedEvent extends EventBase {
    private Long categoryId;
}
