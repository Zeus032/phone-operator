package holycode.phone_operator_app.requests;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Request object for calls search.
 * 
 * @author Aleksandar Komarica
 *
 */
public class CallSearchRequest {

	@NotBlank
	private String phoneNumber;

	private String dateFrom;

	private String dateTo;

	@NotBlank
	private String callDirection;

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom
	 *            the dateFrom to set
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo
	 *            the dateTo to set
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the callDirection
	 */
	public String getCallDirection() {
		return callDirection;
	}

	/**
	 * @param callDirection
	 *            the callDirection to set
	 */
	public void setCallDirection(String callDirection) {
		this.callDirection = callDirection;
	}

}