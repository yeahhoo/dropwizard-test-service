package potato.resources;

import com.google.common.collect.ImmutableList;
import potato.api.PotatoBag;
import potato.db.Storage;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/potatobags")
@Produces(MediaType.APPLICATION_JSON)
public class PotatoStockResource {
    private final Integer defaultBugsNumber;


    public PotatoStockResource(Integer defaultBugsNumber) {
        this.defaultBugsNumber = defaultBugsNumber;
    }

    /**
     * Inits data for testing.
     *
     * @return list of the created bags.
     */
    @POST
    @Path("/init")
    public List<PotatoBag> initStorage() {
        ImmutableList.Builder<PotatoBag> bagsBuilder = ImmutableList.builder();
        PotatoBag bag1 = new PotatoBag(null, 2, PotatoBag.Supplier.OWEL, new Date(), 4);
        PotatoBag bag2 = new PotatoBag(null, 4, PotatoBag.Supplier.POTATAS_RUBEN, new Date(), 5);
        PotatoBag bag3 = new PotatoBag(null, 9, PotatoBag.Supplier.YUNNAN_SPICES, new Date(), 2);
        bagsBuilder.add(Storage.getInstance().putBug(bag1));
        bagsBuilder.add(Storage.getInstance().putBug(bag2));
        bagsBuilder.add(Storage.getInstance().putBug(bag3));
        return bagsBuilder.build();
    }

    @GET
    @Path("list")
    public List<PotatoBag> getThreeBags() {
        return Storage.getInstance().getBagsWithLimit(defaultBugsNumber);
    }

    @GET
    @Path("list/{limit}")
    public List<PotatoBag> getBags(@PathParam("limit") Integer bagsLimit) {
        return Storage.getInstance().getBagsWithLimit(bagsLimit);
    }

    @GET
    @Path("{id}")
    public PotatoBag getBags(@PathParam("id") String id) {
        return Storage.getInstance().getBag(id);
    }

    @PUT
    public PotatoBag replace(@NotNull @Valid PotatoBag potatoBag) {
        return Storage.getInstance().updateBug(potatoBag);
    }

    @POST
    public PotatoBag add(@NotNull @Valid PotatoBag potatoBag) {
        return Storage.getInstance().putBug(potatoBag);
    }
}