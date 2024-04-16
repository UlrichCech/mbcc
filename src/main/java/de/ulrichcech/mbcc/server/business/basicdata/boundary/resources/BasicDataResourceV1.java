package de.ulrichcech.mbcc.server.business.basicdata.boundary.resources;

import de.ulrichcech.mbcc.server.business.basicdata.boundary.command.*;
import de.ulrichcech.mbcc.server.business.car.entity.*;
import de.ulrichcech.mbcc.server.platform.rest.APIException;
import de.ulrichcech.mbcc.server.platform.rest.AbstractRestResource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.List;

import static de.ulrichcech.mbcc.server.platform.rest.AbstractRestResource.MEDIA_TYPE_JSON_STRING;

@Path("/")
@RequestScoped
@Consumes(MEDIA_TYPE_JSON_STRING)
@Produces(MEDIA_TYPE_JSON_STRING)
public class BasicDataResourceV1 extends AbstractRestResource {

    @GET
    @Path("v1/cars/configurations/basic/cartypes")
    @Metered(name = "getAllAvailableCarTypesMetered")
    @Timed(name = "getAllAvailableCarTypesTimed")
    public Response getAllAvailableCarTypes() {
        try {
            final List<CarType> carTypeList = createCommand(CommandGetAllAvailableTypes.class).execute();
            return Response
                    .ok(ExternalBasicCarType.createList(carTypeList))
                    .build();
        } catch (APIException apiex) {
            return Response.noContent().build();
        }
    }


    @GET
    @Path("v1/cars/configurations/basic/carclasses")
    @Metered(name = "getAllAvailableCarClassesMetered")
    @Timed(name = "getAllAvailableCarClassesTimed")
    public Response getAllAvailableCarClasses() {
        try {
            final List<CarClass> carClassList = createCommand(CommandGetAllAvailableClasses.class).execute();
            return Response
                    .ok(ExternalBasicCarClass.createList(carClassList))
                    .build();
        } catch (APIException apiex) {
            return Response.noContent().build();
        }
    }


    @GET
    @Path("v1/cars/configurations/basic/colors")
//    @RolesAllowed({"ROLE_ALL_USERS", "ROLE_ADMIN"})
    @Metered(name = "getBasicColorsMetered")
    @Timed(name = "getBasicColorsTimed")
    public Response getAllAvailableColors() {
        try {
            final List<CarColor> carColorList = createCommand(CommandGetAllAvailableColors.class).execute();
            return Response
                    .ok(ExternalBasicColor.createList(carColorList))
                    .build();
        } catch (APIException apiex) {
            return Response.noContent().build();
        }
    }


    @GET
    @Path("v1/cars/configurations/basic/engines")
    @Metered(name = "getAllAvailableEnginesMetered")
    @Timed(name = "getAllAvailableEnginesTimed")
    public Response getAllAvailableEngines() {
        try {
            final List<CarEngine> carEngineList = createCommand(CommandGetAllAvailableEngines.class).execute();
            return Response
                    .ok(ExternalBasicEngine.createList(carEngineList))
                    .build();
        } catch (APIException apiex) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("v1/cars/configurations/basic/specialfeatures")
    @Metered(name = "getAllAvailableSpecialFeaturesMetered")
    @Timed(name = "getAllAvailableSpecialFeaturesTimed")
    public Response getAllAvailableSpecialFeatures() {
        try {
            final List<CarSpecialFeature> carSpecialFeatureList = createCommand(CommandGetAllAvailableSpecialFeatures.class).execute();
            return Response
                    .ok(ExternalBasicCarSpecialFeature.createList(carSpecialFeatureList))
                    .build();
        } catch (APIException apiex) {
            return Response.noContent().build();
        }
    }

}
