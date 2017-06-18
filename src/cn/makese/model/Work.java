package cn.makese.model;

public class Work {
	private String sno;
	private int workDay = -1;
	private int overDay = -1;
	private int lackDay = -1;
	private int absence = -1;
	public int getLackDay() {
		return lackDay;
	}
	public int getAbsence() {
		return absence;
	}
	public void setAbsence(int absence) {
		this.absence = absence;
	}
	public void setLackDay(int lackDay) {
		this.lackDay = lackDay;
	}
	public int getOverDay() {
		return overDay;
	}
	public void setOverDay(int overDay) {
		this.overDay = overDay;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public int getWorkDay() {
		return workDay;
	}
	public void setWorkDay(int workDay) {
		this.workDay = workDay;
	}
}
