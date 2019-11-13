package demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/secured")
public class UserResource {

    @GET
    @Path("message")
    @Produces("application/json")
    public String getAllUsers() {

        System.out.println("getall users..............");
        return "Hello Chaitali";
    }
}
