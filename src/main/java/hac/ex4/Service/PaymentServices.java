//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Service;

//----------------------------------------------------------------------------------------------------------------------

import hac.ex4.Model.PaymentTransaction;
import hac.ex4.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------

/**
 * PaymentServices
 */
@Service
public class PaymentServices {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * payment
     */
    private final PaymentRepo Payment;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * PaymentServices
     * @param payment payment
     */
    @Autowired
    public PaymentServices(PaymentRepo payment) {
        this.Payment = payment;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * addPayment
     * @param payment payment
     */
    public void addPayment(PaymentTransaction payment){
        this.Payment.save(payment);
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getPaymentsTransactions
     * @return all payments
     */
    public List<PaymentTransaction> getPaymentsTransactions() {
        return this.Payment.findAll();
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------
