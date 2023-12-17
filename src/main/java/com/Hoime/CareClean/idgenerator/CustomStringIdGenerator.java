//package com.Hoime.CareClean.idgenerator;
//
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class CustomStringIdGenerator implements IdentifierGenerator {
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//        String prefix = "BI";
//        String nextValue = "";
//
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet rs = null;
//
//        try {
//            connection = session.getJdbcConnectionAccess().obtainConnection();
//            statement = connection.createStatement();
//            rs = statement.executeQuery("select count(id) as Id from task");
//            if (rs.next()) {
//                nextValue = prefix + String.format("%04d", rs.getInt(1));
//            }
//        } catch (Exception e) {
//            throw new HibernateException("Failed to generate custom ID", e);
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (statement != null) statement.close();
//                if (connection != null) {
//                    session.getJdbcConnectionAccess().releaseConnection(connection);
//                }
//            } catch (Exception e) {
//                // Handle any exception from closing resources
//            }
//        }
//
//        return nextValue;
//    }
//}
