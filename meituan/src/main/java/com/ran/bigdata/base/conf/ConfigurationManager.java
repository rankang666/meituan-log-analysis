package com.ran.bigdata.base.conf;

import com.ran.bigdata.base.constants.Constants;
import com.ran.bigdata.base.constants.DeployMode;
import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @Author rk
 * @Date 2018/9/28 11:45
 * @Description:单例，主要用作配置文件的全局管理
 **/
public class ConfigurationManager {
    private ConfigurationManager() {
    }

    public static DeployMode dMode;
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("conf.properties");
            properties.load(in);
            dMode = DeployMode.valueOf(properties.getProperty(Constants.SPARK_JOB_RUN_MODE).toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Long getLongProperty(String key) {
        return Long.valueOf(properties.getProperty(key));
    }

    public static int getIntProperty(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }

    public static Double getDoubleProperty(String key) {
        return Double.valueOf(properties.getProperty(key));
    }

    public static Boolean getBooleanProperty(String key) {
        return Boolean.valueOf(properties.getProperty(key));
    }

}
