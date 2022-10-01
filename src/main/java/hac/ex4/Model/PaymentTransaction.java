//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Model;

//----------------------------------------------------------------------------------------------------------------------

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

//----------------------------------------------------------------------------------------------------------------------

/**
 * class of PaymentTransaction
 */
@Entity
@Table(name = "PaymentTransaction")
public class PaymentTransaction implements Serializable {

    //------------------------------------------------------------------------------------------------------------------

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;
    /**
     * total price
     */
    @Column(name = "totalPrice")
    private double totalPrice;
    /**
     * date time
     */
    @CreationTimestamp
    @Column(name = "dateTime")
    private Timestamp dateTime;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * PaymentTransaction constructor
     */
    public PaymentTransaction() {}

    //------------------------------------------------------------------------------------------------------------------

    /**
     * PaymentTransaction constructor
     * @param totalPrice total price
     */
    public PaymentTransaction(double totalPrice) {
        this.totalPrice = totalPrice;

    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getTotalPrice
     * @return total price
     */
    public double getTotalPrice(){
        return this.totalPrice;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Timestamp
     * @return date time
     */
    public Timestamp getDateTime(){
        return this.dateTime;
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------
