package org.blh.statistics.download.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DBConnector {

    /** url example: jdbc:mysql://localhost:3306/myDB */
    public static final String MYSQL = "com.mysql.jdbc.Driver";
    /** url example: google */
    public static final String EXCEL = "sun.jdbc.odbc.JdbcOdbcDriver";
    /** url example: jdbc:sqlite:test.db */
    public static final String SQLITE = "org.sqlite.JDBC";
    /////////
    private boolean debug, returnGeneratedKeys;
    private String dbURI;
    private String dbUser;
    private String dbPassword;
    private Connection conn;
    private int defaultResultSetType, defaultResultSetConcurrency;

    /**
     * Returns a map with (human readable, jdbcpath) pairs
     * @return
     */
    public static Map<String, String> getSupportedDrivers() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("MySQL", MYSQL);
        map.put("SQLite", SQLITE);
        map.put("Microsoft Excel", EXCEL);
        return map;
    }

    /** Creates a new instance on DBConnector connecting to the database located on the path specified in dbURI.<br/>
     * The login information is specified by dbUser and dbPassword.<br/>
     * If the driver com.mysql.jdbc.Driver fails a DriverNotFoundException is thrown.<br/>
     *
     * @param dbURI The location of the database.<br/>
     *              The URI consists of x parts:<br/>
     *                  protocol - i.e. jdbc:mysql://<br/>
     *                  domain - i.e. localhost<br/>
     *                  port - i.e. 3306<br/>
     *                  database name.<br/>
     *                  So if you'd were to connect to a mysql database named
     *                  myDB located on localhost the dbURI would be
     *                  <i>jdbc:mysql://localhost:3306/myDB</i>.
     * @param dbUser    The user who's loggin in.
     * @param dbPassword The password associated with dbUSer
     * @throws IllegalArgumentException This is thrown if the driver
     * <code>com.mysql.jdbc.Driver</code> is not found.
     */
    public DBConnector(String dbURI, String dbUser, String dbPassword) throws IllegalArgumentException {
        this(dbURI, dbUser, dbPassword, MYSQL);
    }

    /** Creates a new instance on DBConnector connecting to the database located on the path specified in dbURI.<br/>
     * The login information is specified by dbUser and dbPassword.<br/>
     * If the driver com.mysql.jdbc.Driver fails a DriverNotFoundException is thrown.<br/>
     *
     * @param dbURI The location of the database.<br/>
     *              The URI consists of x parts:<br/>
     *                  protocol - i.e. jdbc:mysql://<br/>
     *                  domain - i.e. localhost<br/>
     *                  port - i.e. 3306<br/>
     *                  database name.<br/>
     *                  So if you'd were to connect to a mysql database named
     *                  myDB located on localhost the dbURI would be
     *                  <i>jdbc:mysql://localhost:3306/myDB</i>.
     * @param dbUser    The user who's loggin in.
     * @param dbPassword The password associated with dbUSer
     * @param dbDriver The driver to use with the connected, for example:
     * com.mysql.jdbc.Driver or org.sqlite.JDBC
     * @throws IllegalArgumentException This is thrown if the driver is not found.
     */
    public DBConnector(String dbURI, String dbUser, String dbPassword, String dbDriver) throws IllegalArgumentException {
        try {
            Class.forName(dbDriver);
        } catch (Exception ex) {
            throw new IllegalArgumentException("The specified driver, " + dbDriver + ", was not found");
        }

        this.dbURI = dbURI;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;

        if (dbDriver.equals(SQLITE)) {
            defaultResultSetType = ResultSet.TYPE_FORWARD_ONLY;
            defaultResultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
            returnGeneratedKeys = false;
        } else {
            defaultResultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
            defaultResultSetConcurrency = ResultSet.CONCUR_UPDATABLE;
            returnGeneratedKeys = true;
        }
    }

    public int getDefaultResultSetConcurrency() {
        return defaultResultSetConcurrency;
    }

    public void setDefaultResultSetConcurrency(int defaultResultSetConcurrency) {
        this.defaultResultSetConcurrency = defaultResultSetConcurrency;
    }

    public int getDefaultResultSetType() {
        return defaultResultSetType;
    }

    public void setDefaultResultSetType(int defaultResultSetType) {
        this.defaultResultSetType = defaultResultSetType;
    }

    public void setReturnGeneratedKeys(boolean returnGeneratedKeys) {
        this.returnGeneratedKeys = returnGeneratedKeys;
    }

    protected Statement getStmt() throws SQLException {
        return conn.createStatement(defaultResultSetType,
                defaultResultSetConcurrency);
    }

    public boolean isOpened() {
        return (conn != null);
    }

    public Connection getConnection() {
        return conn;
    }

    /** Opens the connection
     * @throws java.sql.SQLException if the database could not be located or the
     * login information is incorrect
     */
    public void open() throws SQLException {
        try {
            //System.out.println("Öppnar conn till " + dbURI);
            conn = DriverManager.getConnection(dbURI, dbUser, dbPassword);
            //statement = conn.createStatement();
        } catch (SQLException sqle) {
            throw new SQLException(sqle + "\n\tCould not open connection");
        }
    }

    /** Closes the connection */
    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public ResultSet query(String query) throws IllegalStateException, SQLException {
        return query(query, getStmt());
    }

    /** Used for querying the database. This is done with normal SQL
     * @param query The SQL-statement used to query the db
     * @return  The result of the query
     * @throws IllegalStateException If the connection has not yet been opened.
     * @throws java.sql.SQLException If the query is malformed or if there is
     * something else wrong with the query
     */
    public ResultSet query(String query, Statement statement) throws IllegalStateException, SQLException {
        if (!isOpened()) {
            throw new IllegalStateException("The SQLConnector has not yet had it's connection opened");
        }
        if (debug) {
            System.out.println("Querying: " + query);
        }
        return statement.executeQuery(query);
    }

    /** Used to modify a database table in any way. This is done with normal SQL
     * @param query The SQL-statement to be executed
     * @return  ResultSet object containing the auto-generated key(s) generated
     * by the execution of the query.
     * @throws IllegalStateException If the connection has not yet been opened
     * @throws java.sql.SQLException If the query is malformed or if there is
     * something else wrong with the query
     */
    public ResultSet update(String query) throws IllegalStateException, SQLException {
        return update(query, getStmt());
    }

    public ResultSet update(String query, Statement statement) throws IllegalStateException, SQLException {
        if (!isOpened()) {
            throw new IllegalStateException("The SQLConnector has not yet had it's connection opened");
        }
        if (debug) {
            System.out.println("Updating: " + query);
        }
        if (returnGeneratedKeys) {
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            return statement.getGeneratedKeys();
        } else {
            statement.executeUpdate(query);
            return null;
        }
    }

    public static int getRowCount(ResultSet rs) throws SQLException {
        if (rs == null) {
            return 0;
        }

        int prevRow = rs.getRow();
        rs.last();
        int toReturn = rs.getRow();
        setRow(rs, prevRow);
        return toReturn;
    }

    public static int getColumntCount(ResultSet rs) throws SQLException {
        return rs.getMetaData().getColumnCount();
    }

    /**
     * Generates a SQL-query "INSERT INTO [table] (variables.keys)
     * VALUES(variables.values)
     * @param variables
     * @param table
     * @return The SQL-query used to add the specified values to a database
     */
    public static String generateInsert(Map<String, Object> variables, String table) {
        String start = "INSERT INTO " + table + " (";
        String values = "(";

        if (variables != null) {
            for (Iterator<Entry<String, Object>> it = variables.entrySet().iterator(); it.hasNext();) {

                Map.Entry<String, Object> entry = it.next();
                start += entry.getKey();
                values += "'" + entry.getValue() + "'";

                if (it.hasNext()) {
                    start += ", ";
                    values += ", ";
                }
            }
        }

        return start + ") VALUES " + values + ")";
    }

    /**
     *
     * @param variables
     * @param table
     * @param where
     * @return
     */
    public static String generateUpdate(Map<String, Object> variables, String table, String where) {
        String toReturn = "UPDATE " + table + " SET ";

        for (Iterator<Entry<String, Object>> it = variables.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Object> entry = it.next();
            toReturn += entry.getKey() + "='" + entry.getValue() + "'";
            if (it.hasNext()) {
                toReturn += ", ";
            }
        }
	if(where != null && where.length() > 0) {
	   toReturn += " WHERE " + where;
	}
        return toReturn;
    }

    public static void setRow(ResultSet rs, int row) throws SQLException {
        if (row == 0) {
            rs.beforeFirst();
        } else {
            rs.absolute(row);
        }
    }

    public static String getURI(String driver, String host, int port, String dbName) {
        if (driver.equals(MYSQL)) {
            return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        } else if (driver.equals(SQLITE)) {
            if (!dbName.contains(".")) {
                dbName += ".db";
            }
            return "jdbc:sqlite:" + dbName;
        }

	throw new IllegalArgumentException(driver + " is not a supported driver for this method");
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
