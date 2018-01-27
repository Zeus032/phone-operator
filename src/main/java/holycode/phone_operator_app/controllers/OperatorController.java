package holycode.phone_operator_app.controllers;

import holycode.phone_operator_app.requests.CallSearchRequest;
import holycode.phone_operator_app.services.OperatorService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for handling operator requests.
 * 
 * @author Aleksandar Komarica
 *
 */
@Controller
public class OperatorController {

	private static final String CALLS_PAGE = "calls";
	private static final String ERROR_MESSAGE_REQUIRED_FIELDS = "Please provide all of the required fields.";

	@Autowired
	private OperatorService operatorService;

	/**
	 * Render calls page.
	 * 
	 * @return
	 */
	@GetMapping("/calls")
	public String renderCallsPage() {
		return CALLS_PAGE;
	}

	/**
	 * Search for calls.
	 * 
	 * @param callSearchRequest
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/search")
	public String search(@Valid CallSearchRequest callSearchRequest, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", ERROR_MESSAGE_REQUIRED_FIELDS);
			return CALLS_PAGE;
		}
		model.addAttribute("callSearchResponses", operatorService.populateCallSearchResponses(callSearchRequest));
		return CALLS_PAGE;
	}

}