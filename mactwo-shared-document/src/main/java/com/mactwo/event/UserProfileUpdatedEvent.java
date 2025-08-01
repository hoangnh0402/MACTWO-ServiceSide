package com.mactwo.event;

import com.mactwo.event.base.EventBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProfileUpdatedEvent extends EventBase {
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
}
