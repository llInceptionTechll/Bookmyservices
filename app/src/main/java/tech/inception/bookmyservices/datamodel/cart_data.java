package tech.inception.bookmyservices.datamodel;

/**
 * Created by ghumman on 3/13/2017.
 */

public class cart_data {

    String id , name , qty , price , category , main_service ;

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getQty() {
        return qty;
    }

    public void setMain_service(String main_service) {
        this.main_service = main_service;


    }

    public String getMain_service() {
        return main_service;
    }
}
