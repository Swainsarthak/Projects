import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class employee_access {
    private  Management new_ecommerce_site;
    public  Management getNew_ecommerce_site() {
        return new_ecommerce_site;
    }
    Scanner sc1=new Scanner(System.in);
    public employee_access(){
        System.out.println("Hi,welcome");
        new_ecommerce_site=new Management();
        task();
    }
    public void task(){
        System.out.println("What you want to do: ");
        System.out.println("Press 1 for creating a category.\nPress 2 adding products in the category.\nPress 3 for adding products in the cart.\nPress 4 for proccessing orders.\nPress 0 for process to end.");
        int task=sc1.nextInt();
        while (task!=0) {
            switch (task) {
                case 1:
                    System.out.println("Enter the category to insert in the mart: ");
                    String cat_name = sc1.next();
                    creating_new_category(cat_name);
                    break;
                case 2:
                    System.out.println("Enter the product name:");
                    String p_name = sc1.next().toLowerCase();
                    System.out.println("Enter the product caegory type:");
                    String c_type = sc1.next().toLowerCase();
                    System.out.println("Enter the product id: ");
                    int p_id = sc1.nextInt();
                    System.out.println("Enter the price of the product:");
                    int price = sc1.nextInt();
                    System.out.println("Enter the no of quantity:");
                    int qunt = sc1.nextInt();
                    adding_product(p_name, c_type, p_id, price, qunt);
                    break;
                case 3:
                    add_orders_to_a_cart();
                    break;
                case 4:
                    processing_the_order();
                    break;
                default:
                    System.out.println("invalid choice.");
                    break;
            }
            System.out.println("Press 1 for creating a category.\nPress 2 adding products in the category.\nPress 3 for adding products in the cart.\nPress 4 for proccessing orders.\n");
            task=sc1.nextInt();
        }
    }
    public void creating_new_category(String cat){
        Category new_cat=new Category(cat);
        new_ecommerce_site.getRoot_of_the_site().getSub_category_products_name().add(new_cat);
    }
    public void adding_product(String Product_name,String Product_category_type,int item_id,int price,int no_of_quantity){
        Product product1=new Product(item_id,Product_name,price,no_of_quantity);
        Category temp = null;
        for (Category types_avaiulable:new_ecommerce_site.getRoot_of_the_site().getSub_category_products_name()){
            if(types_avaiulable.category_name.equalsIgnoreCase(Product_category_type)){
                temp=types_avaiulable;
            }
        }
        assert temp != null;
        new_ecommerce_site.add_product_to_the_category(product1,temp);
    }
    public void add_orders_to_a_cart(){
        List<Product> products_to_add_in_cart=new ArrayList<>();
        System.out.println("Enter the product want to add in to the cart: ");
        String prod_typ=sc1.next();
        List<Product> res=new_ecommerce_site.to_show_product_details(prod_typ);
        for (Product pd:res){
            System.out.println("Product name: "+ pd.getName()+" ,product id: "+ pd.getId() + " ,Product cost: "+pd.getCost());
        }
        System.out.println("enter the id:");
        int id=sc1.nextInt();
        System.out.println("enter the no of quantity wanna purchase.");
        int prod_quantity=sc1.nextInt();
        while (prod_quantity!=0){
            Product pd=new_ecommerce_site.getProduct_to_Show().get(id);
            products_to_add_in_cart.add(pd);
            prod_quantity--;
        }
        new_ecommerce_site.adding_product_to_carts(products_to_add_in_cart);
    }
    public void processing_the_order(){
        new_ecommerce_site.processing_the_products();
        System.out.println("Thanks for shopping.");
    }

}
