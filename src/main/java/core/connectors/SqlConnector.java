package core.connectors;

import core.enumerations.EnSqlType;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class SqlConnector {

    public static Integer DEFAULT_SQL_PORT = 1433;
    private static HashMap<EnSqlType, String> drivers = new HashMap<>();
    private static HashMap<EnSqlType, String> connectionUrlPatterns = new HashMap<>();
    {
        drivers.put(EnSqlType.Oracle, "oracle.jdbc.OracleDriver");
        drivers.put(EnSqlType.MSSQL, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        drivers.put(EnSqlType.PostgresSQL, "org.postgresql.Driver");
        drivers.put(EnSqlType.MySQL, "com.mysql.jdbc.Driver");

        connectionUrlPatterns.put(EnSqlType.Oracle, "jdbc:oracle:thin:@%s:%d:%s");
        connectionUrlPatterns.put(EnSqlType.MSSQL, "jdbc:sqlserver://%s:%d;databaseName=%s;");
        connectionUrlPatterns.put(EnSqlType.PostgresSQL, "jdbc:postgresql://%s:%d/%s");
        connectionUrlPatterns.put(EnSqlType.MySQL, "jdbc:mysql://%s:%d/%s");
    }

    private Connection connection;
    private Statement statement;

    public void connect(EnSqlType sqlType, String host, Integer port, String database, String user, String password) throws Exception {
        Class.forName(drivers.get(sqlType)).newInstance();
        String connectionUrl = String.format(connectionUrlPatterns.get(sqlType), host, port, database);
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", user);
        connectionProperties.setProperty("password", password);
        connectionProperties.setProperty("useUnicode", "true");
        connectionProperties.setProperty("characterEncoding", "utf8");
        connection = DriverManager.getConnection(connectionUrl, connectionProperties);
        statement = connection.createStatement();
    }

    public void disconnect() throws Exception {
        connection.close();
        connection = null;
    }

    public void createRows(String table, List<HashMap<String, Object>> rows) {
    }

    public ArrayList<HashMap<String, Object>> readRows(String table, String condition) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ");
        query.append(table);
        if(condition != null) {
            query.append(" WHERE ");
            query.append(condition);
        }
        ResultSet resultSet = statement.executeQuery(query.toString());
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        while (resultSet.next()) {
            int columnsCount = resultSetMetaData.getColumnCount();
            HashMap<String, Object> row = new HashMap<>();
            for (int i = 0; i < columnsCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i + 1).intern();
                Object object = resultSet.getObject(i + 1);
                row.put(columnName, object);
            }
            result.add(row);
        }
        return result;
    }

    public void updateRows(String table, String condition, HashMap<String, Object> row) {

    }

    public void deleteRows(String table, String condition) {

    }
}
