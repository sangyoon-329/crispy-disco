package kr.or.ddit.props;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PropertiesTest {

	@Test
	void test() throws IOException {
		String qualifiedName = "/kr/or/ddit/mbti/mbti.properties";
		InputStream is = PropertiesTest.class.getResourceAsStream(qualifiedName);
		Reader reader = new InputStreamReader(is, "UTF-8");
		
		Properties mbtiProps = new Properties();
		mbtiProps.load(reader);
		System.out.println(mbtiProps.size());
		mbtiProps.forEach((n,v)->System.out.printf("%s : %s\n", n, v));
	}

}
