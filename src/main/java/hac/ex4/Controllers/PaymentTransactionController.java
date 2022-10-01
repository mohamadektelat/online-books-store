//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Controllers;

//----------------------------------------------------------------------------------------------------------------------

import hac.ex4.Service.LibraryServices;
import hac.ex4.Service.PaymentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

//----------------------------------------------------------------------------------------------------------------------

/**
 * PaymentTransactionController
 */
@Controller
public class PaymentTransactionController {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * payment
     */
    private final PaymentServices payment;
    /**
     * library service
     */
    private final LibraryServices libraryServices;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * main start PaymentTransactionController
     * @param payment payments
     * @param libraryServices library service
     */
    public PaymentTransactionController(PaymentServices payment, LibraryServices libraryServices) {
        this.payment = payment;
        this.libraryServices = libraryServices;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * all the payments that happened only admin can see it
     * @param model model
     * @return payments
     */
    @RequestMapping("/payments")
    public String payments(Model model) {
        model.addAttribute("Payments", payment.getPaymentsTransactions());
        return "paymentsPage";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * when the admin in payments page and whant to back to admin page
     * @param model model
     * @return admin
     */
    @RequestMapping("/backPayments")
    public RedirectView backPayments(Model model) {
        model.addAttribute("books", libraryServices.getLibrary());
        return new RedirectView("/admin");
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------