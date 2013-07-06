package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/calc")
@Produces(MediaType.TEXT_PLAIN)
public class Calculator {

    @GET
    @Path("add")
    public String add(@QueryParam("a") int a, @QueryParam("b") int b) {
        long actual = (long) a + b;
        if (actual > Integer.MAX_VALUE || actual < Integer.MIN_VALUE) {
            String msg = String.format("a: %d, b: %d", a, b);
            throw new IllegalArgumentException(msg);
        }
        return Long.toString(actual);
    }
}
