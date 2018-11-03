package io.zilker.appstore.controller;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.constants.RegularExpressions;
import io.zilker.appstore.delegates.*;
import io.zilker.appstore.exceptions.BusinessException;
import io.zilker.appstore.exceptions.SystemException;

import java.util.logging.Logger;

public class UserController {

	private Validator validator;
	private UserDelegates userDelegates;
	static Logger LOGGER;

	public UserController() {
		validator = new Validator();
		userDelegates = new UserDelegates();
		LOGGER = Logger.getLogger(UserController.class.getName());
	}

	public GenericUser userLogin(GenericUser user) throws Exception {
		LOGGER.info("Entered userLogin");
		try {
			validator.checkUserName(user.getUserName());
			validator.checkPassword(user.getPassword());
			return userDelegates.userLogin(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited userLogin");
		}
	}

	public GenericUser getUserInfo(GenericUser user) throws Exception {
		try {
			LOGGER.info("Entered getUserInfo");
			validator.checkID(user.getUserID());
			return userDelegates.getUserInfo(user);
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited userLogin");
		}
	}
	
	public boolean checkUserName(GenericUser user) throws Exception {
		try {
			LOGGER.info("Entered checkUserName");
			return userDelegates.checkUserName(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited checkUserName");
		}
	}

	public boolean userRegister(GenericUser user) throws Exception {
		LOGGER.info("Entered userRegister");
		try {
			if (!validator.isValidated(user.getFullName(), RegularExpressions.NAME)) {
				throw new BusinessException(Errors.NAME_STRUC_MISMATCH);
			}
			validator.checkName(user.getFullName());
			validator.checkEmail(user.getEmail());
			validator.checkUserName(user.getUserName());
			validator.checkPassword(user.getPassword());
			validator.isValidated(user.getEmail(), RegularExpressions.EMAIL);
			return userDelegates.registerUser(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited userRegister");
		}
	}

	public boolean isUser(GenericUser user) throws Exception {
		LOGGER.info("Entered isUser");
		try {
			validator.checkID(user.getUserID());
			validator.checkUserName(user.getUserName());
			validator.checkPassword(user.getPassword());
			return userDelegates.isUser(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited isUser");
		}
	}
	
	public boolean changeFullName(GenericUser user) throws Exception {
		LOGGER.info("Entered changeFullName");
		try {
			validator.checkID(user.getUserID());
			validator.checkUserName(user.getUserName());
			return userDelegates.changeFullName(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited changeFullName");
		}
	}
	
	public boolean changeEmail(GenericUser user) throws Exception {
		LOGGER.info("Entered changeEmail");
		try {
			validator.checkID(user.getUserID());
			validator.checkUserName(user.getUserName());
			validator.checkEmail(user.getEmail());
			return userDelegates.changeEmail(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited changeEmail");
		}
	}
	
	public boolean changeUserName(GenericUser user) throws Exception {
		LOGGER.info("Entered changeUserName");
		try {
			validator.checkID(user.getUserID());
			validator.checkUserName(user.getUserName());
			return userDelegates.changeUserName(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited changeUserName");
		}
	}

	public boolean changePassword(GenericUser user) throws Exception {
		LOGGER.info("Entered changePassword");
		try {
			validator.checkID(user.getUserID());
			validator.checkUserName(user.getUserName());
			validator.checkPassword(user.getPassword());
			return userDelegates.changePassword(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited changePassword");
		}
	}

	public boolean changeUserPrivilege(GenericUser user) throws Exception {
		LOGGER.info("Entered changeUserPrivilege");
		try {
			validator.checkID(user.getUserID());
			validator.checkUserName(user.getUserName());
			if (user.getUserPrivilege().compareTo("u") != 0 && user.getUserPrivilege().compareTo("d") != 0) {
				throw new BusinessException(Errors.PRIVILAGE_STRUC_MISMATCH);
			}
			return userDelegates.changeUserPrivilege(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited changeUserPrivilege");
		}
	}
	
	public boolean isTester(GenericUser user) throws Exception {
		LOGGER.info("Entered isTester");
		try {
			validator.checkID(user.getUserID());
			return userDelegates.isTester(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited isTester");
		}
	}
	
	public boolean isAdmin(GenericUser user) throws Exception {
		LOGGER.info("Entered isAdmin");
		try {
			validator.checkID(user.getUserID());
			return userDelegates.isAdmin(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited isAdmin");
		}
	}
	
	public int userPublishedAppCount(GenericUser user) throws Exception {
		LOGGER.info("Entered userPublishedAppCount");
		try {
			validator.checkID(user.getUserID());
			return userDelegates.userPublishedAppCount(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited userPublishedAppCount");
		}
	}
	
	public float averageUserReviews(GenericUser user) throws Exception {
		LOGGER.info("Entered averageUserReviews");
		try {
			validator.checkID(user.getUserID());
			return userDelegates.averageUserReviews(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited averageUserReviews");
		}
	}

}
