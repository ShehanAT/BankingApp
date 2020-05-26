package co.spraybot.dto;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class BankLocationDTO {
	@NotEmpty
	private String locationName;
	@NotEmpty
	private String address;
	@NotNull
	private int customerId;
	
	private boolean enabled;

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
