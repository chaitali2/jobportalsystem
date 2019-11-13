package demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/item")
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    @GET
    @Path("itemdetail")
    @Produces("application/json")
    public String getItems() {
//        return itemDao.getItems();
        return "item detail";
    }

    @POST
    @Path("addItemDetail")
    @Produces("application/json")
    public void addItem(Item item) {
        itemDao.addItem(item);
    }

    @GET
    @Path("deleteItemDetail")
    @Produces("application/json")
    public void removeItem() throws Exception {
        itemDao.removeItem();
    }

}
