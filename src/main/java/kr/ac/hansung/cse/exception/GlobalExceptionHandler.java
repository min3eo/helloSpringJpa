package kr.ac.hansung.cse.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProductNotFound(ProductNotFoundException e, Model model) {
        model.addAttribute("errorCode", 404);
        model.addAttribute("errorTitle", "\uC0C1\uD488\uC744 \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4");
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorDetail", "\uC694\uCCAD\uD55C \uC0C1\uD488\uC774 \uC874\uC7AC\uD558\uC9C0 \uC54A\uAC70\uB098 \uC774\uBBF8 \uC0AD\uC81C\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFound(NoHandlerFoundException e, Model model) {
        model.addAttribute("errorCode", 404);
        model.addAttribute("errorTitle", "\uD398\uC774\uC9C0\uB97C \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4");
        model.addAttribute("errorMessage", e.getHttpMethod() + " " + e.getRequestURL());
        model.addAttribute("errorDetail", "\uC694\uCCAD\uD55C \uACBD\uB85C\uAC00 \uC874\uC7AC\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException e, Model model) {
        model.addAttribute("errorCode", 400);
        model.addAttribute("errorTitle", "\uC798\uBABB\uB41C \uC694\uCCAD\uC785\uB2C8\uB2E4");
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorDetail", "\uC785\uB825 \uAC12\uC744 \uB2E4\uC2DC \uD655\uC778\uD55C \uD6C4 \uC7AC\uC2DC\uB3C4\uD574 \uC8FC\uC138\uC694.");
        return "error";
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDataAccess(DataAccessException e, Model model) {
        model.addAttribute("errorCode", 500);
        model.addAttribute("errorTitle", "\uB370\uC774\uD130 \uCC98\uB9AC \uC911 \uC624\uB958\uAC00 \uBC1C\uC0DD\uD588\uC2B5\uB2C8\uB2E4");
        model.addAttribute("errorMessage", e.getMostSpecificCause() != null
                ? e.getMostSpecificCause().getMessage()
                : e.getMessage());
        model.addAttribute("errorDetail", "\uC7A0\uC2DC \uD6C4 \uB2E4\uC2DC \uC2DC\uB3C4\uD574 \uC8FC\uC138\uC694.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorCode", 500);
        model.addAttribute("errorTitle", "\uC608\uAE30\uCE58 \uBABB\uD55C \uC624\uB958\uAC00 \uBC1C\uC0DD\uD588\uC2B5\uB2C8\uB2E4");
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorDetail", "\uBB38\uC81C\uAC00 \uACC4\uC18D\uB418\uBA74 \uAD00\uB9AC\uC790\uC5D0\uAC8C \uBB38\uC758\uD574 \uC8FC\uC138\uC694.");
        return "error";
    }
}
