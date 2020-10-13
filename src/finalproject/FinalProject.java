/**
 * JAINAM SHETH
 * FINAL PROJECT
 * 991549128
 */
package finalproject;

import content.Order;
import content.OrderFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Laptop
 */
public class FinalProject extends Application {
    
    private Label lblOrderID=new Label("Order ID: ");
    private TextField txtOrderID=new TextField();
    private Label lblCustomerID=new Label("Customer ID: ");
    private TextField txtCustomerID=new TextField();
    private Label lblProduct=new Label("Product: ");
    private TextField txtProduct=new TextField();
    private Label lblShipping=new Label("Shipping Method: ");
    private TextField txtShipping=new TextField();
    private Label lblSearchCustomerID=new Label("Enter Customer ID to search");
    private TextField txtSearchCustomerID=new TextField();
    private Label lblSearchProduct=new Label("Enter Product to search");
    private TextField txtSearchProduct=new TextField();
    private Button btnAdd=new Button("Add");
    private Button btnFirst=new Button("First");
    private Button btnLast=new Button("Last");
    private Button btnNext=new Button("Next");
    private Button btnPrevious=new Button("Previous");
    private Button btnUpdate=new Button("Update");
    private Button btnDelete=new Button("Delete");
    private Button btnDisplay=new Button("Display");
    private Button btnSearchCustomerID=new Button("Search");
    private Button btnSearchProduct=new Button("Search");
    
    private ArrayList<Order> orderList=new ArrayList<>();
    int sub=0;
    boolean add=false;
    public FinalProject()
    {
        try {
            orderList=OrderFile.getRecords();
        } 
        catch (IOException ex) {
            System.err.println("Error is: "+ex.getMessage());
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene=new Scene(addPane(),400,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Page");
        primaryStage.show();
        
            txtOrderID.setText(orderList.get(0).getOrderID());
            txtCustomerID.setText(orderList.get(0).getCustomerID());
            txtProduct.setText(orderList.get(0).getProduct());
            txtShipping.setText(orderList.get(0).getShipping());
            
        btnAdd.setOnAction((e)->{
            clearAll();
            add=true;
        });
        
        btnSearchCustomerID.setOnAction((e)->{
            DisplayStage display=new DisplayStage(searchCustomerID());
            display.show();
            txtSearchCustomerID.clear();
        });
        
        btnSearchProduct.setOnAction((e)->{
            DisplayStage display=new DisplayStage(searchProduct());
            display.show();
            txtSearchProduct.clear();
        });
        
        btnDisplay.setOnAction((e)->{
            DisplayStage display=new DisplayStage(displayAll());
            display.show();
        });
       
        btnFirst.setOnAction((e)->{
            txtOrderID.setText(orderList.get(0).getOrderID());
            txtCustomerID.setText(orderList.get(0).getCustomerID());
            txtProduct.setText(orderList.get(0).getProduct());
            txtShipping.setText(orderList.get(0).getShipping());
            sub=0;
        });
        
        btnLast.setOnAction((e)->{
            int size=orderList.size();
            txtOrderID.setText(orderList.get(size-1).getOrderID());
            txtCustomerID.setText(orderList.get(size-1).getCustomerID());
            txtProduct.setText(orderList.get(size-1).getProduct());
            txtShipping.setText(orderList.get(size-1).getShipping());
            sub=size-1;
        });
        
        btnNext.setOnAction((e)->{
            showNext();
        });
        
        btnPrevious.setOnAction((e)->{
            showPrevious();
        });
        
        btnUpdate.setOnAction((e)->{
            try
            {
                Alert dlgConfirmation=new Alert(Alert.AlertType.CONFIRMATION);
                dlgConfirmation.setHeaderText("Want to add/update order?");
                Optional<ButtonType> result= dlgConfirmation.showAndWait();
                Alert dlgInfo=new Alert(Alert.AlertType.INFORMATION);
                String message=new String();
                if(result.get()==ButtonType.OK)
                {
                    if(add==false)
                    {
                        Order three= orderList.get(sub);
                        if(!txtOrderID.getText().equals(three.getOrderID())
                  &&!txtCustomerID.getText().equals(three.getCustomerID()))
                        {
                          Alert dlgError=new Alert(Alert.AlertType.ERROR);
                          dlgError.setContentText("Cannot chnage OrderID and CustomerID");
                          dlgError.show();
                        }
                            three.setProduct(txtProduct.getText());
                            three.setShipping(txtShipping.getText());
                            message="Order updated successfully";
                            txtOrderID.setText(three.getOrderID());
                            txtCustomerID.setText(three.getCustomerID());
                            txtProduct.setText(three.getProduct());
                            txtShipping.setText(three.getShipping());
                    }
                    else
                    {
                    addOrder();
                    message="Order Successfully Added";
                    int size=orderList.size();
                    txtOrderID.setText(orderList.get(size-1).getOrderID());
                    txtCustomerID.setText(orderList.get(size-1).getCustomerID());
                    txtProduct.setText(orderList.get(size-1).getProduct());
                    txtShipping.setText(orderList.get(size-1).getShipping());
                    add=false;
                    }
                }
                else
                {
                    message="Adding/Updating Order Cancelled";
                    txtOrderID.setText(orderList.get(sub).getOrderID());
                    txtCustomerID.setText(orderList.get(sub).getCustomerID());
                    txtProduct.setText(orderList.get(sub).getProduct());
                    txtShipping.setText(orderList.get(sub).getShipping());
                }
                OrderFile.setRecords(orderList);
                dlgInfo.setContentText(message);
                dlgInfo.show();
            }
            catch(Exception ex)
            {
                System.out.println("Error is: "+ex.getMessage());
            }
        });

        btnDelete.setOnAction((e)->{
            try {
                int size=orderList.size();
                Alert dlgConfirmation=new Alert(Alert.AlertType.CONFIRMATION);
                dlgConfirmation.setHeaderText("Want to delete order?");
                Optional<ButtonType> result= dlgConfirmation.showAndWait();
                Alert dlgInfo=new Alert(Alert.AlertType.INFORMATION);
                String message=new String();
                if(result.get()==ButtonType.OK)
                {
                    orderList.remove(sub);
                    message="Order successfully deleted";  
                    size=orderList.size();
                }
                else
                {
                    message="Deletion Cancelled";
                }
                if(sub>=size)
                {
                    sub=size-1;
                }
                OrderFile.setRecords(orderList);
                dlgInfo.setContentText(message);
                dlgInfo.show();
                txtOrderID.setText(orderList.get(sub).getOrderID());
                txtCustomerID.setText(orderList.get(sub).getCustomerID());
                txtProduct.setText(orderList.get(sub).getProduct());
                txtShipping.setText(orderList.get(sub).getShipping());
            } catch (IOException ex) {
                 System.err.println("Error is: "+ex.getMessage());
            }
        });
    }

    private GridPane addPane()
    {
        GridPane pane=new GridPane();
        pane.add(lblOrderID,0,0);
        pane.add(lblCustomerID,0,1);
        pane.add(lblProduct,0,2);
        pane.add(lblShipping,0,3);
        
        pane.add(txtOrderID,1,0);
        pane.add(txtCustomerID,1,1);
        pane.add(txtProduct,1,2);
        pane.add(txtShipping,1,3);
        
        pane.add(btnFirst,0,4);
        pane.add(btnLast,2,4);
        pane.add(btnNext,0,5);
        pane.add(btnPrevious,2,5);
        
        pane.add(btnUpdate,0,6);
        pane.add(btnAdd,1,6);
        pane.add(btnDelete,2,6);
        
        pane.add(lblSearchCustomerID,0,8);
        pane.add(txtSearchCustomerID,1,8);
        pane.add(btnSearchCustomerID,2,8);
        
        pane.add(lblSearchProduct,0,9);
        pane.add(txtSearchProduct,1,9);
        pane.add(btnSearchProduct,2,9);
        
        pane.add(btnDisplay,1,10);
        
        return pane;
    }
    
     private void addOrder()
    {
        String orderID=txtOrderID.getText();
        String customerID=txtCustomerID.getText();
        String product=txtProduct.getText();
        String shipping=txtShipping.getText();
        Order one=new Order(orderID);
        one.setCustomerID(customerID);
        one.setProduct(product);
        one.setShipping(shipping);
        orderList.add(one);
    }
     
    private String searchCustomerID()
    {
        String search=new String();
        for(Order sub: orderList)
        {
            String ID=sub.getCustomerID();
            if(txtSearchCustomerID.getText().equalsIgnoreCase(ID))
            {
                search+=sub.getOrderID()+","+sub.getCustomerID()+
                        ","+sub.getProduct()+","+sub.getShipping()+"\n";
            }
        }    
        return search;
    }
    
    private String searchProduct()
    {
       String search=new String();
        for(Order sub: orderList)
        {
            String product=sub.getProduct();
            if(txtSearchProduct.getText().equalsIgnoreCase(product))
            {
                search+=sub.getOrderID()+","+sub.getCustomerID()+
                        ","+sub.getProduct()+","+sub.getShipping()+"\n";
            }
        }    
        return search;
    }
    
    private String displayAll()
    {
        String data=new String();
        for(Order sub: orderList)
        {
            data+=sub.getOrderID()+","+sub.getCustomerID()+","
                    +sub.getProduct()+","+sub.getShipping()+"\n";
        }
        return data;
    }
    
    private void showNext()
    {
        int size=orderList.size();
            sub++;
            if(sub>=size)
            {
            txtOrderID.setText(orderList.get(0).getOrderID());
            txtCustomerID.setText(orderList.get(0).getCustomerID());
            txtProduct.setText(orderList.get(0).getProduct());
            txtShipping.setText(orderList.get(0).getShipping());
            sub=0;
            }
            else
            {
            txtOrderID.setText(orderList.get(sub).getOrderID());
            txtCustomerID.setText(orderList.get(sub).getCustomerID());
            txtProduct.setText(orderList.get(sub).getProduct());
            txtShipping.setText(orderList.get(sub).getShipping());
            }
    }
    
    private void showPrevious()
    {
        int size=orderList.size();
            sub--;
            if(sub<0)
            {
                txtOrderID.setText(orderList.get(size-1).getOrderID());
                txtCustomerID.setText(orderList.get(size-1).getCustomerID());
                txtProduct.setText(orderList.get(size-1).getProduct());
                txtShipping.setText(orderList.get(size-1).getShipping());
                sub=size-1;
            }
            else
            {
            txtOrderID.setText(orderList.get(sub).getOrderID());
            txtCustomerID.setText(orderList.get(sub).getCustomerID());
            txtProduct.setText(orderList.get(sub).getProduct());
            txtShipping.setText(orderList.get(sub).getShipping());
            }
    }
    
    private void clearAll()
    {
        txtOrderID.clear();
        txtCustomerID.clear();
        txtProduct.clear();
        txtShipping.clear();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
