package com.mactwo.event;

import com.mactwo.event.base.EventBase;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PasswordResetOTPSentEvent extends EventBase {
    private Long userId;
    private String email;
    private String otp;
}
