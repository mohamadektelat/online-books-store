//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Controllers;

//----------------------------------------------------------------------------------------------------------------------

import hac.ex4.Model.Book;
import hac.ex4.Service.LibraryServices;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;
import javax.validation.Valid;

//----------------------------------------------------------------------------------------------------------------------

/**
 * BookResource Controller
 */
@Controller
public class BookResourceController {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * library service
     */
    private final LibraryServices libraryServices;


    //------------------------------------------------------------------------------------------------------------------

    /**
     * start of BookResourceController
     * @param libraryServices library service
     */
    public BookResourceController(LibraryServices libraryServices) {
        this.libraryServices = libraryServices;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for showing the admin page.
     * @param book book
     * @param model model
     * @return admin page
     */
    @RequestMapping("/admin")
    public String getAllBooks (Book book, Model model) {
        model.addAttribute("books", libraryServices.getLibrary());
        model.addAttribute("error", false);
        return "adminPage";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for add book action.
     * @param book book
     * @param result result
     * @param model model
     * @return add book form
     */
    @PostMapping(value = "/admin/addBookAction")
    public String addBook1( @Valid Book book,BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addBookForm";
        }
        libraryServices.addBook(book);
        model.addAttribute("books", libraryServices.getLibrary());
        model.addAttribute("error", false);
        return "redirect:/admin";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for showing the add book form.
     * @param model model
     * @return add book form
     */
    @GetMapping("/admin/addBookAction")
    public String addBook2Page(Model model) {
        model.addAttribute("book", new Book());
        return "addBookForm";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * to add book we redirect to /admin/add2 to keep the path clean.
     * @return redirect view.
     */
    @PostMapping(value = "/admin/add")
    public RedirectView addBook2() {
        return new RedirectView("/admin/addBookAction");
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for updating book action.
     * @param id id
     * @param book book
     * @param result result
     * @param model model
     * @return update form
     */
    @PostMapping("/admin/update")
    public String updateBookPage(@RequestParam("id") Long id,@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Book bookToBeUpdated = libraryServices.findBookById(id);

            book.setId(id);
            model.addAttribute("book", book);
            model.addAttribute("error", true);
            return "updateBookForm";
        }
        model.addAttribute("books", libraryServices.getLibrary());
        model.addAttribute("error", false);
        Book toBeUpdated = libraryServices.findBookById(id);
        toBeUpdated.setName(book.getName());
        toBeUpdated.setImage(book.getImage());
        toBeUpdated.setQuantity(book.getQuantity());
        toBeUpdated.setPrice(book.getPrice());
        toBeUpdated.setDiscount(book.getDiscount());
        libraryServices.addBook(toBeUpdated);
        return "redirect:/admin";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for showing the update form.
     * @param id id
     * @param model model
     * @return update book form
     */
    @GetMapping("/admin/update/{id}")
    public String updateBookPage(@PathVariable Long id, Model model) {
        Book bookToBeUpdated = libraryServices.findBookById(id);
        //model.addAttribute("b", bookToBeUpdated);
        model.addAttribute("book", bookToBeUpdated);
        return "updateBookForm";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for redirecting to update book form to keep the url clean.
     * @param id id
     * @return redirect admin page
     */
    @RequestMapping("admin/updateButtonClicked")
    public RedirectView updateButton(@RequestParam("bookId") Long id) {
        return new RedirectView("/admin/update/" + id);
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This function is responsible for redirecting to update book form to keep the url clean.
     * @param id id
     * @return redirect admin page
     */
    @RequestMapping("admin/deleteButtonClicked")
    public RedirectView deleteButton(@RequestParam("bookId") Long id) {
        libraryServices.deleteBook(id);
        return new RedirectView("/admin");
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------
