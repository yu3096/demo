package com.ysm.demo.init.database.tables.dataClasses;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.*;
import java.util.Properties;

public class CustomUUID implements IdentifierGenerator, Configurable {

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {

    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        /*
        byte[] result = (byte[]) session.createNativeQuery("SELECT CONCAT(DATE_FORMAT(NOW(), '%Y%m%d'),'-' , REPLACE(UUID(), '-', ''))").getSingleResult();
        return result;
        */
        String result = null;
        try {
            Connection conn = session.getJdbcConnectionAccess().obtainConnection();
            PreparedStatement preparedStatement =  conn.prepareStatement("SELECT CONCAT(DATE_FORMAT(NOW(), '%Y%m%d'),'-' , REPLACE(UUID(), '-', '')) AS USER_UUID FROM DUAL");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            result = rs.getString("USER_UUID");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
