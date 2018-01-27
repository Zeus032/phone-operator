package holycode.phone_operator_app.services.impl;

import holycode.phone_operator_app.requests.CallSearchRequest;
import holycode.phone_operator_app.responses.CallSearchResponse;
import holycode.phone_operator_app.services.OperatorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Implementation class for {@link OperatorService}.
 * 
 * @author Aleksandar Komarica
 *
 */
@Service
public class OperatorServiceImpl implements OperatorService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see holycode.phone_operator_app.services.OperatorService#
	 * populateCallSearchResponses
	 * (holycode.phone_operator_app.requests.CallSearchRequest)
	 */
	@Override
	public List<CallSearchResponse> populateCallSearchResponses(CallSearchRequest callSearchRequest) {
		List<CallSearchResponse> callSearchResponses = new ArrayList<>();
		callSearchResponses.add(new CallSearchResponse("111", new Date()));
		callSearchResponses.add(new CallSearchResponse("222", new Date()));
		callSearchResponses.add(new CallSearchResponse("333", new Date()));
		return callSearchResponses;
	}

}