package io.zilker.appstore.constants;

public class RegularExpressions {

	public static final String APP_NAME = "[a-zA-Z0-9 ]{1,20}";
	public static final String ID = "[0-9]{1,20}";
	public static final String USER_NAME = "[a-zA-Z0-9]{0,20}";
	public static final String NAME = "[a-zA-Z ]{0,20}";
	public static final String PASSWORD = ".{6,20}";
	public static final String COMMENT = ".{1,100}";
	public static final String DESCRIPTION = ".{0,100}";
	public static final String COST = "[0-9]{0,8}"; 
	public static final String EMAIL = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	
}
