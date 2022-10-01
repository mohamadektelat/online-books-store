//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Controllers;

//----------------------------------------------------------------------------------------------------------------------

import hac.ex4.Model.Book;
import hac.ex4.Model.Cart;
import hac.ex4.Model.CartItem;
import hac.ex4.Model.PaymentTransaction;
import hac.ex4.Service.LibraryServices;
import hac.ex4.Service.PaymentServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------


/**
 * the controller of the user
 */
@Controller
public class UserController {

    //------------------------------------------------------------------------------------------------------------------

    /**
     * library service
     */
    private final LibraryServices libraryServices;
    /**
     * payment
     */
    private final PaymentServices payment;
    /**
     * cart
     */
    @Resource(name="cartObject")
    private Cart cart;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * constructor
     * @param libraryServices library service
     * @param payment payments
     */
    public UserController(LibraryServices libraryServices, PaymentServices payment) {
        this.libraryServices = libraryServices;
        this.payment = payment;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Landing page for the user
     * @param model model
     * @return user page
     */
    @RequestMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("error", false);
        model.addAttribute("books", fiveBooksSale());
        model.addAttribute("booksOnSale", fiveBooksSale());
        model.addAttribute("cart", cart.getCart());
        return "userPage"; //view
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * search
     * @param s parameter
     * @param model model
     * @return user page
     */
    @GetMapping("/search/{s}")
    public String searchRes(@PathVariable String s, Model model) {
        List<Book> result = new ArrayList<Book>();
        for (int book = 0; book < libraryServices.getLibrary().size(); book++) {
            if (libraryServices.getLibrary().get(book).getName().contains(s)) {
                result.add(libraryServices.getLibrary().get(book));
            }
        }
        model.addAttribute("books", result);
        model.addAttribute("cart", cart.getCart());
        model.addAttribute("error", false);
        return "userPage";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * search redirect
     * @param search search
     * @param model model
     * @return redirect for search results
     */
    @RequestMapping("/search")
    public RedirectView search(@RequestParam("searchResult") String search, Model model) {
        if(search.equals(""))
            return new RedirectView("/");
        return new RedirectView("/search/"+search);
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * redirect to payment page
     * @param totalPrice total price
     * @param model model
     * @return payments
     */
    @RequestMapping("/pay")
    public String pay(@RequestParam("totalPrice") double totalPrice, Model model) {
        model.addAttribute("cart", this.cart);
        model.addAttribute("error1", false);
        model.addAttribute("error2", false);
        if (totalPrice == 0) {
            model.addAttribute("error1", true);
            return "cartPage";
        } else if (!ifQuantityValid()) {
            model.addAttribute("error2", true);
            return "cartPage";
        }
        PaymentTransaction pay = new PaymentTransaction(totalPrice);
        payment.addPayment(pay);
        for(CartItem item : cart.getCart()){
            Book toBeUpdated = libraryServices.findBookById(item.getBook().getId());
            if (toBeUpdated.getQuantity()-item.getQuantity() == 0) { libraryServices.deleteBook(item.getBook().getId());}
            else {
                toBeUpdated.setQuantity(toBeUpdated.getQuantity()-item.getQuantity());
                libraryServices.addBook(toBeUpdated);
            }
        }
        cart.deleteAll();
        model.addAttribute("error", false);
        model.addAttribute("books", libraryServices.getLibrary());
        model.addAttribute("booksOnSale", fiveBooksSale());
        model.addAttribute("cart", cart.getCart());
        return "redirect:/";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * check if the quantity valid
     * @return validation
     */
    public boolean ifQuantityValid() {
        for (CartItem item : cart.getCart()) {
            if (item.getQuantity() > libraryServices.findBookById(item.getBook().getId()).getQuantity()) {
                return false;
            }
        }
        return true;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * get 5 books with the bigger discount.
     * @return five books
     */
    public List<Book> fiveBooksSale() {
        //in case we have less than 6
        if (libraryServices.getLibrary().size() <= 5) {
            return libraryServices.getLibrary();
        }
        return findMaxValueWithDiscount();
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * find the most 5 books with bigger discount.
     * @return five books on sale
     */
    public List<Book> findMaxValueWithDiscount() {
        List<Book> array = libraryServices.getLibrary();
        array.sort(new Comparator<Book>() {
            @Override
            public int compare(Book c1, Book c2) {
                return Double.compare(c2.getDiscount(),c1.getDiscount());
            }
        });
        array = array.subList(0,5);
        return array;
    }

    //------------------------------------------------------------------------------------------------------------------
}

//----------------------------------------------------------------------------------------------------------------------
