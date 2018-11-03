package io.zilker.appstore.controller;

import java.util.logging.Logger;

import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;
import io.zilker.appstore.constants.RegularExpressions;
import io.zilker.appstore.delegates.*;
import io.zilker.appstore.exceptions.BusinessException;
import io.zilker.appstore.exceptions.SystemException;

public class AppController {

	private Validator validator;
	private AppDelegates appDelegates;
	static Logger LOGGER;

	public AppController() {
		validator = new Validator();
		appDelegates = new AppDelegates();
		LOGGER = Logger.getLogger(UserController.class.getName());
	}

	public Applications[] getAllApps() throws Exception {
		LOGGER.info("Entered getAllApps");
		try {
			return appDelegates.getAllApps();
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getAllApps");
		}
	}

	public Applications[] getWishList(StoreUser user) throws Exception {
		LOGGER.info("Entered getWishList");
		try {
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			return appDelegates.getWishList(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getWishList");
		}
	}

	public Applications[] getDownloads(StoreUser user) throws Exception {
		LOGGER.info("Entered getDownloads");
		try {
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			return appDelegates.getDownloads(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getDownloads");
		}
	}

	public Applications[] getMyApps(StoreUser user) throws Exception {
		LOGGER.info("Entered getMyApps");
		try {
			return appDelegates.getMyApps(user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getMyApps");
		}
	}

	public void addAppToWishList(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered addAppToWishList");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			appDelegates.addAppToWishList(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited addAppToWishList");
		}
	}
	
	public void addApps(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered addApps");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			appDelegates.addApps(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited addApps");
		}
	}
	
	public void downloadApp(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered downloadApp");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			appDelegates.downloadApp(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited downloadApp");
		}
	}

	public Applications getAppInfo(Applications app) throws Exception {
		LOGGER.info("Entered getAppInfo");
		try {
			validator.checkID(app.getAppID());
			return appDelegates.getAppInfo(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getAppInfo");
		}
	}
	
	public void deleteAppFromWishList(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered deleteAppFromWishList");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			appDelegates.deleteAppFromWishList(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited deleteAppFromWishList");
		}
	}
	
	public void deleteAppFromDownloads(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered deleteAppFromDownloads");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			appDelegates.deleteAppFromDownloads(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited deleteAppFromDownloads");
		}
	}
	
	public void reportApp(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered reportApp");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			appDelegates.reportApp(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited reportApp");
		}
	}
	
	public void reviewApp(Applications app, StoreUser user, int review) throws Exception {
		LOGGER.info("Entered reviewApp");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			appDelegates.reviewApp(app, user, review);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited reviewApp");
		}
	}
	
	public int getReviewByUser(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered getReviewByUser");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			return appDelegates.getReviewByUser(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getReviewByUser");
		}
	}
	
	public void addComment(Comments comment) throws Exception {
		LOGGER.info("Entered addComment");
		try {
			validator.checkID(comment.getUserID());
			validator.checkID(comment.getAppID());
			appDelegates.addComment(comment);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited addComment");
		}
	}
	
	public boolean isReportedByUser(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered isReportedByUser");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			validator.checkUserName(user.getGenUser().getUserName());
			return appDelegates.isReportedByUser(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited isReportedByUser");
		}
	}
	
	public Comments[] getAllComments(Applications app) throws Exception {
		LOGGER.info("Entered getAllComments");
		try {
			validator.checkID(app.getAppID());
			return appDelegates.getAllComments(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getAllComments");
		}
	}
	
	public boolean hasAppInWishlist(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered hasAppInWishlist");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			return appDelegates.hasAppInWishlist(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited hasAppInWishlist");
		}
	}
	
	public boolean hasAppInDownloads(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered hasAppInDownloads");
		try {
			validator.checkID(app.getAppID());
			validator.checkID(user.getGenUser().getUserID());
			return appDelegates.hasAppInDownloads(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited hasAppInDownloads");
		}
	}
	
	public boolean hasAppWithSameName(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered hasAppWithSameName");
		try {
			return appDelegates.hasAppWithSameName(app, user);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited hasAppWithSameName");
		}
	}
	
	public Applications[] getUnpublishedApps() throws Exception {
		LOGGER.info("Entered getUnpublishedApps");
		try {
			return appDelegates.getUnpublishedApps();
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getUnpublishedApps");
		}
	}
	
	public Applications[] getReportedApps() throws Exception {
		LOGGER.info("Entered getReportedApps");
		try {
			return appDelegates.getReportedApps();
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getReportedApps");
		}
	}
	
	public Applications[] getTesterVerifiedApps() throws Exception {
		LOGGER.info("Entered getTesterVerifiedApps");
		try {
			return appDelegates.getTesterVerifiedApps();
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited getTesterVerifiedApps");
		}
	}
	
	public void updateRejectedApp(Applications app) throws Exception {
		LOGGER.info("Entered updateRejectedApp");
		try {
			validator.checkID(app.getAppID());
			appDelegates.updateRejectedApp(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited updateRejectedApp");
		}
	}
	
	public void updateTesterApproved(Applications app) throws Exception {
		LOGGER.info("Entered updateTesterApproved");
		try {
			validator.checkID(app.getAppID());
			appDelegates.updateTesterApproved(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited updateTesterApproved");
		}
	}
	
	public int findAppsWithAppName(Applications app) throws Exception {
		LOGGER.info("Entered findAppsWithAppName");
		try {
			validator.checkID(app.getAppID());
			return appDelegates.findAppsWithAppName(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited findAppsWithAppName");
		}
	}
	
	public int findAppsWithAppNameSameCategory(Applications app) throws Exception {
		LOGGER.info("Entered findAppsWithAppNameSameCategory");
		try {
			validator.checkID(app.getAppID());
			return appDelegates.findAppsWithAppNameSameCategory(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited findAppsWithAppNameSameCategory");
		}
	}
	
	public int findAppsWithDescription(Applications app) throws Exception {
		LOGGER.info("Entered findAppsWithDescription");
		try {
			validator.checkID(app.getAppID());
			return appDelegates.findAppsWithDescription(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited findAppsWithDescription");
		}
	}
	
	public void updateAdminApproved(Applications app) throws Exception {
		LOGGER.info("Entered updateAdminApproved");
		try {
			validator.checkID(app.getAppID());
			appDelegates.updateAdminApproved(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited updateAdminApproved");
		}
	}
	
	public void setReportCountToZero(Applications app) throws Exception {
		LOGGER.info("Entered setReportCountToZero");
		try {
			validator.checkID(app.getAppID());
			appDelegates.setReportCountToZero(app);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage());
			throw new BusinessException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(e.getMessage());
		} finally {
			LOGGER.info("Exited setReportCountToZero");
		}
	}
	
}
