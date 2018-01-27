package holycode.phone_operator_app.responses;

import java.util.Date;

/**
 * Response object for calls search.
 * 
 * @author Aleksandar Komarica
 *
 */
public class CallSearchResponse {

	private String phoneNumber;

	private Date callDate;

	/**
	 * @param phoneNumber
	 * @param callDate
	 */
	public CallSearchResponse(String phoneNumber, Date callDate) {
		this.phoneNumber = phoneNumber;
		this.callDate = callDate;
	}

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
	 * @return the callDate
	 */
	public Date getCallDate() {
		return callDate;
	}

	/**
	 * @param callDate
	 *            the callDate to set
	 */
	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

}