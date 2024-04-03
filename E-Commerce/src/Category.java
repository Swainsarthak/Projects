import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Category {
    public String category_name;
    private List<Category> sub_category_products_name;
    private List<Product> products_to_be_added;
    public List<Product> getProducts_to_be_added() {
        return products_to_be_added;
    }
    public List<Category> getSub_category_products_name() {
        return sub_category_products_name;
    }
    public Category(String the_category_name){
        this.category_name=the_category_name;
        this.products_to_be_added=new ArrayList<>();
        this.sub_category_products_name=new ArrayList<>();

    }
}
