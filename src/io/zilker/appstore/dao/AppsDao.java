package io.zilker.appstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.DatabaseQueries;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.dbutil.Connections;
import io.zilker.appstore.exceptions.BusinessException;
import io.zilker.appstore.exceptions.SystemException;

public class AppsDao {

	private Connection conn;
	private PreparedStatement query;
	private ResultSet result;
	private Connections connections;
	static Logger LOGGER;

	public AppsDao() {
		LOGGER = Logger.getLogger(AppsDao.class.getName());
		connections = new Connections();
	}

	public Applications[] populateApps(ResultSet result, Applications[] apps) throws Exception {
		LOGGER.info("Entered populateApps");
		int index = 0;
		try {
			while (result.next()) {
				apps[index] = new Applications();
				apps[index].setAppID(result.getInt("Apps.AppID"));
				apps[index].setUserID(result.getInt("Apps.UserID"));
				apps[index].setAppName(result.getString("Apps.AppName"));
				apps[index].setDescription(result.getString("Apps.Description"));
				apps[index].setStatus(result.getString("Apps.Status"));
				apps[index].setCreatedAt(result.getString("Apps.Created_At"));
				apps[index].setUpdatedAt(result.getString("Apps.Updated_At"));
				apps[index].setCost(result.getInt("Apps.Cost"));
				PreparedStatement categoryQuery = conn
						.prepareStatement(DatabaseQueries.SELECT_CATEGORY_WITH_CATEGORY_ID);
				categoryQuery.setInt(1, result.getInt("Apps.CategoryID"));
				ResultSet categoryResult = categoryQuery.executeQuery();
				categoryResult.next();
				apps[index].getCategory().setCategoryID(result.getInt("Apps.CategoryID"));
				apps[index].getCategory().setCategoryName(categoryResult.getString("CategoryName"));
				PreparedStatement ratingQuery = conn.prepareStatement(DatabaseQueries.SELECT_APP_RATING);
				ratingQuery.setInt(1, result.getInt("Apps.AppID"));
				ResultSet ratingResult = ratingQuery.executeQuery();
				if (ratingResult.next())
					apps[index].setReview(ratingResult.getFloat(1));
				else
					apps[index].setReview(0);
				index++;
			}
		} catch (Exception e) {
			LOGGER.info(Errors.SQL_EXCEPTION);
			throw new SystemException(Errors.SQL_EXCEPTION);
		}
		LOGGER.info("Exited populateApps");
		return apps;
	}

	public Applications[] getAllApps() throws Exception {
		LOGGER.info("Entered getAllApps");
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_ALL_APPS);
			result = query.executeQuery();
			result.last();
			apps = new Applications[result.getRow()];
			result.beforeFirst();
			apps = populateApps(result, apps);
		} catch (Exception e) {
			LOGGER.info(Errors.SQL_EXCEPTION);
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getAllApps");
		}
		return apps;
	}

	public Applications[] getWishList(StoreUser user) throws Exception {
		LOGGER.info("Entered getWishList");
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.VIEW_WISHLIST);
			query.setInt(1, user.getGenUser().getUserID());
			result = query.executeQuery();
			result.last();
			apps = new Applications[result.getRow()];
			result.beforeFirst();
			apps = populateApps(result, apps);
		} catch (Exception e) {
			LOGGER.info(Errors.SQL_EXCEPTION);
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getWishList");
		}
		return apps;
	}

	public Applications[] getDownloads(StoreUser user) throws Exception {
		LOGGER.info("Entered getDownloads");
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_FROM_DOWNLOADS);
			query.setInt(1, user.getGenUser().getUserID());
			result = query.executeQuery();
			result.last();
			apps = new Applications[result.getRow()];
			result.beforeFirst();
			apps = populateApps(result, apps);
		} catch (Exception e) {
			LOGGER.info(Errors.SQL_EXCEPTION);
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getDownloads");
		}
		return apps;
	}

	public Applications[] getMyApps(StoreUser user) throws Exception {
		LOGGER.info("Entered getMyApps");
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_MY_APPS);
			query.setInt(1, user.getGenUser().getUserID());
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			LOGGER.info(Errors.SQL_EXCEPTION);
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getMyApps");
		}
		return apps;
	}

	public boolean hasAppInWishList(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered hasAppInWishList");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.HAS_APP_IN_WISHLIST);
			query.setInt(1, user.getGenUser().getUserID());
			query.setInt(2, app.getAppID());
			result = query.executeQuery();
			if (!result.next()) {
				return false;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited hasAppInWishList");
		}
		return true;
	}

	public void addAppToWishList(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered addAppToWishList");
		try {
			if (!hasAppInWishList(app, user)) {
				conn = connections.open();
				query = conn.prepareStatement(DatabaseQueries.INSERT_WISHLIST);
				query.setInt(1, user.getGenUser().getUserID());
				query.setInt(2, app.getAppID());
				int status = query.executeUpdate();
				if (status < 0)
					throw new BusinessException(Errors.NO_SUCH_APP);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited addAppToWishList");
		}
	}

	public boolean hasAppInDownloads(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered hasAppInDownloads");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.HAS_APP_IN_DOWNLOADS);
			query.setInt(1, user.getGenUser().getUserID());
			query.setInt(2, app.getAppID());
			result = query.executeQuery();
			if (!result.next()) {
				return false;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited hasAppInDownloads");
		}
		return true;
	}

	public Applications getAppInfo(Applications app) throws Exception {
		LOGGER.info("Entered getAppInfo");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.GET_APP_INFO);
			query.setInt(1, app.getAppID());
			result = query.executeQuery();
			if (result.next()) {
				app.setAppID(result.getInt("Apps.AppID"));
				app.setUserID(result.getInt("Apps.UserID"));
				app.setAppName(result.getString("Apps.AppName"));
				app.setDescription(result.getString("Apps.Description"));
				app.setStatus(result.getString("Apps.Status"));
				app.setCreatedAt(result.getString("Apps.Created_At"));
				app.setUpdatedAt(result.getString("Apps.Updated_At"));
				app.setCost(result.getInt("Apps.Cost"));
				PreparedStatement categoryQuery = conn
						.prepareStatement(DatabaseQueries.SELECT_CATEGORY_WITH_CATEGORY_ID);
				categoryQuery.setInt(1, result.getInt("Apps.CategoryID"));
				ResultSet categoryResult = categoryQuery.executeQuery();
				categoryResult.next();
				app.getCategory().setCategoryID(result.getInt("Apps.CategoryID"));
				app.getCategory().setCategoryName(categoryResult.getString("CategoryName"));
				PreparedStatement ratingQuery = conn.prepareStatement(DatabaseQueries.SELECT_APP_RATING);
				ratingQuery.setInt(1, result.getInt("Apps.AppID"));
				ResultSet ratingResult = ratingQuery.executeQuery();
				if (ratingResult.next())
					app.setReview(ratingResult.getFloat(1));
				else
					app.setReview(0);
			} else {
				throw new SystemException(Errors.SQL_EXCEPTION);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getAppInfo");
		}
		return app;
	}

	public boolean downloadApp(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered downloadApp");
		try {
			if (!hasAppInDownloads(app, user)) {
				conn = connections.open();
				query = conn.prepareStatement(DatabaseQueries.INSERT_DOWNLOADS);
				query.setInt(1, app.getAppID());
				query.setInt(2, user.getGenUser().getUserID());
				int status = query.executeUpdate();
				if (status >= 0)
					return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited downloadApp");
		}
		return false;
	}

	public void deleteAppFromWishList(StoreUser user, Applications app) throws Exception {
		LOGGER.info("Entered deleteAppFromWishList");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.DELETE_FROM_WISHLIST);
			query.setInt(1, user.getGenUser().getUserID());
			query.setInt(2, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.SQL_EXCEPTION);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited deleteAppFromWishList");
		}
	}

	public void deleteAppFromDownloads(StoreUser user, Applications app) throws Exception {
		LOGGER.info("Entered deleteApp");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.DELETE_APP);
			query.setInt(1, user.getGenUser().getUserID());
			query.setInt(2, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.SQL_EXCEPTION);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited deleteApp");
		}
	}

	public Comments[] getAllComments(Applications app) throws Exception {
		LOGGER.info("Entered getAllComments");
		Comments[] comments = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.GET_ALL_COMMENTS);
			query.setInt(1, app.getAppID());
			result = query.executeQuery();
			result.last();
			comments = new Comments[result.getRow()];
			result.beforeFirst();
			int index = 0;
			while (result.next()) {
				comments[index] = new Comments();
				comments[index].setAppID(result.getInt("AppID"));
				comments[index].setCommentID(result.getInt("CommentID"));
				comments[index].setCommentText(result.getString("CommentText"));
				comments[index].setUserID(result.getInt("UserID"));
				PreparedStatement statement = conn.prepareStatement(DatabaseQueries.GET_USER);
				statement.setInt(1, result.getInt("UserID"));
				ResultSet tempResult = statement.executeQuery();
				if (tempResult.next())
					comments[index].setUserName(tempResult.getString("UserName"));
				else
					comments[index].setUserName("user");
				index++;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getAllComments");
		}
		return comments;
	}

	public void reportApp(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered reportApp");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_REPORTED_BY_APPID_AND_USERID);
			query.setInt(1, app.getAppID());
			query.setInt(2, user.getGenUser().getUserID());
			result = query.executeQuery();
			if (result.next()) {
				throw new BusinessException(Errors.APP_ALREADY_REPORTED);
			}
			query = conn.prepareStatement(DatabaseQueries.REPORT_APP);
			query.setInt(1, app.getAppID());
			query.setInt(2, user.getGenUser().getUserID());
			int status = query.executeUpdate();
			if (status < 0)
				throw new SystemException(Errors.SQL_EXCEPTION);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(Errors.APP_ALREADY_REPORTED);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited reportApp");
		}
	}

	public boolean isReportedByUser(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered isReportedByUser");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_REPORTED_BY_APPID_AND_USERID);
			query.setInt(1, app.getAppID());
			query.setInt(2, user.getGenUser().getUserID());
			result = query.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited isReportedByUser");
		}
		return false;
	}

	public int getRatingByUser(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered getRatingByUser");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.GET_REVIEWS);
			query.setInt(1, app.getAppID());
			query.setInt(2, user.getGenUser().getUserID());
			result = query.executeQuery();
			if (result.next()) {
				return result.getInt("Review");
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getRatingByUser");
		}
		return 0;
	}

	public void addReview(Applications app, StoreUser user, int review) throws Exception {
		LOGGER.info("Entered addReview");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_FROM_REVIEWS);
			query.setInt(1, app.getAppID());
			query.setInt(2, user.getGenUser().getUserID());
			result = query.executeQuery();
			if (!result.next()) {
				query = conn.prepareStatement(DatabaseQueries.INSERT_INTO_REVIEWS);
				query.setInt(1, app.getAppID());
				query.setInt(2, user.getGenUser().getUserID());
				query.setInt(3, review);
				int status = query.executeUpdate();
				if (status < 0) {
					throw new SystemException(Errors.SQL_EXCEPTION);
				}
			} else {
				query = conn.prepareStatement(DatabaseQueries.UPDATE_REVIEWS);
				query.setInt(1, review);
				query.setInt(2, app.getAppID());
				query.setInt(3, user.getGenUser().getUserID());
				int status = query.executeUpdate();
				if (status < 0) {
					throw new SystemException(Errors.SQL_EXCEPTION);
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited addReview");
		}
	}

	public void addComment(Comments comment) throws Exception {
		LOGGER.info("Entered addComment");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_COMMENT);
			query.setInt(1, comment.getUserID());
			query.setInt(2, comment.getAppID());
			query.setString(3, comment.getCommentText());
			int status = query.executeUpdate();
			if (status < 0)
				throw new SystemException(Errors.SQL_EXCEPTION);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited addComment");
		}
	}

	public boolean hasAppWithSameName(StoreUser user, Applications app) throws Exception {
		LOGGER.info("Entered hasAppWithSameName");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.USER_HAS_APP_WITH_NAME);
			query.setInt(1, user.getGenUser().getUserID());
			query.setString(2, app.getAppName());
			result = query.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited hasAppWithSameName");
		}
		return false;
	}

	public void addApps(StoreUser user, Applications app) throws Exception {
		LOGGER.info("Entered addApps");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.INSERT_APP);
			query.setInt(1, user.getGenUser().getUserID());
			query.setString(2, app.getAppName());
			query.setString(3, app.getDescription());
			query.setString(4, "u");
			query.setInt(5, app.getCategory().getCategoryID());
			query.setInt(6, app.getCost());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.SQL_EXCEPTION);
			}
		} catch (Exception e) {
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Entered addApps");
		}
	}

	public Applications[] getUnpublishedApps() throws Exception {
		LOGGER.info("Entered getUnpublishedApps");
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_UNPUBLISHED_APPS);
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getUnpublishedApps");
		}
		return apps;
	}

	public Applications[] getReportedApps() throws Exception {
		LOGGER.info("Entered getReportedApps");
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_REPORTED_APPS);
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getReportedApps");
		}
		return apps;
	}

	public Applications[] getTesterVerifiedApps() throws Exception {
		LOGGER.info("Entered getTesterVerifiedApps");
		Applications[] apps = null;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_TESTER_VERIFIED_APPS);
			result = query.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				apps = new Applications[result.getRow()];
				result.beforeFirst();
				apps = populateApps(result, apps);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited getTesterVerifiedApps");
		}
		return apps;
	}

	public void updateRejectedApp(Applications app) throws Exception {
		LOGGER.info("Entered updateRejectedApp");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_APP_REJECTED);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited updateRejectedApp");
		}
	}

	public void updateTesterApproved(Applications app) throws Exception {
		LOGGER.info("Entered updateTesterApproved");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_APP_TO_TESTER_VERIFIED);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.DATABASE_ERR);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited updateTesterApproved");
		}
	}

	public int findAppsWithAppName(Applications app) throws Exception {
		LOGGER.info("Entered findAppsWithAppName");
		int total = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_APPNAME);
			query.setString(1, app.getAppName());
			query.setInt(2, app.getAppID());
			result = query.executeQuery();
			result.last();
			total = result.getRow();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited findAppsWithAppName");
		}
		return total;
	}

	public int findAppsWithAppNameSameCategory(Applications app) throws Exception {
		LOGGER.info("Entered findAppsWithAppNameSameCategory");
		int total = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_APPNAME_SAME_CATEGORY);
			query.setString(1, app.getAppName());
			query.setInt(2, app.getCategory().getCategoryID());
			query.setInt(3, app.getAppID());
			result = query.executeQuery();
			result.last();
			total = result.getRow();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited findAppsWithAppNameSameCategory");
		}
		return total;
	}

	public int findAppsWithDescription(Applications app) throws Exception {
		LOGGER.info("Entered findAppsWithDescription");
		int total = 0;
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SELECT_APPS_WITH_APPNAME);
			query.setString(1, app.getDescription());
			query.setInt(2, app.getAppID());
			result = query.executeQuery();
			result.last();
			total = result.getRow();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited findAppsWithDescription");
		}
		return total;
	}

	public void updateAdminApproved(Applications app) throws Exception {
		LOGGER.info("Entered updateAdminApproved");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.UPDATE_APP_TO_ADMIN_VERIFIED);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.DATABASE_ERR);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited updateAdminApproved");
		}
	}

	public void setReportCountToZero(Applications app) throws Exception {
		LOGGER.info("Entered setReportCountToZero");
		try {
			conn = connections.open();
			query = conn.prepareStatement(DatabaseQueries.SET_REPORTS_TO_ZERO);
			query.setInt(1, app.getAppID());
			int status = query.executeUpdate();
			if (status < 0) {
				throw new SystemException(Errors.DATABASE_ERR);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(Errors.SQL_EXCEPTION);
		} finally {
			connections.close(conn);
			LOGGER.info("Exited setReportCountToZero");
		}
	}

}
