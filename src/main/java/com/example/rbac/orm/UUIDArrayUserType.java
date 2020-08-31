package com.example.rbac.orm;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

public class UUIDArrayUserType extends GenericArrayUserType<UUID> {

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		if (names != null && names.length > 0 && rs != null && rs.getArray(names[0]) != null) {
			Object array = rs.getArray(names[0]).getArray();
			if (array instanceof UUID[]) {
				return array;
			}
		}
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index,
			SharedSessionContractImplementor session) throws HibernateException, SQLException {
		Connection connection = statement.getConnection();
		if (value == null) {
			statement.setNull(index, SQL_TYPES[0]);
		} else {
			UUID[] castObject = (UUID[]) value;
			Array array = connection.createArrayOf("uuid", castObject);
			statement.setArray(index, array);
		}
	}

}
