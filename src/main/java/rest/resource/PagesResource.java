package rest.resource;

import rest.dao.VehicleDAO;
import rest.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Optional;

@Path("/pages")
public class PagesResource {
    private final VehicleDAO vehicleDAO = new VehicleDAO();

    @GET
    @Path("/get-by-id-form")
    public Response showGetByIdForm (@Context HttpServletRequest request,
                                     @Context HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/get-by-id.jsp").forward(request, response);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/add-vehicle-form")
    public Response showNewForm (@Context HttpServletRequest request,
                                 @Context HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add-vehicle-form.jsp").forward(request, response);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/edit-form")
    public Response showEditForm (@Context HttpServletRequest request,
                                  @Context HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Optional<Vehicle> existingVehicle = Optional.ofNullable(vehicleDAO.getVehicleForId(Long.parseLong(id)));
        request.setAttribute("vehicle", existingVehicle.get());
        request.getRequestDispatcher("/edit-vehicle-form.jsp").forward(request, response);
        return Response.status(Response.Status.OK).build();
    }
}