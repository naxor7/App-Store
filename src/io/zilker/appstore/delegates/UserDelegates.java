package io.zilker.appstore.delegates;

import io.zilker.appstore.dao.*;
import io.zilker.appstore.exceptions.*;
import java.util.logging.Logger;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;

public class UserDelegates {

	private UserDao userDao;
	static Logger LOGGER;

	public UserDelegates() {
		userDao = new UserDao();
		LOGGER = Logger.getLogger(UserDelegates.class.getName());
	}

	public GenericUser userLogin(GenericUser user) throws Exception {
		try {
			LOGGER.info("Entered userLogin");
			user = userDao.getUser(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited userLogin");
		}
		return user;
	}

	public GenericUser getUserInfo(GenericUser user) throws Exception {
		try {
			LOGGER.info("Entered getUserInfo");
			return userDao.getUserInfo(user);
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited userLogin");
		}
	}

	public boolean checkUserName(GenericUser user) throws Exception {
		LOGGER.info("Entered checkUserName");
		try {
			return userDao.checkUserName(user);
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

	public boolean registerUser(GenericUser user) throws Exception {
		LOGGER.info("Entered registerUser");
		try {
			if (!userDao.checkUserName(user)) {
				if (!userDao.insertUser(user)) {
					LOGGER.info(Errors.DATABASE_ERR);
					throw new SystemException(Errors.DATABASE_ERR);
				}
			} else {
				LOGGER.info(Errors.USERNAME_EXISTS);
				throw new BusinessException(Errors.USERNAME_EXISTS);
			}
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited registerUser");
		}
		return true;
	}

	public boolean isUser(GenericUser user) throws Exception {
		LOGGER.info("Entered isUser");
		try {
			if (!userDao.isUser(user)) {
				throw new BusinessException(Errors.INVALID_PASSWORD);
			}
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited isUser");
		}
		return true;
	}

	public boolean changeFullName(GenericUser user) throws Exception {
		LOGGER.info("Entered changeFullName");
		try {
			if (!userDao.isUser(user)) {
				throw new BusinessException(Errors.INVALID_PASSWORD);
			}
			return userDao.updateFullName(user);
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
			if (!userDao.isUser(user)) {
				throw new BusinessException(Errors.INVALID_PASSWORD);
			}
			return userDao.updateEmail(user);
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
			if (!userDao.checkUserName(user)) {
				return userDao.updateUserName(user);
			} else
				throw new BusinessException(Errors.USERNAME_EXISTS);
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
			return userDao.updatePassword(user);
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
			return userDao.updateUserPrivilege(user);
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
			return userDao.isTester(user);
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
			return userDao.isAdmin(user);
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
			return userDao.userPublishedAppCount(user);
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
			return userDao.averageUserReviews(user);
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
