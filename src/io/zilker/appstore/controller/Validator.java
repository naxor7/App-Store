package io.zilker.appstore.controller;

import java.util.regex.*;

import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.constants.RegularExpressions;
import io.zilker.appstore.exceptions.*;
import java.util.logging.Logger;
import io.zilker.appstore.beans.*;

public class Validator {

	private static Validator controllerValidator = new Validator();;
	private Logger LOGGER;

	public Validator() {
		LOGGER = Logger.getLogger(Validator.class.getName());
	}
	
	public boolean isValidated(String text, String regex) {
		if(text == null)
			return false;
		return Pattern.matches(regex, text);
	}
	
	public void checkID(int id) throws Exception {
		if(!controllerValidator.isValidated(String.valueOf(id), RegularExpressions.ID))
			throw new Exception(Errors.INVALID_APP_ID);
	}
	
	public void checkUserName(String name) throws Exception {
		LOGGER.info("Entered checkUserName");
		if(!controllerValidator.isValidated(name, RegularExpressions.USER_NAME)) {
			LOGGER.info(Errors.INVALID_USERNAME_PASSWORD);
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		}
		LOGGER.info("Exited checkUserName");
	}
	
	public void checkEmail(String email) throws Exception {
		LOGGER.info("Entered checkEmail");
		if(!controllerValidator.isValidated(email, RegularExpressions.EMAIL)) {
			LOGGER.info(Errors.INVALID_EMAIL);
			throw new BusinessException(Errors.INVALID_EMAIL);
		}
		LOGGER.info("Exited checkEmail");
	}
	
	public void checkName(String name) throws Exception {
		LOGGER.info("Entered checkName");
		if(!controllerValidator.isValidated(name, RegularExpressions.APP_NAME)) {
			LOGGER.info(Errors.NAME_STRUC_MISMATCH);
			throw new BusinessException(Errors.NAME_STRUC_MISMATCH);
		}
		LOGGER.info("Exited checkName");
	}
	
	public void checkPassword(String password) throws Exception {
		LOGGER.info("Entered checkPassword");
		if(!controllerValidator.isValidated(password, RegularExpressions.PASSWORD)) {
			LOGGER.info(Errors.INVALID_USERNAME_PASSWORD);
			throw new BusinessException(Errors.INVALID_USERNAME_PASSWORD);
		}
		LOGGER.info("Exited checkPassword");
	}
	
}
