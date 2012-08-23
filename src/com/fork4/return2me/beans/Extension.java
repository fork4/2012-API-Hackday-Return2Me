package com.fork4.return2me.beans;

public class Extension {
	final String repPhone;
	final String custPhone;
	final String extension;
	final boolean oneTimeUse;
	final boolean hasCustomerPhone;
	
	public Extension(String repPhone, String extension, String custPhone, boolean oneTimeUse) {
		super();
		this.repPhone = repPhone.trim();
		if(custPhone != null && custPhone.trim().length() > 0) {
			this.custPhone = custPhone.trim();
			this.hasCustomerPhone = true;
		} else {
			this.custPhone = "N/A";
			this.hasCustomerPhone = false;			
		}
		this.extension = extension.trim();
		this.oneTimeUse = oneTimeUse;
	}
	

	public String getRepPhone() {
		return repPhone;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public String getExtension() {
		return extension;
	}
	
	public boolean isOneTimeUse() {
		return oneTimeUse;
	}
	
	public boolean hasCustomerPhone() {
		return hasCustomerPhone;
	}


}
