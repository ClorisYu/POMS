package hit.poms.dao.entity;

import java.util.Date;

/**
 * 作者：dsz
 * 时间：2014-07-15 PM
 * @author new
 *功能：通知表的实体类
 */

public class Inform {
	private Long send_emp_id;//发送员工id
	private Long get_emp_id;//接受员工id
	private char if_read;//是否已读0:未读；1：已读
	private String content;//内容
	private String send_time;//发送时间
	private Long inform_id;//自增加:有序列，名为inform_quen，从1开始，增量为1
	//----------------------------------------------------------
	@Override
	public String toString() {
		return "Inform [content=" + content + ", get_emp_id=" + get_emp_id
				+ ", if_read=" + if_read + ", inform_id=" + inform_id
				+ ", send_emp_id=" + send_emp_id + ", send_time=" + send_time
				+ "]";
	}
	//----------------------------------------------------------
	public Inform(Long sendEmpId, Long getEmpId, char ifRead, String content,
			String sendTime, Long informId) {
		super();
		send_emp_id = sendEmpId;
		get_emp_id = getEmpId;
		if_read = ifRead;
		this.content = content;
		send_time = sendTime;
		inform_id = informId;
	}
	
	//----------------------------------------------------------
	public Inform() {
		super();
	}
	//-------------------------------------------------------------
	public Long getSend_emp_id() {
		return send_emp_id;
	}
	public void setSend_emp_id(Long sendEmpId) {
		send_emp_id = sendEmpId;
	}
	public Long getGet_emp_id() {
		return get_emp_id;
	}
	public void setGet_emp_id(Long getEmpId) {
		get_emp_id = getEmpId;
	}
	public char getIf_read() {
		return if_read;
	}
	public void setIf_read(char ifRead) {
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
	public Long getInform_id() {
		return inform_id;
	}
	public void setInform_id(Long informId) {
		inform_id = informId;
	}


}
