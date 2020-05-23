package cc.xzwb.bookstore.typeHandler;


import cc.xzwb.bookstore.pojo.OrderStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderEnumTypeHandler implements TypeHandler<OrderStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, OrderStatus orderStatus, JdbcType jdbcType) throws SQLException {
        int status = orderStatus.getStatus();
        preparedStatement.setInt(i, status);
    }

    @Override
    public OrderStatus getResult(ResultSet resultSet, String s) throws SQLException {
        int status = resultSet.getInt(s);
        if (status == 1) {
            return OrderStatus.WAIT_PAY;
        } else if (status == 2) {
            return OrderStatus.SUCCESS_PAY;
        } else if (status == 3) {
            return OrderStatus.CANCEL;
        } else {
            return OrderStatus.REFUND;
        }
    }

    @Override
    public OrderStatus getResult(ResultSet resultSet, int i) throws SQLException {
        int status = resultSet.getInt(i);
        if (status == 1) {
            return OrderStatus.WAIT_PAY;
        } else if (status == 2) {
            return OrderStatus.SUCCESS_PAY;
        } else if (status == 3) {
            return OrderStatus.CANCEL;
        } else {
            return OrderStatus.REFUND;
        }
    }

    @Override
    public OrderStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        int status = callableStatement.getInt(i);
        if (status == 1) {
            return OrderStatus.WAIT_PAY;
        } else if (status == 2) {
            return OrderStatus.SUCCESS_PAY;
        } else if (status == 3) {
            return OrderStatus.CANCEL;
        } else {
            return OrderStatus.REFUND;
        }
    }
}
