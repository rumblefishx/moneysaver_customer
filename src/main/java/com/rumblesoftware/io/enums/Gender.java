package com.rumblesoftware.io.enums;

/**
 * Enum responsible for identify user gender's
 * M : Male
 * F : Female
 * @author Cleiton
 *
 */
public enum Gender {
	M("M"),
	F("F");
	
	private Gender(String gender) {
		this.gender = gender;
	}
	
	private String gender;
	
	public static Gender castStringToEnum(String genderStr) {
		if(genderStr == null)
			return null;
		
		if(genderStr.trim().equalsIgnoreCase("m"))
			return M;
		
		if(genderStr.trim().equalsIgnoreCase("F"))
			return F;
		
		return null;
	}
	
	public String toString() {
		return this.gender;
	}
}
