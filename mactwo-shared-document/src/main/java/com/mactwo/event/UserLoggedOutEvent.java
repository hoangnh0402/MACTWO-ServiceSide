package com.mactwo.event;

import com.mactwo.event.base.EventBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLoggedOutEvent extends EventBase {
    private Long userId;
    private String email;
}