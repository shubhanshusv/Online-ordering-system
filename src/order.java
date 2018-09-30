import java.io.Serializable;
import java.util.*;

public class order implements Serializable {

    private String[] items;//
    private int[] quantities;//
    private String name;//
    private Date order_date;
    private int total_amount;
    private float time_required;

    order(String[] items, int[] quantities , String name){

        this.items = new String[items.length];
        this.quantities = new int[quantities.length];
        this.name = name;
        int  i = 0;
        while( i < items.length ){
            this.items[i] = items[i];
            this.quantities[i] = quantities[i];
            i += 1;
        }

    }

    public String[] get_items(){
        return this.items;
    }

    public int[] get_quantities(){
        return this.quantities;
    }


    public void set_total_amount( int x ){
        this.total_amount = x;
    }

    public void set_time_required( float x ){
        this.time_required = x;
    }

    public void set_date(){
        this.order_date = new Date();
    }

    public void print_order(){

        System.out.println(this.order_date);
        System.out.println(this.name);
        System.out.println(this.time_required);
        System.out.println(this.total_amount);
        System.out.println(Arrays.toString(this.quantities));
    }
}
