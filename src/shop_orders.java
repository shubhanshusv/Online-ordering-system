import java.util.*;
import java.util.concurrent.*;

public class shop_orders {

    private core_shop shop;
    private ArrayList<order> orders;
    private long tea_coffee_time;

    shop_orders( int item_count , int stock_threshold , String[] items, int[] quantity, int[] price ){

        this.shop = new core_shop( item_count , stock_threshold , items , quantity , price );
        this.orders = new ArrayList<order>();
        this.tea_coffee_time = System.currentTimeMillis()/1000;

    }

    public String add_order( order placed_order ){

        int i = 0;
        int order_amount = 0;
        float time_required = 2*60;
        long current_time;
        String item;
        int available_quantity;
        int asked_quantity;
        int[] new_quantities = new int[placed_order.get_items().length];
        ArrayList<String> unavailable = new ArrayList<String>();
        String output = "";
        while( i < placed_order.get_items().length ){
            item = placed_order.get_items()[i];

            try{
                available_quantity = this.shop.get_item_quantity().get( item );
                asked_quantity = placed_order.get_quantities()[i];

                if( item.equals("Tea") || item.equals("Coffee") ){

                    order_amount += ( asked_quantity )*( this.shop.get_item_price().get( item ) );
                    current_time = System.currentTimeMillis()/1000;
                    if(this.tea_coffee_time < current_time ){
                        this.tea_coffee_time = current_time + 60*asked_quantity;

                        time_required += 60*asked_quantity;
                    }else{
                        time_required += this.tea_coffee_time - current_time;
                        time_required += 60*asked_quantity;
                        this.tea_coffee_time += 60*asked_quantity;
                    }
                    new_quantities[i] = available_quantity;

                }else{
                    if( available_quantity >= asked_quantity ){
                        order_amount += ( asked_quantity )*( this.shop.get_item_price().get( item ) );
                        new_quantities[i] = available_quantity - asked_quantity;
                        this.shop.update_item_quantities(item , available_quantity - asked_quantity);
                    }else{
                        unavailable.add( item );
                    }
                }

            }catch( Exception e ){
                unavailable.add( item );
            }


            i += 1;
        }

        if( unavailable.size() > 0 ){
            System.out.println("Some items you requested are unavailable.");
            output += "Some items you requested are unavailable. ";
            i = 0;
            while( i < unavailable.size() ){
                System.out.println( unavailable.get(i));
                output += unavailable.get(i) + " ";
                i += 1;
            }
            System.out.println("Order not placed.");
            output += "Order not placed.";

        }else{
            output += "Order successfully placed ";
            output += "Amout to be paid : " + Integer.toString(order_amount) + " ";
            output += "Wait time : " +  Float.toString( time_required/60)  + " minutes ";
            placed_order.set_date();
            placed_order.set_total_amount(order_amount);
            placed_order.set_time_required(time_required/60);
            i = 0;
            while( i < placed_order.get_items().length ){
                item = placed_order.get_items()[i];
                this.shop.update_item_quantities(item , new_quantities[i]);
                i += 1;
            }
            this.orders.add(placed_order);
        }
        System.out.println(output);
        return output;
    }

    public void print_orders(){

        int i = 0;
        int total = this.orders.size();

        while( i < total ){

            System.out.println("Order " + Integer.toString(i));
            this.orders.get(i).print_order();
            i += 1;
        }

    }

    public static void main(String args[]){

//		String[] items = new String[5];
//		items[0] = "tea";
//		items[1] = "biscuit";
//		items[2] = "lays";
//		items[3] = "tom tom";
//		items[4] = "ice-cream";
//		int[] quantity = new int[5];
//		quantity[0] = 1000;
//		quantity[1] = 20;
//		quantity[2] = 30;
//		quantity[3] = 9;
//		quantity[4] = 5;
//		int[] price = new int[5];
//		price[0] = 7;
//		price[1] = 10;
//		price[2] = 10;
//		price[3] = 5;
//		price[4] = 25;
//
//		shop_orders test_shop = new shop_orders(4 , 10 , items, quantity , price);
//		test_shop.shop.print_db();
//
//		String[] it = new String[2];
//		it[0] = "tea";
//		it[1] = "biscuit";
//
//		int[] qt = new int[2];
//		qt[0] = 5;
//		qt[1] = 11;
//
//		order order1 = new order(it,qt,"SV");
//		test_shop.add_order(order1);
//
//		it[0] = "tea";
//		it[1] = "biscuit";
//		qt[0] = 3;
//		qt[1] = 9;
//
//		order order2 = new order(it,qt,"SA");
//		test_shop.add_order(order2);
//
//		test_shop.print_orders();
//		test_shop.shop.print_db();

        Semaphore sem = new Semaphore(1);

        // creating two threads with name A and B
        // Note that thread A will increment the count
        // and thread B will decrement the count
//        MyThread mt1 = new MyThread(sem, "A");
//        MyThread mt2 = new MyThread(sem, "B");

        // stating threads A and B
//        mt1.start();
//        mt2.start();

    }

}

class Shared
{
    static int[] price = {6,7,10,10,15,20};
    static int[] quantity = {1000,1000,20,30,9,5};
    static String[] items = {"Tea", "Coffee", "Biscuits" , "Chips" , "Cake" , "Sandwich"};
    static shop_orders core1_shop = new shop_orders(6 , 10 , items, quantity , price);
    Shared(){
        this.core1_shop = new shop_orders(6 , 10 , items, quantity , price);
    }
}

class MyThread extends Thread
{
    Semaphore sem;
    String threadName;
    order o;
    String output;
    Shared shared_obj;
    public MyThread(Semaphore sem, String threadName, order o,Shared shared_obj)
    {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
        this.o = o;
        this.shared_obj = shared_obj;
    }

    @Override
    public void run() {
        System.out.println("Starting " + threadName);
        try
        {
            // First, get a permit.
            System.out.println(threadName + " is waiting for a permit.");

            // acquiring the lock
            sem.acquire();

            System.out.println(threadName + " gets a permit.");

//            String[] it = new String[2];
//            it[0] = "tea";
//            it[1] = "biscuit";
//
//            int[] qt = new int[2];
//            qt[0] = 5;
//            qt[1] = 10;
//
//            order order1 = new order(it,qt,"SV");
            this.output = Shared.core1_shop.add_order(this.o);
            shared_obj.core1_shop.print_orders();
            System.out.println(threadName + " releases permit.");
            Thread.sleep(1000);
            sem.release();

        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

    }
}
