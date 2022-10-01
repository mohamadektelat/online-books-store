//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Model;

//----------------------------------------------------------------------------------------------------------------------

import java.io.Serializable;

//----------------------------------------------------------------------------------------------------------------------

/**
 * cart item class
 */
public class CartItem implements Serializable {

    //------------------------------------------------------------------------------------------------------------------

    /**
     * quantity
     */
    private int quantity;
    /**
     * total price
     */
    private double totalPrice;
    /**
     * book
     */
    private Book book;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * constructor of cart item
     * @param book book
     * @param quantity quantity
     */
    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = (book.getPrice() * ((100 - book.getDiscount())/100)) * quantity;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getQuantity
     * @return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getTotalPrice
     * @return total price
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getBook
     * @return book
     */
    public Book getBook() {
        return this.book;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setQuntity
     * @param quantity quantity
     */
    public void setQuntity(int quantity) {
        this.quantity = quantity;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setTotalPrice
     * @param totalPrice total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setBook
     * @param book book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------
