public class Product {
    private int id;

    private String name;
    private  int cost;
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    private int quantity;
    public int getId() {return id;}
    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
    public int getQuantity() {
        return quantity;
    }
    public Product(int id_no,String product_name,int price,int no_of_quantity){
        this.id=id_no;
        this.name=product_name;
        this.cost=price;
        this.quantity=no_of_quantity;
    }
}
