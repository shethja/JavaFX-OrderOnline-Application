/**
 * JAINAM SHETH
 * FINAL PROJECT
 * 991549128
 */
package content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Laptop
 */
public class OrderFile {
    
    public static void setRecords(ArrayList<Order> orderList) throws IOException
    {
        FileWriter fw=new FileWriter("Order.dat");
        BufferedWriter bw=new BufferedWriter(fw);
        for(Order sub: orderList)
        {
            Order one= sub;
            String data=one.getOrderID()+","+one.getCustomerID()+","
                    +one.getProduct()+","+one.getShipping();
            bw.write(data);
            bw.flush();
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
    
    public static ArrayList<Order> getRecords() throws IOException
    {
        FileReader fr=new FileReader("Order.dat");
        BufferedReader br=new BufferedReader(fr);
        ArrayList<Order> orderList=new ArrayList<>();
        String line=br.readLine();
        while(line!=null)
        {
            StringTokenizer s1=new StringTokenizer(line,",");
            while(s1.hasMoreTokens())
            {
            String orderID=s1.nextToken();
            String customerID=s1.nextToken();
            String product=s1.nextToken();
            String shipping=s1.nextToken();
            Order one=new Order(orderID);
            one.setCustomerID(customerID);
            one.setProduct(product);
            one.setShipping(shipping);
            orderList.add(one);
            }
            line=br.readLine();
        }
        br.close();
        fr.close();
        return orderList;
    }
    
}
