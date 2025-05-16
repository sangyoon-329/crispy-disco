package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import javax.management.RuntimeErrorException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * Layerd Architecture : 하나의 명령이 처리되는 동안 순차적으로 객체들이 동작하는 구조
 * 		계층을 구성하고 있는 객체들간의 (의존)관계를 기반으로 한 설계 구조
 */
public class AuthenticateServiceImpl implements AuthenticateService {
	private MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public boolean authenticate(MemberVO vo) {
		MemberVO saved = dao.selectMember(vo.getMemId());
		if(saved!=null) {
			String inputPass = vo.getMemPassword();
			String savedPass=  saved.getMemPassword();
			boolean result = savedPass.equals(inputPass);
			if(result) {
//				vo.setMemName(saved.getMemName());
//				vo.setMemMail(saved.getMemMail());
//				vo.setMemBir(saved.getMemBir());
				try {
					BeanUtils.copyProperties(vo, saved);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
			return result;
		}else {
			return false;			
		}
		
	}

}
