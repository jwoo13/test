package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

    @Test
    public void testConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://pilab.smu.ac.kr:3306/nachanee12_db",
                "nachanee12",
                "C8vWqX3pLj!");

        Assertions.assertNotNull(connection);

        connection.close();
    }


    @Test
    public void test1() {

        int v1 = 10;
        int v2 = 110;

        Assertions.assertEquals(v1,v2);

    }


}



