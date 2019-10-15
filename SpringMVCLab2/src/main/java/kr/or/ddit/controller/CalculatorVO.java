package kr.or.ddit.controller;

import java.io.Serializable;

public class CalculatorVO implements Serializable{
	private int leftOp;
	private int rightOp;
	private int result;
	
	
	public int getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}
	public int getRightOp() {
		return rightOp;
	}
	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "CalculatorVO [leftOp=" + leftOp + ", rightOp=" + rightOp + ", result=" + result + "]";
	}
	
	
}
