
package se.thematrix.infinispan;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.infinispan.Cache;

import se.thematrix.infinispan.cache.CacheContainerFactory;
import se.thematrix.infinispan.cache.CachedVehicle;

@Path("/vehicle")
public class InfinispanRestlet {
	private final static Cache<String, CachedVehicle> cache = CacheContainerFactory.getCache("vehicleCache");
    
    @PUT
    @Produces("text/plain")
    public Response storeVehicle(@QueryParam("vin") String vin, @QueryParam("license") String license) {
    	if(checkInput(vin) && checkInput(license)){
        	CachedVehicle vehicle = new CachedVehicle(vin, license);
        	cache.put(vin, vehicle);
        	
        	return Response.ok(vehicle.toString()).build();
    	}
    	
    	return Response.status(Status.NOT_MODIFIED).build();
    }
    
    @GET
    @Produces("text/plain")
    public Response retreiveVehicle(@QueryParam("vin") String vin) {
    	CachedVehicle vehicle = null;
    	if(checkInput(vin)) {
        	vehicle = cache.get(vin);
    	}    	
    	if(vehicle != null){
    		return Response.ok(vehicle.toString()).build();
    	}
    	
    	return Response.status(Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteVehicle(@QueryParam("vin") String vin){
    	if(checkInput(vin)){
    		cache.remove(vin);
    		return Response.ok("removed vehicle with vin: " + vin).build();
    	}
    	return Response.status(Status.NOT_MODIFIED).build();
    }
    
    private Boolean checkInput(String input){
    	if(input != null && !input.equals("")){
    		return true;
    	}
    	return false;
    }
}