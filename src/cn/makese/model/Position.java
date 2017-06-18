package cn.makese.model;

public class Position {
	private int pno = -1;
	private String job;
	private int esalary = -1;
	private int bonus = -1;
	private int leaveday = -1;
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getEsalary() {
		return esalary;
	}
	public void setEsalary(int esalary) {
		this.esalary = esalary;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getLeaveday() {
		return leaveday;
	}
	public void setLeaveday(int leaveday) {
		this.leaveday = leaveday;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
}
