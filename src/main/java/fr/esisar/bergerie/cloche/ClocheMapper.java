package fr.esisar.bergerie.cloche;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface ClocheMapper {

    ClocheResponseDTO toResponseDTO(Cloche cloche);
}