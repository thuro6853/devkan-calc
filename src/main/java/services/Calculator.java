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
    public String add(@QueryParam("a")int a, @QueryParam("b")int b){
        return String.valueOf(a + b);
    }
    
    @GET
    @Path("subtract")
    public String subtract(@QueryParam("a")int a, @QueryParam("b")int b){
        return String.valueOf(a - b);
    }
    
    @GET
    @Path("multiply")
    public String multiply(@QueryParam("a")int a, @QueryParam("b")int b){
        return String.valueOf(a * b);
    }
    
    @GET
    @Path("divide")
    public String divide(@QueryParam("a")int a, @QueryParam("b")int b){
        return String.valueOf(a / b);
    }
}
