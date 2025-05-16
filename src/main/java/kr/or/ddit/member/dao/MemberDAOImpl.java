package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.text.WordUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMember(String username) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select mem_id, mem_password, mem_name, mem_mail, mem_bir, mem_add1, mem_add2");
		sql.append("from member                                    ");
		sql.append("where mem_id = ?                         ");
		
		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstmt =  conn.prepareStatement(sql.toString());) 
		{	
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			MemberVO vo = null;
			if(rs.next()) {
				// dataMapping : entity -> object 변환할 예정
				vo = entityToObject(rs, MemberVO.class);
			}
			return vo;			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}                                             
	}

	private <T> T entityToObject(ResultSet rs, Class<T> resultType) throws SQLException{
		try {
			T resultObject = (T)resultType.getConstructor().newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCnt = rsmd.getColumnCount();
			for(int i=1; i<=columnCnt; i++) {
				String columnName = rsmd.getColumnName(i);
				String columnValue = rs.getString(columnName);
				// mem_id, memId
				String propertyName = WordUtils.capitalize(columnName.toLowerCase(), '_').replace("_", "");
				
				PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType);
				pd.getWriteMethod().invoke(resultObject, columnValue);
			}
			
			return resultObject;
		} catch (Exception e) {
			throw new SQLException(e);
		}
//		vo = new MemberVO();
//		vo.setMemId(rs.getString("MEM_ID"));
//		vo.setMemPassword(rs.getString("MEM_PASSWORD"));
//		vo.setMemName(rs.getString("MEM_NAME"));
//		vo.setMemMail(rs.getString("MEM_MAIL"));
//		vo.setMemBir(rs.getString("MEM_BIR"));
//		return vo;
	}

}
