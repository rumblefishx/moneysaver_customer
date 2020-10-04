package com.rumblesoftware.enums;

public enum ExternalTokenType {
	FaceBook(1,"FaceBook"),
	Google(0,"Google");	

	private ExternalTokenType(Integer id,String description){
		this.id = id;
		this.description = description;
	}
	
	private String description;
	private Integer id;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static ExternalTokenType castIntToEnum(Integer id) {
		for(ExternalTokenType type : ExternalTokenType.values()) {
			if(type.getId().equals(id))
				return type;
		}
		return null;
	}
	
	
}
