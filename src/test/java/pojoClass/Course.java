package pojoClass;

import java.util.List;

public class Course {
	
	private List<WebAutomation> webAutomation;
	private List<APIAutomation> api;
	private List<MobileAutomation> mobile;
	
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<APIAutomation> getApi() {
		return api;
	}
	public void setApi(List<APIAutomation> api) {
		this.api = api;
	}
	public List<MobileAutomation> getMobile() {
		return mobile;
	}
	public void setMobile(List<MobileAutomation> mobile) {
		this.mobile = mobile;
	}
	
	

	
	
	
	
	

}
