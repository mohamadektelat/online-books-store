//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Model;

//----------------------------------------------------------------------------------------------------------------------

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

//----------------------------------------------------------------------------------------------------------------------

/**
 * Book class we put all thing that book need with validation
 */
@Entity
@Table(name = "books")
@DynamicInsert
@DynamicUpdate
public class Book implements Serializable{

    //------------------------------------------------------------------------------------------------------------------
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;
    /**
     * name
     */
    @Column(name = "name")
    @NotBlank(message = "name is mandatory")
    private String name;
    /**
     * image
     */
    @Column(name = "image")
    private String image;
    /**
     * quantity
     */
    @Column(name = "quantity")
    @Min(value = 1, message = "must have at least one book")
    private int quantity;
    /**
     * price
     */
    @Column(name = "price")
    @DecimalMin(value = "0.1", message = "Price must be bigger than 0")
    private double price;
    /**
     * discount
     */
    @Column(name = "discount")
    @DecimalMin(value = "0.0", message = "discount must be between 0 and 100") @DecimalMax(value = "100.0", message = "discount must be between 0 and 100")
    private double discount;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * default constructor.
     */
    public Book() { this.image = "https://islandpress.org/sites/default/files/default_book_cover_2015.jpg"; }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * constructor of book
     * @param name name
     * @param image image url
     * @param quantity quantity
     * @param price price
     * @param discount discount
     */
    public Book(String name, String image, int quantity, double price, double discount) {
      this.name = name;
      this.image = image;
      this.quantity = quantity;
      this.price = price;
      this.discount = discount;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getId
     * @return the book id
     */
    public Long getId() {
        return this.id;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getName
     * @return the book name
     */
    public String getName() {
        return this.name;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getImage
     * @return the image url
     */
    public String getImage() {
        return this.image;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getQuantity
     * @return the book quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getPrice
     * @return the book price
     */
    public double getPrice() {
        return this.price;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getDiscount
     * @return the book discount
     */
    public double getDiscount() {
        return this.discount;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setId
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setName
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setImage
     * @param image the image url
     */
    public void setImage(String image) {
        this.image = image;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setQuantity
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setPrice
     * @param price the price
     */
    public void setPrice(double price){
        this.price = price;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * setDiscount
     * @param discount the discount
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * funcrtion to get the book ass json request body(for validation)
     * @return string json request body.
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }

    //------------------------------------------------------------------------------------------------------------------
}

//----------------------------------------------------------------------------------------------------------------------