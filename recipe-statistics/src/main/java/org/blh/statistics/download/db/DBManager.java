package org.blh.statistics.download.db;

import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    private static DBConnector dbc;
    private static String URI, user, pwd, driver;

    /**
     * Uses the property keys [URI, user, password]
     * @param p
     */
    public static void setCredentials(Properties p) {
        setCredentials(p.getProperty("URI"), p.getProperty("user"),
                p.getProperty("password"), p.getProperty("driver"));
    }

    public static void setCredentials(String URI, String user, String pwd) {
        setCredentials(URI, user, pwd, null);
    }

    public static void setCredentials(String URI, String user, String pwd, String driver) {
        DBManager.URI = URI;
        DBManager.user = user;
        DBManager.pwd = pwd;
        if (driver == null) {
            DBManager.driver = DBConnector.MYSQL;
        } else {
            DBManager.driver = driver;
        }

        invalidate();
    }

    public static Properties getCredentials() {
        Properties p = new Properties();
        p.setProperty("URI", URI);
        p.setProperty("user", user);
        p.setProperty("password", pwd);
        p.setProperty("dirver", driver);

        return p;
    }

    public static DBConnector getDBC() throws SQLException {
        if (dbc == null) {
            dbc = new DBConnector(URI, user, pwd, driver);
            dbc.open();
        } else {
            try {
                if (dbc.getConnection() == null || dbc.getConnection().isClosed()) {
                    dbc = null;
                    return getDBC();
                }
            } catch (SQLException ex) {
                dbc = null;
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dbc;
    }

    /**
     * Forces the <code>getDBC()</code> method to reconnect to the database the
     * next time it's invoked. This is by no means thread-safe.
     */
    public static void invalidate() {
        dbc = null;
    }
}
