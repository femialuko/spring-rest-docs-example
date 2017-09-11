package ng.babafemi.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author babafemi
 */
@Controller
public class HomeResource {
    @RequestMapping("/")
    public String docs() {
        return "docs/index.html";
    }
}
