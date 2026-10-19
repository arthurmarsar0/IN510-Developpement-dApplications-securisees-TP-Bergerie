package fr.esisar.bergerie.mouton;

import java.util.List;
import java.time.LocalDate;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import fr.esisar.bergerie.cloche.*;

@Path("/moutons")
@Consumes(MediaType.APPLICATION_JSON)
public class MoutonResource{
	@Inject
	MoutonMapper moutonMapper;
	
	@GET
	public Response getAllMoutons() {
		List<Mouton> moutons = Mouton.listAll();
		
		List<MoutonResponseDTO> result = moutons.stream().map(moutonMapper :: toResponseDTO).toList();
		
		return Response.ok(result).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getMouton(@PathParam("id") Long id) {
		Mouton mouton = Mouton.findById(id);
		if (mouton == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(moutonMapper.toResponseDTO(mouton)).build();
	}
	
	@GET
	@Path("/nes-apres")
	public Response getMoutonsByYear(@QueryParam("date") LocalDate date) {
		List<Mouton> moutons = Mouton.listAll();

	    List<Mouton> filtered = moutons.stream()
	        .filter(m -> m.dateNaissance.isAfter(date))
	        .toList();

	    return Response.ok(filtered).build();
	}
	
	@POST
	@Transactional
	public Response createMouton(CreateMoutonDTO dto) {
		Mouton mouton = moutonMapper.toEntity(dto);
		mouton.persist();
		
		return Response.status(Status.CREATED).entity(moutonMapper.toResponseDTO(mouton)).build();
	}
	
	@PATCH
	@Path("/{id}")
	@Transactional
	public Response updateMouton(@PathParam("id") Long id, PatchMoutonDTO dto) {
		Mouton mouton = Mouton.findById(id);
		if(mouton == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		moutonMapper.patchEntity(dto, mouton);
		
		return Response.ok(moutonMapper.toResponseDTO(mouton)).build();
	}
	
	@DELETE
	@Transactional
	@Path("/{id}")
	public Response deleteMouton(Long id) {
		Mouton mouton = Mouton.findById(id);
		
		if (mouton == null) return Response.status(Status.NOT_FOUND).build();
		
		mouton.delete();
		
		return Response.noContent().build();
		
	}
	
	// CLOCHE MANAGER -- Q3.1
	
	@Inject
	ClocheMapper clocheMapper;
	
	@GET
	@Path("/sans-cloche")
	public Response getMoutonsWithoutCloche() {
		List<Mouton> moutons = Mouton.list("cloche is null");
		
		List<MoutonResponseDTO> result = moutons.stream().map(moutonMapper :: toResponseDTO).toList();
	
		return Response.ok(result).build();
		
	}
	
	@GET
	@Path("/{moutonId}/cloche")
	public Response getClocheByMouton(@PathParam("moutonId") Long moutonId) {
		
		Mouton mouton = Mouton.findById(moutonId);
		if(mouton == null) return Response.status(Status.NOT_FOUND).build();
		if(mouton.cloche == null) return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(clocheMapper.toResponseDTO(mouton.cloche)).build();
	}
	
	@PUT
	@Path("/{moutonId}/cloche/{clocheId}")
	@Transactional
	public Response associateCloche(@PathParam("moutonId") Long moutonId, 
									@PathParam("clocheId") Long clocheId) {
		
		Mouton mouton = Mouton.findById(moutonId);
		Cloche cloche = Cloche.findById(clocheId);
		
		if(mouton == null || cloche == null) return Response.status(Status.NOT_FOUND).build();
		
		mouton.cloche = cloche;
		cloche.mouton = mouton;
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{moutonId}/cloche")
	@Transactional
	public Response removeCloche(@PathParam("moutonId") Long moutonId) {
		
		Mouton mouton = Mouton.findById(moutonId);
		if(mouton == null) return Response.status(Status.NOT_FOUND).build();
		if(mouton.cloche == null) return Response.noContent().build();
		
		Cloche cloche = mouton.cloche;
		
		mouton.cloche = null;
		cloche.mouton = null;
		
		return Response.noContent().build();
		
	}
}