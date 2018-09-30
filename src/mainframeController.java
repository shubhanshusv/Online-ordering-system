import javafx.application.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class mainframeController {
    private order_details od;
    private order o;
    mainframeController() {

        od = new order_details();
        od.setContentPane(od.$$$getRootComponent$$$());
        od.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        od.pack();
        od.setVisible(true);

//        JButton okButton =od.getOkButton();
//        okButton.addActionListener(new OkButtonListner());
        //        od.getSpinners()
//        initComponents();
    }

    public static void main(String[] args){

    }


    order get_order(){
        return od.get_order();
    }

//    private class OkButtonListner implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            ArrayList<JSpinner> spinners = od.getSpinners();
//            int[] quantities = new int[]{(Integer)spinners.get(0).getValue(),
//                                    (Integer)spinners.get(1).getValue(),
//                                    (Integer)spinners.get(2).getValue(),
//                                    (Integer)spinners.get(3).getValue(),
//                                    (Integer)spinners.get(4).getValue(),
//                                    (Integer)spinners.get(5).getValue()};
//            String[] x=new String[]{"Tea","Coffee","Chips","Biscuits","Cake","Sandwich"};
//            o = new order(x,quantities,"Name");
//            od.dispose();
//
//        }
//    }

}
