package io.zilker.appstore.dao;

import java.sql.*;
import java.util.logging.Logger;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.*;
import io.zilker.appstore.dbutil.*;
import io.zilker.appstore.exceptions.*;

public class UserDao {

	private Connection conn;
	private PreparedStatement query;
	private ResultSet result;
	private Connections connections;
	private AppsDao appsDao;
	static Logger LOGGER;
	private UserDao userDao;

	public UserDao() {
		LOGGER = Logger.getLogger(UserDao.class.getName());
		appsDao = new AppsDao();
		connections = new Connections();
	}
	
	public boolean isUser(GenericUser user) throws Exception {
		LOGGER.info("Entered isUser");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_WITH_USERNAME_AND_PASSWORD);
			query.setString(1, user.getUserName());
			query.setString(2, user.getPassword());
			result = query.executeQuery();
			if (result.next()) {
				return true;
			}
		}catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting isUser");
		}
		return false;
	}
	
	public GenericUser getUser(GenericUser user) throws Exception {
		LOGGER.info("Entered getUser");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_WITH_USERNAME_AND_PASSWORD);
			query.setString(1, user.getUserName());
			query.setString(2, user.getPassword());
			result = query.executeQuery();
			if (result.next()) {
				user.setFullName(result.getString("FullName"));
				user.setUserID(result.getInt("UserID"));
				user.setUserPrivilege(result.getString("UserPrivilege"));
				user.setEmail(result.getString("EmailID"));
				return user;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting getUser");
		}
		return null;
	}
	
	public GenericUser getUserInfo(GenericUser user) throws Exception {
		LOGGER.info("Entered getUserInfo");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_WITH_ID);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			if (result.next()) {
				user.setFullName(result.getString("FullName"));
				user.setUserID(result.getInt("UserID"));
				user.setUserPrivilege(result.getString("UserPrivilege"));
				user.setEmail(result.getString("EmailID"));
				user.setUserName(result.getString("UserName"));
			}
		}catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting getUserInfo");
		}
		return user;
	}
	
	public boolean checkUserName(GenericUser user) throws Exception {
		LOGGER.info("Entered checkUserName");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_WITH_USERNAME);
			query.setString(1, user.getUserName());
			result = query.executeQuery();
			if (!result.next()) {
				return false;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting checkUserName");
		}
		return true;
	}

	public boolean insertUser(GenericUser user) throws Exception {
		LOGGER.info("Entered insertUser");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_USER);
			query.setString(1, user.getUserName());
			query.setString(2, user.getPassword());
			query.setString(3, "u");
			query.setString(4, user.getFullName());
			query.setString(5, user.getEmail());
			int status = query.executeUpdate();
			if (status > 0) {
				userDao = new UserDao();
				user = userDao.getUser(user);
				insertWallet(user);
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting insertUser");
		}
		return false;
	}
	
	public void insertWallet(GenericUser user) throws Exception {
		LOGGER.info("Entered insertWallet");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_WALLET);
			query.setInt(1, user.getUserID());
			query.setInt(2, 0);
			int status = query.executeUpdate();
			if(status <= 0) 
				throw new SystemException(Errors.SQL_EXCEPTION);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting insertWallet");
		}
	}
	
	public boolean updateFullName(GenericUser user) throws Exception {
		LOGGER.info("Entered updateFullName");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_FULLNAME);
			query.setString(1, user.getFullName());
			query.setInt(2, user.getUserID());
			int status = query.executeUpdate();
			if (status > 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting updateFullName");
		}
		return false;
	}
	
	public boolean updateEmail(GenericUser user) throws Exception {
		LOGGER.info("Entered updateEmail");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_EMAIL);
			query.setString(1, user.getEmail());
			query.setInt(2, user.getUserID());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting updateEmail");
		}
		return false;
	}
	
	public boolean updateUserName(GenericUser user) throws Exception {
		LOGGER.info("Entered updateUserName");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_USERNAME);
			query.setString(1, user.getUserName());
			query.setInt(2, user.getUserID());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting updateUserName");
		}
		return false;
	}

	public boolean updatePassword(GenericUser user) throws Exception {
		LOGGER.info("Entered updatePassword");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_PASSWORD);
			query.setString(1, user.getPassword());
			query.setInt(2, user.getUserID());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting updatePassword");
		}
		return false;
	}

	public boolean updateUserPrivilege(GenericUser user) throws Exception {
		LOGGER.info("Entered updateUserPrivilege");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_USER_PRIVILEGE);
			query.setString(1, user.getUserPrivilege());
			query.setInt(2, user.getUserID());
			int status = query.executeUpdate();
			if (status >= 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exiting updateUserPrivilege");
		}
		return false;
	}

	public boolean isTester(GenericUser user) throws Exception {
		LOGGER.info("Entered isTester");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.IS_TESTER);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			if(result.next())
				return true;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited isTester");
		}
		return false;
	}
	
	public boolean isAdmin(GenericUser user) throws Exception {
		LOGGER.info("Entered isAdmin");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.IS_ADMIN);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			if(result.next())
				return true;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited isAdmin");
		}
		return false;
	}
	
	public float averageUserReviews(GenericUser user) throws Exception {
		LOGGER.info("Entered averageUserReviews");
		float rating = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_USER_RATING);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			if (result.next())
				rating = result.getFloat(1);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited averageUserReviews");
		}
		return rating;
	}

	public int userPublishedAppCount(GenericUser user) throws Exception {
		LOGGER.info("Entered userPublishedAppCount");
		int apps = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_USER_ID);
			query.setInt(1, user.getUserID());
			result = query.executeQuery();
			result.last();
			apps = result.getRow();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited userPublishedAppCount");
		}
		return apps;
	}
	
}
