package com.unitech.mapper;

import com.unitech.model.dto.RegisterUserDto;
import com.unitech.model.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RegisterMapper {

    RegisterUserDto toRegisterDto(RegisterRequest registerRequest);
}
