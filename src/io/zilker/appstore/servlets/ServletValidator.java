package io.zilker.appstore.servlets;

import java.util.regex.*;

import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.constants.RegularExpressions;
import io.zilker.appstore.controller.Validator;
import io.zilker.appstore.exceptions.*;
import java.util.logging.Logger;

public class ServletValidator {
	
	static Logger LOGGER;
	
	public ServletValidator() {
		LOGGER = Logger.getLogger(ServletValidator.class.getName());
	}

	public boolean isValidated(String text, String regex) {
		if(text == null)
			return false;
		return Pattern.matches(regex, text);
	}
	
	public void checkName(String name) throws Exception {
		LOGGER.info("Entered checkName");
		if(!this.isValidated(name, RegularExpressions.APP_NAME)) {
			LOGGER.info(Errors.NAME_STRUC_MISMATCH);
			throw new BusinessException(Errors.NAME_STRUC_MISMATCH);
		}
		LOGGER.info("Exited checkName");
	}
	
	public void checkDescription(String name) throws Exception {
		LOGGER.info("Entered checkDescription");
		if(!this.isValidated(name, RegularExpressions.DESCRIPTION)) {
			LOGGER.info(Errors.NAME_STRUC_MISMATCH);
			throw new BusinessException(Errors.NAME_STRUC_MISMATCH);
		}
		LOGGER.info("Exited checkDescription");
	}
	
	public void checkCost(String cost) throws Exception {
		LOGGER.info("Entered checkCost");
		try {
			if(Integer.parseInt(cost) > 1000 || Integer.parseInt(cost) < 0)
				throw new BusinessException(Errors.COST_ERROR);
		} catch(Exception e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(Errors.COST_ERROR);
		} finally {
			LOGGER.info("Exited checkCost");
		}
	}
	
	public void checkID(String id) throws Exception {
		LOGGER.info("Entered checkID");
		try {
			if(Integer.parseInt(id) < 0 || Integer.parseInt(id) > 1000)
				throw new BusinessException(Errors.INVALID_APP_ID);
		} catch(Exception e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} finally {
			LOGGER.info("Exited checkCost");
		}
	}
	
}
