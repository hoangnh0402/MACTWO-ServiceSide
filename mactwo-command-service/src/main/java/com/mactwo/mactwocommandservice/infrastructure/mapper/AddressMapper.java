package com.mactwo.command.infrastructure.mapper;

import com.mactwo.command.domain.model.Address;
import com.mactwo.document.dto.request.address.AddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Address toAddress(AddressRequest request);
}