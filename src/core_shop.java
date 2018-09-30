
import java.util.*;

public class core_shop {

    private int item_count;
    private int stock_threshold;
    private ArrayList<String> items;
    private Map< String,Integer> item_quantity;
    private Map< String,Integer> item_price;
    private ArrayList<String> purchase_list;

    core_shop( int item_count , int stock_threshold , String[] items, int[] quantity , int[] price ){

        this.item_count = item_count;
        this.stock_threshold = stock_threshold;
        this.items = new ArrayList<String>();
        this.purchase_list = new ArrayList<String>();
        this.item_quantity = new HashMap< String,Integer>();
        this.item_price = new HashMap< String,Integer>();
        int i = 0;
        while( i < item_count ){
            this.items.add(items[i]);
            this.item_quantity.put(items[i], quantity[i]);
            this.item_price.put(items[i], price[i]);
            if(quantity[i] < stock_threshold){
                this.purchase_list.add(items[i]);
            }
            i += 1;
        }

    }

    public Map< String,Integer> get_item_quantity(){
        return this.item_quantity;
    }

    public Map< String,Integer> get_item_price(){
        return this.item_price;
    }

    public void update_item_quantities( String item , int new_quantity ){

        int old_quantity = this.item_quantity.get(item);
        this.item_quantity.put( item , new_quantity);
        int i = 0;

        if( old_quantity > this.stock_threshold ){
            if( new_quantity <= this.stock_threshold ){
                this.purchase_list.add(item);
            }
        }else{
            if( new_quantity > this.stock_threshold){
                this.purchase_list.remove(item);
            }
        }
    }

    public void print_db(){

        System.out.println("Following are items and their quantities\n");
        System.out.println("Item \t \t Quantity \t \t Price");
        int i = 0;

        while( i < this.item_count ){
            System.out.println( this.items.get(i) +
                    " \t \t " +
                    Integer.toString(this.item_quantity.get(this.items.get(i))) +
                    " \t \t " +
                    Integer.toString(this.item_price.get(this.items.get(i))));
            i += 1;
        }

        System.out.println("\nFollowing are items in purchase list\n");
        i = 0;
        while( i < this.purchase_list.size() ){
            System.out.println( this.purchase_list.get(i) );
            i += 1;
        }
    }

}
