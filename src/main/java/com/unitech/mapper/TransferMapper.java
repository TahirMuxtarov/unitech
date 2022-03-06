package com.unitech.mapper;

import com.unitech.model.dto.TransferRequestDto;
import com.unitech.model.request.TransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransferMapper {

    TransferRequestDto toTransferRequestDto(TransferRequest request);
}
