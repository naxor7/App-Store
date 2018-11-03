package io.zilker.appstore.constants;

public class DatabaseQueries {
	
	public static final String SELECT_USER_WITH_ID = "SELECT * FROM User WHERE UserID = ?";
	public static final String SELECT_USER_WITH_USERNAME_AND_PASSWORD = "SELECT * FROM User WHERE UserName = ? AND Password = MD5(?);";
	public static final String SELECT_USER_WITH_USERNAME = "SELECT * FROM User WHERE UserName = ?;";
	public static final String INSERT_USER = "INSERT INTO User(UserName, Password, UserPrivilege, FullName, EmailID) VALUES (?,MD5(?),?,?,?)";
	public static final String INSERT_WALLET = "INSERT INTO Wallet(UserID, Money) VALUES (?, ?);"; 
	public static final String SELECT_CATEGORY_WITH_CATEGORY_ID = "SELECT * FROM Category WHERE CategoryID = ?;";
	public static final String SELECT_APP_RATING = "SELECT AVG(Review) FROM Reviews WHERE AppID = ?;";
	public static final String VIEW_WISHLIST = "SELECT * FROM Apps INNER JOIN WishList ON Apps.AppID = WishList.AppID WHERE WishList.UserID = ?;";
	public static final String SELECT_APPS_FROM_DOWNLOADS = "SELECT * FROM Apps INNER JOIN Downloads ON Apps.AppID = Downloads.AppID WHERE Downloads.UserID = ?;";
	public static final String SELECT_MY_APPS = "SELECT * FROM Apps WHERE UserID = ?";
	public static final String SELECT_ALL_APPS = "SELECT * FROM Apps WHERE Status = 'p';";
	public static final String UPDATE_USERNAME = "UPDATE User SET UserName = ? WHERE UserID = ?";
	public static final String UPDATE_PASSWORD = "UPDATE User SET Password = MD5(?) WHERE UserID = ?";
	public static final String UPDATE_USER_PRIVILEGE = "UPDATE User SET UserPrivilege = ? WHERE UserID = ?";
	public static final String UPDATE_EMAIL = "UPDATE User SET EmailID = ? WHERE UserID = ?";
	public static final String UPDATE_FULLNAME = "UPDATE User SET FullName = ? WHERE UserID = ?";
	public static final String HAS_APP_IN_WISHLIST = "SELECT * FROM WishList WHERE UserID = ? AND AppID = ?;";
	public static final String INSERT_WISHLIST = "INSERT INTO WishList (UserID, AppID) VALUES (?, ?);";
	public static final String HAS_APP_IN_DOWNLOADS = "SELECT * FROM Downloads WHERE UserID = ? AND AppID = ?;";
	public static final String GET_APP_INFO = "SELECT * FROM Apps WHERE AppID = ?";
	public static final String INSERT_DOWNLOADS = "INSERT INTO Downloads (AppID, UserID) VALUES (?, ?);";
	public static final String DELETE_FROM_WISHLIST = "DELETE FROM WishList WHERE UserID = ? AND AppID = ?";
	public static final String DELETE_APP = "DELETE FROM Downloads WHERE UserID = ? AND AppID = ?";
	public static final String GET_ALL_COMMENTS = "SELECT * FROM Comments WHERE AppID = ?";
	public static final String GET_USER = "SELECT * FROM User WHERE UserID=?";
	public static final String SELECT_REPORTED_BY_APPID_AND_USERID = "SELECT * FROM Reports WHERE AppID = ? AND UserID = ?;";
	public static final String REPORT_APP = "INSERT INTO Reports (AppID, UserID) VALUES (?, ?);";
	public static final String SELECT_FROM_REVIEWS = "SELECT * FROM Reviews WHERE AppID = ? AND UserID = ?";
	public static final String INSERT_INTO_REVIEWS = "INSERT INTO Reviews (AppID, UserID, Review) VALUES (?, ?, ?);";
	public static final String UPDATE_REVIEWS = "UPDATE Reviews SET Review = ? WHERE AppID = ? AND UserID = ?;";
	public static final String INSERT_COMMENT = "INSERT INTO Comments (UserID, AppID, CommentText) VALUES (?,?,?);";
	public static final String GET_REVIEWS = "SELECT * FROM Reviews WHERE AppID = ? AND UserID = ?;";
	public static final String USER_HAS_APP_WITH_NAME = "SELECT * FROM Apps WHERE UserID = ? AND AppName = ?";
	public static final String INSERT_APP = "INSERT INTO Apps (UserID, AppName, Description, Status, CategoryID, Cost) VALUES (?,?,?,?,?,?);";
	public static final String SELECT_UNPUBLISHED_APPS = "SELECT * FROM Apps WHERE Status = 'u'";
	public static final String SELECT_REPORTED_APPS = "SELECT Apps.AppID, Apps.UserID, Apps.AppName, Apps.Description, Apps.Status, Apps.Cost, Apps.CategoryID, Apps.Created_At, Apps.Updated_At FROM Reports INNER JOIN Apps ON Apps.AppID = Reports.AppID GROUP BY Reports.AppID HAVING COUNT(*) > 0;";
	public static final String UPDATE_APP_TO_TESTER_VERIFIED = "UPDATE Apps SET Status = 'p' WHERE AppID = ?";
	public static final String UPDATE_APP_REJECTED = "UPDATE Apps SET Status = 'r' WHERE AppID = ?";
	public static final String UPDATE_APP_TO_ADMIN_VERIFIED = "UPDATE Apps SET Status = 'p' WHERE AppID = ?";
	public static final String SELECT_TESTER_VERIFIED_APPS = "SELECT * FROM Apps WHERE Status = 't'";
	public static final String SELECT_APPS_WITH_APPNAME = "SELECT * FROM Apps WHERE AppName = ? AND Status = 'p' AND AppID != ?";
	public static final String SELECT_APPS_WITH_APPNAME_SAME_CATEGORY = "SELECT * FROM Apps WHERE AppName = ? AND Status = 'p' AND CategoryID = ? AND AppID != ?";
	public static final String SELECT_APPS_WITH_DESCRIPTION = "SELECT * FROM Apps WHERE Description = ? AND Status = 'p' AND AppID != ?";
	public static final String SET_REPORTS_TO_ZERO = "DELETE FROM Reports WHERE AppID=?";
	public static final String IS_TESTER = "SELECT * FROM User WHERE UserID = ? AND UserPrivilege = 't'";
	public static final String IS_ADMIN = "SELECT * FROM User WHERE UserID = ? AND UserPrivilege = 'a'";
	public static final String SELECT_APPS_WITH_USER_ID = "SELECT * FROM Apps WHERE UserID = ? AND Status = 'p'";
	public static final String SELECT_USER_RATING = "SELECT AVG(Reviews.Review) FROM Reviews INNER JOIN Apps ON Apps.AppID = Reviews.AppID WHERE Apps.UserID = ?;";
	
	
}
