package ua.lviv.lgs.utils;

import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static String user = "root";
    private static String password = "Karnaval3e";
    private static String URL = "jdbc:mysql://localhost:3306/i_shop?serverTimezone=UTC";

    public static Connection openConection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
      //  DOMConfigurator.configure("log4j.xml");
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
        return DriverManager.getConnection(URL,user,password);
    }
}
