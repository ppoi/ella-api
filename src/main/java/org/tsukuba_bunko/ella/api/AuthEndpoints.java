package org.tsukuba_bunko.ella.api;

import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthEndpoints {

    @Path("/store")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response store(Map<String, String> token) {
        String refreshToken = token.remove("refresh_token");
        NewCookie hiddenStore = new NewCookie.Builder("rt")
            .httpOnly(true)
            .secure(true)
            .path("/auth")
            .value(refreshToken)
            .build();
        return Response.ok(token).cookie(hiddenStore).build();
    }


}
