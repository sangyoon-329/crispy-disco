package kr.or.ddit.enumkg;

@FunctionalInterface
public interface BiOperandOperator {
	long operate(long left, long right);
}
