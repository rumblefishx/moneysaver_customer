package com.rumblesoftware.enums;

public enum ExternalIdType {
	FaceBook("FaceBook"),
	Google("Google");
	

	private ExternalIdType(String description){
		this.description = description;
	}
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
