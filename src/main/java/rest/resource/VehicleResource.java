package rest.resource;

import rest.dto.VehicleDto;
import rest.model.Error;
import rest.service.VehicleService;
import rest.validator.ValidateFieldsException;
import rest.validator.VehicleValidator;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Path("/vehicles")
public class VehicleResource {
    private final VehicleService vehicleService = new VehicleService();
    private final VehicleValidator validator = new VehicleValidator();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVehicle (VehicleDto dto) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
            dto.setCreationDate(ZonedDateTime.now().format(formatter));
            validator.validate(dto);
            vehicleService.createVehicle(dto);
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (ValidateFieldsException ex) {
            List<Error> errors = ex.getErrorMsg();
            return sendError(errors);
        } catch (IllegalAccessException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Illegal Access").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVehicles (@Context HttpServletRequest request) {
        List<VehicleDto> dtos;
        if (request.getParameterMap().isEmpty())
            dtos = vehicleService.getAllVehicles();
         else {
            Map<String, String[]> queryMap = request.getParameterMap();
            dtos = vehicleService.filter(queryMap);
        }
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleById (@PathParam("id") String id) {
        try {
            long vehicleId = Long.parseLong(id);
            VehicleDto dto = vehicleService.getVehicleForId(vehicleId);
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("The following value can't be a number").build();
        } catch (EntityNotFoundException | NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Vehicle not found").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateVehicle (@PathParam("id") String id, VehicleDto dto) {
        try {
            validator.validate(dto);
            dto.setId(Long.parseLong(id));
            vehicleService.updateVehicle(dto);
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (ValidateFieldsException ex) {
            List<Error> errors = ex.getErrorMsg();
            return sendError(errors);
        } catch (IllegalAccessException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Illegal Access").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVehicle (@PathParam("id") String id) {
        try {
            vehicleService.deleteVehicle(Long.parseLong(id));
            return Response.status(Response.Status.OK).entity("Vehicle deleted").build();
        } catch (EntityNotFoundException | NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Vehicle not found").build();
        } catch (NumberFormatException nfe) {
            return Response.status(Response.Status.BAD_REQUEST).entity("The following value can't be a number").build();
        }
    }

    @GET
    @Path("/engine-power/sum")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPowerSum () {
        return Response.status(Response.Status.OK).entity(vehicleService.getPowerSum()).build();
    }

    @GET
    @Path("/fuel-type/unique")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFuelUnique () {
        return Response.status(Response.Status.OK).entity(vehicleService.getFuelUnique()).build();
    }

    @GET
    @Path("/name/min")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNameMin () {
        return Response.status(Response.Status.OK).entity(vehicleService.getNameMin()).build();
    }

    public Response sendError (List<Error> errors) {
        StringBuilder errorMsg = new StringBuilder();
        for (Error error: errors) {
            errorMsg.append(error.getName()).append(": ").append(error.getDesc()).append("\n");
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(errorMsg.toString()).build();
    }
}