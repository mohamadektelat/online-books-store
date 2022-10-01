//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Model;

//----------------------------------------------------------------------------------------------------------------------

import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

//----------------------------------------------------------------------------------------------------------------------

/**
 * cart class
 */
@Component
public class Cart implements Serializable {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * cart
     */
    private ArrayList<CartItem> cart;
    /**
     * total price
     */
    private double totalPrice;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * constructor of cart
     */
    public Cart(){
        this.cart = new ArrayList<>();
        this.totalPrice = 0L;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * ArrayList of Cart item
     * @return cart
     */
    public ArrayList<CartItem> getCart(){
        return this.cart;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getTotalPrice
     * @return total price
     */
    public double getTotalPrice(){return this.totalPrice;}

    //------------------------------------------------------------------------------------------------------------------

    /**
     * set cart
     * @param cart cart
     */
    public void setCart(ArrayList<CartItem> cart){
        this.cart = cart;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * add item to cart
     * @param item item
     */
    public void add(CartItem item){
        this.cart.add(item);
        this.totalPrice += item.getTotalPrice();
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * delete item from cart
     * @param id id
     */
    public void delete(Long id){
        for(CartItem item : this.cart)
            if(Objects.equals(item.getBook().getId(), id)){
                this.totalPrice -= item.getTotalPrice();
                break;
            }
        this.cart.removeIf(item -> Objects.equals(item.getBook().getId(), id));
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * delete all items in cart
     */
    public void deleteAll(){
        this.totalPrice = 0;
        this.cart.clear();
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------