package io.zilker.appstore.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import io.zilker.appstore.constants.*;
import java.util.logging.Logger;
import io.zilker.appstore.exceptions.*;

public class Connections {

	private Logger LOGGER;
	private Connection conn;
	
	public Connections() {
		LOGGER = Logger.getLogger(Connections.class.getName());
	}
	
	public Connection open() {
		LOGGER.info("Entered open");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(ConnectionDetails.url, ConnectionDetails.user, ConnectionDetails.pass);
		} catch (Exception e) {
			LOGGER.info(Errors.CONNECTION_ERR);
		} finally {
			LOGGER.info("Entered open");
		}
		return conn;
	}

	public void close(Connection conn) {
		LOGGER.info("Entered close");
		try {
			conn.close();
		} catch (Exception e) {
			LOGGER.info(Errors.CONNECTION_ERR);
		} finally {
			LOGGER.info("Entered open");
		}
	}
	
}
