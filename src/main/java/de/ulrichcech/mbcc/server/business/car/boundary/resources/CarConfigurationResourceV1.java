package de.ulrichcech.mbcc.server.business.car.boundary.resources;

import de.ulrichcech.mbcc.server.business.car.boundary.command.*;
import de.ulrichcech.mbcc.server.platform.rest.AbstractRestResource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.List;

import static de.ulrichcech.mbcc.server.platform.rest.AbstractRestResource.MEDIA_TYPE_JSON_STRING;

@Path("/")
@RequestScoped
@Consumes(MEDIA_TYPE_JSON_STRING)
@Produces(MEDIA_TYPE_JSON_STRING)
public class CarConfigurationResourceV1 extends AbstractRestResource {

    @POST
    @Path("v1/cars/configurations")
    @Metered(name = "createNewConfigurationMetered")
    @Timed(name = "createNewConfigurationTimed")
    public Response createNewConfiguration(NewCarConfigurationRequest request) {
        final ExternalCarConfiguration carConfiguration = createCommand(CommandCreateNewCarConfiguration.class).execute(request);
        return Response
                .ok(carConfiguration)
                .build();
    }


    @GET
    @Path("v1/cars/configurations/{configId}/")
    @Metered(name = "fetchConfigurationMetered")
    @Timed(name = "fetchConfigurationTimed")
    public Response fetchConfiguration(@PathParam("configId") String configId) {
        final ExternalCarConfiguration carConfiguration = createCommand(CommandFetchCarConfiguration.class).execute(configId);
        return Response
                .ok(carConfiguration)
                .build();
    }

    @GET
    @Path("v1/cars/configurations/users/{userId}/")
    @Metered(name = "fetchConfigurationByUserMetered")
    @Timed(name = "fetchConfigurationByUserTimed")
    public Response fetchConfigurationByUser(@PathParam("userId") String userId) {
        final List<ExternalCarConfiguration> carConfiguration = createCommand(CommandFetchCarConfiguration.class).executeByUser(userId);
        return Response
                .ok(carConfiguration)
                .build();
    }



    @PUT
    @Path("v1/cars/configurations/{configId}/")
    @Metered(name = "updateConfigurationMetered")
    @Timed(name = "updateConfigurationTimed")
    public Response updateConfiguration(@PathParam("configId") String configId, UpdateCarConfigurationRequest request) {
        // TODO: check if configId from path matches configurationId from request
        final ExternalCarConfiguration carConfiguration = createCommand(CommandUpdateCarConfiguration.class).execute(request);
        return Response
                .ok(carConfiguration)
                .build();
    }

    @DELETE
    @Path("v1/cars/configurations/{configId}/")
    @Metered(name = "deleteConfigurationMetered")
    @Timed(name = "deleteConfigurationTimed")
    public Response daleteConfiguration(@PathParam("configId") String configId) {
        createCommand(CommandDeleteCarConfiguration.class).execute(configId);
        return Response
                .ok()
                .build();
    }

}
