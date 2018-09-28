package com.ran.bigdata.base.utils;

import com.ran.bigdata.base.conf.ConfigurationManager;
import com.ran.bigdata.base.constants.DeployMode;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author rk
 * @Date 2018/9/28 12:09
 * @Description:
 **/
public class DBCPUtils {
    private DBCPUtils() {
    }

    private static DataSource ds;

    static {
        DeployMode dMode = ConfigurationManager.dMode;
        try {
            String parent = dMode.name().toUpperCase();
            String path = parent + "/dbcp-config.properties";
            Properties properties = new Properties();
            InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream(path);
            properties.load(in);
            ds = BasicDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void release(Connection con, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
