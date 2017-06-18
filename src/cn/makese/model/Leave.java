package cn.makese.model;

public class Leave {
	private String Lno;
	private String sno;
	private String date;
	private String reason;
	private int day = -1;
	private String state;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLno() {
		return Lno;
	}
	public void setLno(String lno) {
		Lno = lno;
	}
	
}
