/**
 * Created by Sasha on 09.08.2016.
 */

import packOrder.TooManyBikesException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;


/**
 * Created by Sasha on 29.05.2016.
 */
public class OrderWindow implements ActionListener{

    static String selectedModel = "FireBird";

    String getSelectedModel(){

        return selectedModel;
    }

    void setSelectedModel(String aselectedModel){

        selectedModel = aselectedModel;
    }


    JFrame frame = new JFrame("Заказы на покупку велосипедов");
    JMenuBar jMenuBar = new JMenuBar();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();

    JButton button = new JButton("Order");

    private JTextField txtFieldModel  = new JTextField();
    private JTextField txtFieldQuantity = new JTextField();
    private JTextField txtFieldOrderConfirmation = new JTextField();
    JLabel  bikesModel = new JLabel("Bikes Model");
    JLabel  bikesQuantity = new JLabel("Bikes Quantity");

    String[] items = {
            "FireBird",
            "Hurley",
            "BlackNight"
    };



    public static void main(String[] args) {

        OrderWindow orderWindow = new OrderWindow();
        orderWindow.go();

    }

    private void go() {

        JComboBox<String> comboBox = new JComboBox<String>(items);
        comboBox.addItemListener(new BoxListener());

        jPanel1.setLayout(new BorderLayout());
        jPanel2.setLayout(new GridLayout(2,2));



        jPanel2.add(bikesModel);
        jPanel2.add(comboBox);
        jPanel2.add(bikesQuantity);
        jPanel2.add(txtFieldQuantity);

        jPanel1.add(jPanel2);


        jPanel1.add(txtFieldOrderConfirmation,BorderLayout.NORTH);
        jPanel1.add(button,BorderLayout.SOUTH);



        frame.setJMenuBar(jMenuBar);

        frame.add(jPanel1);
        frame.getDefaultCloseOperation();
        frame.setLocationRelativeTo(null);
        frame.setSize(470,130);
        frame.setVisible(true);


        button.addActionListener( this);


    }



    public class BoxListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            JComboBox<String> box = (JComboBox<String>)e.getSource();
            String selectedModelnew = (String)box.getSelectedItem();

            OrderWindow box1 = new OrderWindow();
            box1.setSelectedModel(selectedModelnew);


        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {


        String selectedModel =  getSelectedModel();
        //String selectedModel = txtFieldModel.getText();

        String selectedQuantity = txtFieldQuantity.getText();
        int quantity = (int) Integer.parseInt(selectedQuantity);

        OrderWindow bikeOrder = new OrderWindow();




        try {

            bikeOrder.checkOrder(selectedModel, quantity);

            txtFieldOrderConfirmation.setText("Размещение вашего заказа завершено");

        }catch (TooManyBikesException ex){

            txtFieldOrderConfirmation.setText(ex.getMessage());
        }
    }



    void checkOrder(String bikeModel,int  quantity)
            throws TooManyBikesException{


        if((bikeModel.equals("FireBird")&& quantity > 3)||(bikeModel.equals("Hurley")&& quantity > 5)||
                (bikeModel.equals("BlackNight")&& quantity > 9)) {
            throw new TooManyBikesException("Невозможно доставить "
                    + quantity + " модели велосипедов " + bikeModel + " за одну доставку");
        }
    }
}

