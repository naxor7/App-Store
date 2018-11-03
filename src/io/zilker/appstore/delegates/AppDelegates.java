package io.zilker.appstore.delegates;

import io.zilker.appstore.dao.*;
import io.zilker.appstore.exceptions.*;
import java.util.logging.Logger;
import io.zilker.appstore.beans.*;
import io.zilker.appstore.constants.Errors;

public class AppDelegates {

	private AppsDao appsDao;
	static Logger LOGGER;
	private UserDao userDao;

	public AppDelegates() {
		appsDao = new AppsDao();
		userDao = new UserDao();
		LOGGER = Logger.getLogger(UserDelegates.class.getName());
	}

	public Applications[] getAllApps() throws Exception {
		LOGGER.info("Entered getAllApps");
		try {
			return appsDao.getAllApps();
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

	public Applications[] getDownloads(StoreUser user) throws Exception {
		LOGGER.info("Entered getDownloads");
		try {
			return appsDao.getDownloads(user);
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
			return appsDao.getMyApps(user);
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
			if (appsDao.hasAppInDownloads(app, user))
				throw new BusinessException(Errors.ALREADY_DOWNLOADED_APP);
			appsDao.addAppToWishList(app, user);
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
			appsDao.addApps(user, app);
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
	
	public void deleteAppFromWishList(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered deleteAppFromWishList");
		try {
			appsDao.deleteAppFromWishList(user, app);
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
			appsDao.deleteAppFromDownloads(user, app);
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
	
	public void downloadApp(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered downloadApp");
		try {
			if (appsDao.hasAppInDownloads(app, user))
				throw new BusinessException(Errors.ALREADY_DOWNLOADED_APP);
			appsDao.downloadApp(app, user);
			appsDao.deleteAppFromWishList(user, app);
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
	
	public void reportApp(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered reportApp");
		try {
			if (!appsDao.hasAppInDownloads(app, user))
				throw new BusinessException(Errors.CANT_REPORT);
			appsDao.reportApp(app, user);
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
			appsDao.addReview(app, user, review);
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
			return appsDao.getRatingByUser(app, user);
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
			appsDao.addComment(comment);
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
			if (!appsDao.hasAppInDownloads(app, user))
				throw new BusinessException(Errors.INVALID_OPERATION);
			return appsDao.isReportedByUser(app, user);
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
	
	public boolean hasAppWithSameName(Applications app, StoreUser user) throws Exception {
		LOGGER.info("Entered hasAppWithSameName");
		try {
			return appsDao.hasAppWithSameName(user, app);
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

	public Applications[] getWishList(StoreUser user) throws Exception {
		LOGGER.info("Entered getWishList");
		try {
			return appsDao.getWishList(user);
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

	public Applications getAppInfo(Applications app) throws Exception {
		LOGGER.info("Entered getAppInfo");
		try {
			return appsDao.getAppInfo(app);
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
	
	public Comments[] getAllComments(Applications app) throws Exception {
		LOGGER.info("Entered getAllComments");
		try {
			return appsDao.getAllComments(app);
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
			return appsDao.hasAppInWishList(app, user);
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
			return appsDao.hasAppInDownloads(app, user);
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
	
	public Applications[] getUnpublishedApps() throws Exception {
		LOGGER.info("Entered getUnpublishedApps");
		try {
			return appsDao.getUnpublishedApps();
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
			return appsDao.getReportedApps();
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
			return appsDao.getTesterVerifiedApps();
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
			appsDao.updateRejectedApp(app);
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
			appsDao.updateTesterApproved(app);
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
			return appsDao.findAppsWithAppName(app);
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
			return appsDao.findAppsWithAppNameSameCategory(app);
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
			return appsDao.findAppsWithDescription(app);
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
			appsDao.updateAdminApproved(app);
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
			appsDao.setReportCountToZero(app);
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
