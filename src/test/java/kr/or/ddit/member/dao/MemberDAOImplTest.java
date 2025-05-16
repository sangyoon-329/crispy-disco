package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.vo.MemberVO;

class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();
	
	@Test
	void testNotExist() {
		String username = "asdasdf' OR '1'='1";
		MemberVO vo =  dao.selectMember(username);
		System.out.println(vo);
		assertNull(vo);
//		Optional.ofNullable(dao.selectMember(username))
//				.ifPresent(System.out::print);
	
	}
	
	@Test
	void testExist() {
		String username = "a001";
		MemberVO vo =  dao.selectMember(username);
		assertNotNull(vo);
//		Optional.ofNullable(dao.selectMember(username))
//				.ifPresent(System.out::print);
	
	}
}
