//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Controllers;

//----------------------------------------------------------------------------------------------------------------------

import hac.ex4.Model.Book;
import hac.ex4.Model.Cart;
import hac.ex4.Model.CartItem;
import hac.ex4.Service.LibraryServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------

/**
 * controller of ShoppingCart
 */
@Controller
public class ShoppingCartController {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * library service
     */
    private final LibraryServices libraryServices;
    /**
     * cart
     */
    @Resource(name="cartObject")
    private Cart cart;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * main start for controller of ShoppingCart
     * @param libraryServices library service
     * @param cart cart
     */
    public ShoppingCartController(LibraryServices libraryServices, Cart cart) {
        this.libraryServices = libraryServices;
        this.cart = cart;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for Showing the cart page.
     * @param model model
     * @return cart page
     */
    @RequestMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", this.cart);
        model.addAttribute("error1", false);
        model.addAttribute("error2", false);
        return "cartPage";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function responsible for redirection to cart page.
     * @param model model
     * @return redirect for cart page
     */
    @PostMapping("/cartPage")
    public RedirectView cartPage(Model model) {
        return new RedirectView("/cart");
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for adding to cart action.
     * @param id id
     * @param quantity quantity
     * @param model model
     * @return user page
     */
    @RequestMapping(value = {"/addToCart", "/*/addToCart"})
    public String addToCart(@RequestParam("id") Long id, @RequestParam("quantity") int quantity, Model model) {
        model.addAttribute("error", false);
        model.addAttribute("books", libraryServices.getLibrary());
        model.addAttribute("booksOnSale", fiveBooksSale());
        model.addAttribute("cart", cart.getCart());
        Book newBook = libraryServices.findBookById(id);
        if (quantity <= 0 || quantity > newBook.getQuantity()) {
            model.addAttribute("error", true);
            return "userPage";
        }
        boolean flag = false;
        for (CartItem item:cart.getCart()){
            if(item.getBook().getId().equals(id)){
                int newQuantity = item.getQuantity() + quantity;
                System.out.println(newQuantity);
                cart.delete(id);
                CartItem newItem = new CartItem(newBook, newQuantity);
                cart.add(newItem);
                flag = true;
            }
        }
        if(!flag){
            CartItem item = new CartItem(newBook, quantity);
            cart.add(item);
        }
        model.addAttribute("cart",cart.getCart());
        model.addAttribute("books", libraryServices.getLibrary());
        return "redirect:/";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for deleting from cart action then redirect to cart page.
     * @param id id
     * @return redirect to cart page
     */
    @RequestMapping("/deleteFromCart")
    public RedirectView deleteFromCart(@RequestParam("id") Long id) {
        cart.delete(id);
        return new RedirectView("/cart");
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for delete all from the cart action, then redirect to cart page.
     * @return cart page
     */
    @RequestMapping("/deleteAll")
    public RedirectView deleteAll(){
        cart.deleteAll();
        return new RedirectView("/cart");
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
