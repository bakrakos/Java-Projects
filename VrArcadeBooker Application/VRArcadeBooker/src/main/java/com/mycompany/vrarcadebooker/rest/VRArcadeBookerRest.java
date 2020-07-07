package com.mycompany.vrarcadebooker.rest;

import com.google.gson.Gson;
import com.mycompany.vrarcadebooker.entity.VrBooker;
import com.mycompany.vrarcadebooker.repositories.VRArcadeBookerRepository;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

/**
 * VR BOOKING WEB REST APPLICATION INFORMATION
 *
 * @Since 20200620
 * @author Omars
 */

@Path("/VRArcadeBookerService/VrBooker")
public class VRArcadeBookerRest {

    private static Map<Integer, VrBooker> DB = new HashMap<>();
    private final VRArcadeBookerRepository br;

    @Autowired
    public VRArcadeBookerRest(VRArcadeBookerRepository br) {
        this.br = br;
    }

    /**
     * GET request to retrieve source information
     *
     * @Since 20200620
     * @author Omars
     */
    @GET
    @Produces("application/json")
    public ArrayList<VrBooker> getAllVrBookings() {
        ArrayList<VrBooker> vrbookers = (ArrayList<VrBooker>) br.findAll();
        return vrbookers;
    }

    /**
     * GET request to retrieve source information
     *
     * @Since 20200620
     * @author Omars
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") int id) throws URISyntaxException {

        Optional<VrBooker> vrbooker = br.findById(id);

        if (vrbooker == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(vrbooker).build();
    }

    /**
     * Allows to create a booking
     *
     * @author BJ
     * @since 20200611
     *
     * @Modified - Allows to create VR Booking using POST service for Sprint 4
     * @by OB
     * @since 20200624  
     * 
     */
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    public Response createVrBooking(String vrBookerJson) {

        Gson gson = new Gson();
        VrBooker vrbooker = gson.fromJson(vrBookerJson, VrBooker.class);

        if (vrbooker.getName1() == null || vrbooker.getName1().isEmpty()) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        if (vrbooker.getId() == null) {
            vrbooker.setId(0);
        }

        vrbooker = br.save(vrbooker);

        String temp = "";
        temp = gson.toJson(vrbooker);

        return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();

    }
    
    /**
     * DELETE request to DELETE source information 
     *
     * @Since 20200620
     * @author Omars
     */
    
    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") int id) throws URISyntaxException {
        Optional<VrBooker> vrbooker = br.findById(id);
        if (vrbooker != null) {
            br.deleteById(id);
            return Response.status(HttpURLConnection.HTTP_CREATED).build();
        }

        return Response.status(404).build();
    }
    
    /**
     * PUT request to update source information 
     *
     * @Since 20200620
     * @author Omars
     */
    
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateBooking(@PathParam("id") int id, String vrbookingJson) throws URISyntaxException {

        Gson gson = new Gson();
        VrBooker vrbooker = gson.fromJson(vrbookingJson, VrBooker.class);

        if (vrbooker.getName1() == null || vrbooker.getName1().isEmpty()) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }

        if (vrbooker.getId() == null) {
            vrbooker.setId(0);
        }

        vrbooker = br.save(vrbooker);
//        BookingDAO bookingDAO = new BookingDAO();
//        bookingDAO.insert(booking);

        String temp = "";
        temp = gson.toJson(vrbooker);

        return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
    }
}
