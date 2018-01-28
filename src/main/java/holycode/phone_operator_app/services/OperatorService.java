package holycode.phone_operator_app.services;

import holycode.phone_operator_app.requests.CallSearchRequest;
import holycode.phone_operator_app.responses.CallSearchResponse;

import java.util.List;

/**
 * Interface for handling operator services.
 * 
 * @author Aleksandar Komarica
 *
 */
public interface OperatorService {

	/**
	 * Populate {@link CallSearchResponse} list.
	 * 
	 * @param callSearchRequest
	 * @return
	 */
	List<CallSearchResponse> populateCallSearchResponses(CallSearchRequest callSearchRequest);

    /**
     * Insert data from CSV
     */
	String initializeDB();
	
	

}
