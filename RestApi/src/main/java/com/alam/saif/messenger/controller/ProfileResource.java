package com.alam.saif.messenger.controller;

import com.alam.saif.messenger.controller.beans.FilteringBean;
import com.alam.saif.messenger.model.Profile;
import com.alam.saif.messenger.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by BS102-Saif on 3/4/2016.
 */
@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private ProfileService profileService= new ProfileService();

    @GET
    public List<Profile> getProfiles(@BeanParam FilteringBean bean) {

        if(bean.getYear() > 0) {
            return profileService.getAllProfilesForYear(bean.getYear());
        }
        if(bean.getmYear() > 0) {
            return profileService.getAllProfilesForYear(bean.getmYear());
        }

        if(bean.getStart() >= 0 && bean.getSize() > 0) {
            return profileService.getAllProfilesPaginated(bean.getStart(), bean.getSize());
        }

        if(bean.getmStart() >= 0 && bean.getmSize() > 0) {
            return profileService.getAllProfilesPaginated(bean.getmStart(), bean.getmSize());
        }
        return profileService.getAllProfiles();
    }


    @POST
    public Response createProfile(@Context UriInfo uriInfo, Profile profile) {
        Profile responseProfile = profileService.createProfile(profile);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(profile.getProfileName()).build())
                        .entity(responseProfile)
                        .build();
    }


    @GET
    @Path("/{profileName}")
    public Response getProfile(@PathParam("profileName") String name) {
        Profile responseProfile = profileService.getProfileByName(name);
        return Response.status(Response.Status.OK)
                       .entity(responseProfile)
                       .build();
    }


    @PUT
    @Path("/{profileName}")
    public Response updateProfile(@PathParam("profileName") String name, Profile profile) {
        Profile responseProfile = profileService.updateProfile(profile, name);
        return Response.status(Response.Status.ACCEPTED)
                .entity(responseProfile)
                .build();
    }


    @DELETE
    @Path("/{profileName}")
    public void deleteProfile(@PathParam("profileName") String name) {
        profileService.deleteProfile(name);

    }


}
