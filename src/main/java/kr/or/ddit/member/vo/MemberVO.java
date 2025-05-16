package kr.or.ddit.member.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringExclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "memId") // 데이터 베이스 설계 참고
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
public class MemberVO implements Serializable{

	private String memId;
	private String memPassword;
	private String memName;
	@ToStringExclude
	private transient String memRegno1;
	@ToStringExclude
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memHobby;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
}
