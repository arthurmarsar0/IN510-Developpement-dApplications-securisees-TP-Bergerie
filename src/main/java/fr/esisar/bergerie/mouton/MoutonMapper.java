package fr.esisar.bergerie.mouton;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface MoutonMapper {

	MoutonResponseDTO toResponseDTO(Mouton mouton);
	
	@Mapping(target = "id", ignore = true)
	Mouton toEntity(CreateMoutonDTO dto);
	
	@Mapping(target = "id", ignore = true)
	void patchEntity(PatchMoutonDTO dto, @MappingTarget Mouton mouton);
}
