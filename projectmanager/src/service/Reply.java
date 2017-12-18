package service;

public class Reply {

	public static final Reply DEFAULT_REPLY = new Reply();
	public static final String OK_STR = "ok";
	public static final String FAIL_STR = "fail";
	public static final String FAIL_STR_COMM = "fail (communicatin error)";
	public static final int FAIL_ID = -1;
	public static final int OK_ID = 0;

	private int id = OK_ID;
	private String msg = OK_STR;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
