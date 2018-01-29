package holycode.phone_operator_app.responses;

import java.util.Date;

/**
 * Response object for calls search.
 * 
 * @author Aleksandar Komarica
 *
 */
public class CallSearchResponse {

	private String numberCaller;
	
	private String numberReciever;
	
	private String duration;

	private Date callDate;
	
	private String userCaller;
	
	private String addressCaller;
    
    private String userReciever;
    
    private String addressReciever;

    public String getNumberCaller() {
      return numberCaller;
    }

    public void setNumberCaller(String numberCaller) {
      this.numberCaller = numberCaller;
    }

    public String getNumberReciever() {
      return numberReciever;
    }

    public void setNumberReciever(String numberReciever) {
      this.numberReciever = numberReciever;
    }

    public String getDuration() {
      return duration;
    }

    public void setDuration(String duration) {
      this.duration = duration;
    }

    public Date getCallDate() {
      return callDate;
    }

    public void setCallDate(Date callDate) {
      this.callDate = callDate;
    }

    public String getUserCaller() {
      return userCaller;
    }

    public void setUserCaller(String userCaller) {
      this.userCaller = userCaller;
    }

    public String getAddressCaller() {
      return addressCaller;
    }

    public void setAddressCaller(String addressCaller) {
      this.addressCaller = addressCaller;
    }

    public String getUserReciever() {
      return userReciever;
    }

    public void setUserReciever(String userReciever) {
      this.userReciever = userReciever;
    }

    public String getAddressReciever() {
      return addressReciever;
    }

    public void setAddressReciever(String addressReciever) {
      this.addressReciever = addressReciever;
    }

    public CallSearchResponse(String numberCaller, String numberReciever, String duration, Date callDate,
        String userCaller, String addressCaller, String userReciever, String addressReciever) {
      super();
      this.numberCaller = numberCaller;
      this.numberReciever = numberReciever;
      this.duration = duration;
      this.callDate = callDate;
      this.userCaller = userCaller;
      this.addressCaller = addressCaller;
      this.userReciever = userReciever;
      this.addressReciever = addressReciever;
    }
	
	


}