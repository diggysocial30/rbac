package com.example.rbac.orm;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataUserType implements UserType {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws SQLException {
		if (value == null) {
			st.setNull(index, Types.OTHER);
		} else {
			try {
				st.setObject(index, objectMapper.writeValueAsString(value), Types.OTHER);
			} catch (JsonProcessingException e) {
				throw new HibernateException(e);
			}
		}
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object deepCopy(Object originalValue) {
		Object returnValue = null;
		if (originalValue instanceof Map) {
			Map resultMap = new HashMap<>();

			Map<?, ?> tempMap = (Map<?, ?>) originalValue;
			tempMap.forEach((key, value) -> resultMap.put((String) key, value));

			returnValue = resultMap;
		} else if (originalValue instanceof List) {
			List resultList = new ArrayList<>();
			List<?> tempList = (List<?>) originalValue;
			tempList.forEach(resultList::add);

			returnValue = resultList;
		} else {
			returnValue = originalValue;
		}

		return returnValue;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws SQLException {
		PGobject o = (PGobject) rs.getObject(names[0]);
		if (o == null) {
			return null;
		} else if (ObjectUtils.isEmpty(o.getValue())) {
			return Collections.emptyMap();
		} else {
			try {
				return objectMapper.readValue(o.getValue(), Map.class);
			} catch (IOException e) {
				throw new HibernateException(e);
			}
		}
	}

	@Override
	public Serializable disassemble(Object value) {
		Object copy = deepCopy(value);

		if (copy instanceof Serializable) {
			return (Serializable) copy;
		}

		throw new SerializationException(
				String.format("Cannot serialize '%s', %s is not Serializable.", value, value.getClass()), null);
	}

	@Override
	public Object assemble(Serializable cached, Object owner) {
		return deepCopy(cached);
	}

	@Override
	public Object replace(Object original, Object target, Object owner) {
		return deepCopy(original);
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public int hashCode(Object x) {
		if (x == null) {
			return 0;
		}

		return x.hashCode();
	}

	@Override
	public boolean equals(Object x, Object y) {
		return ObjectUtils.nullSafeEquals(x, y);
	}

	@Override
	public Class<?> returnedClass() {
		return Map.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.JAVA_OBJECT };
	}

}