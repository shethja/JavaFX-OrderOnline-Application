/**
 * JAINAM SHETH
 * FINAL PROJECT
 * 991549128
 */
package content;

/**
 *
 * @author Laptop
 */
public class Order {
    private String orderID;
    private String customerID;
    private String product;
    private String shipping;
    
    public Order(String orderID)
    {
        this.orderID=orderID;
    }
    
    public String getOrderID()
    {
        return orderID;
    }
    
    public void setCustomerID(String customerID)
    {
        this.customerID=customerID;
    }
    
    public String getCustomerID()
    {
        return customerID;
    }
    
    public void setProduct(String product)
    {
        this.product=product;
    }
    
    public String getProduct()
    {
        return product;
    }
    
    public void setShipping(String shipping)
    {
        this.shipping=shipping;
    }
    
    public String getShipping()
    {
        return shipping;
    }
}
