package jungwoo.demo3.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;


public enum ConnectionUtil {

    //외부에서 접속하기 위한 변수
    INSTANCE;

    private HikariDataSource ds;

    ConnectionUtil()  {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://pilab.smu.ac.kr:3306/nachanee12_db");
        config.setUsername("nachanee12");
        config.setPassword("C8vWqX3pLj!");

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection()throws Exception {
        return ds.getConnection();
    }

}