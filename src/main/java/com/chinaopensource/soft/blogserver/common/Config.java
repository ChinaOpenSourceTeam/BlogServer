package com.chinaopensource.soft.blogserver.common;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ftl  路径 生成页面用
     */
    public static final String FTL_PATH = properties.getProperty("ftl_path");
    /**
     * html  生成页面的存放路径
     */
    public static final String WEB_SITE = properties.getProperty("web_site");
    /**
     * 是否缓存 ftl 模板
     * 生成环境  缓存ftl
     * 开发环境 不缓存ftl
     */
    public static final boolean FTL_CACHE = Boolean.parseBoolean(properties.getProperty("ftl_cache"));
    /**
     * 网站路径
     */
    public static final String WEB_URL = properties.getProperty("web_url");
    /**
     * 动态网站路径
     */
    public static final String DYNAMIC_WEB_URL = properties.getProperty("dynamic_web_url");

    public static void main(String[] args) {
            System.out.println(Config.DYNAMIC_WEB_URL);
    }
}
