package kr.or.ddit.enumkg;

public enum OperatorType {
	PLUS('+',(l,r)->l+r), 
	MINUS('-',(l,r)->l-r), 
	MULTIPLE('*',(l,r)->l*r), 
	DIVIDE('/',(l,r)->l/r);
	
//	final OperatorType PLUS = new OperatorType('+',(l,r)->l+r);
//	final OperatorType MINUS = new OperatorType('-', (l,r)->l-r);

	private OperatorType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	private char sign;
	private BiOperandOperator realOperator;
	
	public long operate(int left, int right) {
		return realOperator.operate(left, right);
	}
}
