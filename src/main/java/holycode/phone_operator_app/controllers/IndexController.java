package holycode.phone_operator_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling index requests.
 * 
 * @author Aleksandar Komarica
 *
 */
@Controller
public class IndexController {

	/**
	 * Render index page.
	 * 
	 * @return
	 */
	@GetMapping(value = { "/", "/index" })
	public String renderIndexPage() {
		return "index";
	}

}