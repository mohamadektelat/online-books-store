//----------------------------------------------------------------------------------------------------------------------

package hac.ex4.Service;

//----------------------------------------------------------------------------------------------------------------------

import hac.ex4.Exeptions.bookNotFoundExeption;
import hac.ex4.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hac.ex4.repo.Library;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------

/**
 * LibraryServices
 */
@Service
public class LibraryServices {

    //------------------------------------------------------------------------------------------------------------------
    /**
     * library
     */
    private final Library library;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * LibraryServices
     * @param library library
     */
    @Autowired
    public LibraryServices( Library library) {
        this.library = library;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * addBook
     * @param book book
     * @return success
     */
    public Book addBook(Book book) {
        return library.save(book);
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * getLibrary
     * @return all books
     */
    public List<Book> getLibrary() {
        return library.findAll();
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * deleteBook
     * @param id id
     */
    public void deleteBook(Long id){ library.deleteById(id); }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * findBookById
     * @param id id
     * @return book
     */
    public Book findBookById(Long id) {
        return library.findBookById(id).orElseThrow(
                () -> new bookNotFoundExeption("cant find book :" + id));
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------