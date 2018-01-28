package holycode.phone_operator_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import holycode.phone_operator_app.services.OperatorService;

/**
 * Controller for handling index requests.
 * 
 * @author Aleksandar Komarica
 *
 */
@Controller
public class IndexController {
  
    /**
     * OperatorService bean
     */
    @Autowired
    OperatorService operatorService;

	/**
	 * Render index page.
	 * 
	 * @return
	 */
	@GetMapping(value = { "/", "/index" })
	public String renderIndexPage() {
	  return "index";
	}
	
	@PostMapping(value = { "/", "/index" })
	public String initDB() {
	  operatorService.initializeDB();
	  
	  return "index";
	}

}