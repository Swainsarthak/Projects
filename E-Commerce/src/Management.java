import java.util.*;

public class Management {
    private HashMap<Integer,Product> product_to_Show;
    private Category root_of_the_site;
    private Queue<List<Product>> the_cart;
    public HashMap<Integer, Product> getProduct_to_Show() {
        return product_to_Show;
    }
    public Category getRoot_of_the_site() {
        return root_of_the_site;
    }
    public Management(){
        this.root_of_the_site=new Category("root");
        this.product_to_Show=new HashMap<>();
        this.the_cart=new LinkedList<>();
    }
    public void add_product_to_the_category(Product pr,Category cat_type){
        product_to_Show.put(pr.getId(),pr);
        cat_type.getProducts_to_be_added().add(pr);
    }
    public List<Product> to_show_product_details(String product_name){
        List<Product> product_available=new ArrayList<>();
        for(Product we_have:product_to_Show.values()){
            if(we_have.getName().equalsIgnoreCase(product_name)){
                product_available.add(we_have);
            }
        }
        return product_available;
    }
    public void adding_product_to_carts(List<Product> list_of_products){
        this.the_cart.add(list_of_products);
    }
    public void processing_the_products(){
        List<Product> the_prod=the_cart.poll();
        if(the_prod!=null){
            for (Product each_product:the_prod){
                Product the_stored_product=product_to_Show.get(each_product.getId());
                if(the_stored_product!=null && the_stored_product.getQuantity()>=each_product.getQuantity()){
                    System.out.println("Thanku for purchasing...😃😃😄");
                    the_stored_product.setQuantity(the_stored_product.getQuantity()-each_product.getQuantity());
                }
                else {
                    System.out.println("Sorry! don't have sufficient stock for "+each_product.getName()+ "  this product.🙃🙃🙃");
                }
            }
        }
    }

}
