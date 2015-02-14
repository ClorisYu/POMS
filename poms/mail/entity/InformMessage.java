package hit.poms.mail.entity;
/*
 * 消息通知实体层代码
 */
public class InformMessage {
	private long inform_id = 0L;
	private long send_emp_id = 0L;
	private long get_emp_id = 0L;
	private String if_read = "1";
	private String content = null;
	private String send_time = null;
	public long getInform_id() {
		return inform_id;
	}
	public void setInform_id(long informId) {
		inform_id = informId;
	}
	public long getSend_emp_id() {
		return send_emp_id;
	}
	public void setSend_emp_id(long sendEmpId) {
		send_emp_id = sendEmpId;
	}
	public long getGet_emp_id() {
		return get_emp_id;
	}
	public void setGet_emp_id(long getEmpId) {
		get_emp_id = getEmpId;
	}
	public String getIf_read() {
		return if_read;
	}
	public void setIf_read(String ifRead) {
		if_read = ifRead;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}
	public InformMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InformMessage(long informId, long sendEmpId, long getEmpId,
			String ifRead, String content, String sendTime) {
		super();
		inform_id = informId;
		send_emp_id = sendEmpId;
		get_emp_id = getEmpId;
		if_read = ifRead;
		this.content = content;
		send_time = sendTime;
	}
	@Override
	public String toString() {
		return "InformMessage [content=" + content + ", get_emp_id="
				+ get_emp_id + ", if_read=" + if_read + ", inform_id="
				+ inform_id + ", send_emp_id=" + send_emp_id + ", send_time="
				+ send_time + "]";
	}
	
	
	

}
