package hac.ex4.Controllers;

//----------------------------------------------------------------------------------------------------------------------

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;
import org.unbescape.html.HtmlEscape;
import javax.servlet.http.HttpServletRequest;

//----------------------------------------------------------------------------------------------------------------------

/**
 * controller of the authintications
 */
@Controller
public class MainController {

    //------------------------------------------------------------------------------------------------------------------

    /**
     * error exeptions
     */
    @RequestMapping("/simulateError")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * when there is an error we return error page
     * @param request request
     * @param model model
     * @return error
     */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * when there is a page without access we return 403
     * @return 403 error
     */
    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * we return login page when try to get /admin page
     * @return admin
     */
    @RequestMapping("/login")
    public RedirectView login() {
        System.out.println("get inside /login in maincontroller");
        return new RedirectView("/admin");
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * the logout page do redirect to landing page
     * @return /
     */
    @RequestMapping("/logout")
    public RedirectView logout() {
        // reset the seassion
        System.out.println("get inside /logout");
        return new RedirectView("/");
    }

    //------------------------------------------------------------------------------------------------------------------

}

//----------------------------------------------------------------------------------------------------------------------