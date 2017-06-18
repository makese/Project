package cn.makese.model;

public class Tax {
	private int tax = -1;
	private int hLimit = -1;
	private int lLimit = -1;
	public int getHLimit() {
		return hLimit;
	}
	public void setHLimit(int limit) {
		hLimit = limit;
	}
	public int getLLimit() {
		return lLimit;
	}
	public void setLLimit(int limit) {
		lLimit = limit;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	
}
