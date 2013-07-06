package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;

@Path("/calc")
@Produces(MediaType.TEXT_PLAIN)
public class Calculator {

    @GET
    @Path("add")
    public String add(@QueryParam("a") int a, @QueryParam("b") int b) {
        BigInteger actual = BigInteger.valueOf(a).add(BigInteger.valueOf(b));
        if (actual.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
                || actual.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            String msg = String.format("a: %d, b: %d", a, b);
            throw new IllegalArgumentException(msg);
        }
        return actual.toString();
    }

    @GET
    @Path("subtract")
    public String subtract(@QueryParam("a") int a, @QueryParam("b") int b) {
        BigInteger actual = BigInteger.valueOf(a).subtract(BigInteger.valueOf(b));
        if (actual.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
                || actual.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            String msg = String.format("a: %d, b: %d", a, b);
            throw new IllegalArgumentException(msg);
        }
        return actual.toString();
    }
}
