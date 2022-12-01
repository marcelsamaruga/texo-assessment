package com.texo.assessment.util;

import com.texo.assessment.dto.WinnerDto;
import com.texo.assessment.dto.WinnerProjectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WinnerConverter {

    WinnerConverter INSTANCE = Mappers.getMapper(WinnerConverter.class);

    @ValueMapping(source = "UNRECOGNIZED", target = MappingConstants.NULL)
    @Mapping(target = "interval", source = "gap")
    WinnerDto toWinnerDto(WinnerProjectionDto winnerProjectionDto);
}
