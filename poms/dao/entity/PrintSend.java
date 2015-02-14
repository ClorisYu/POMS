package hit.poms.dao.entity;

import java.util.Date;

/**
 * 作者：dsz
 * 时间：2014-07-15 PM
 * @author new
 *功能：印刷点运输表的实体类
 */

public class PrintSend {
	private Long print_id;//印刷厂编号
	private Long sub_id;//分站编号
	private Long pub_id;//刊物编号
	private Long pub_num;//刊物数量
	private String send_time;//运输时间
	private String transport_company_name;//运输公司
	//------------------------------------------------------------------------
	@Override
	public String toString() {
		return "PrintSend [print_id=" + print_id + ", pub_id=" + pub_id
				+ ", pub_num=" + pub_num + ", send_time=" + send_time
				+ ", sub_id=" + sub_id + ", transport_company_name="
				+ transport_company_name + ", getPrint_id()=" + getPrint_id()
				+ ", getPub_id()=" + getPub_id() + ", getPub_num()="
				+ getPub_num() + ", getSend_time()=" + getSend_time()
				+ ", getSub_id()=" + getSub_id()
				+ ", getTransport_company_name()="
				+ getTransport_company_name() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	//---------------------------------------------------------------------------
	public PrintSend(Long printId, Long subId, Long pubId, Long pubNum,
			String sendTime, String transportCompanyName) {
		super();
		print_id = printId;
		sub_id = subId;
		pub_id = pubId;
		pub_num = pubNum;
		send_time = sendTime;
		transport_company_name = transportCompanyName;
	}
	//-----------------------------------------------------------------------------
	public PrintSend() {
		super();
	}
	//------------------------------------------------------------------------------
	public Long getPrint_id() {
		return print_id;
	}
	public void setPrint_id(Long printId) {
		print_id = printId;
	}
	public Long getSub_id() {
		return sub_id;
	}
	public void setSub_id(Long subId) {
		sub_id = subId;
	}
	public Long getPub_id() {
		return pub_id;
	}
	public void setPub_id(Long pubId) {
		pub_id = pubId;
	}
	public Long getPub_num() {
		return pub_num;
	}
	public void setPub_num(Long pubNum) {
		pub_num = pubNum;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}
	public String getTransport_company_name() {
		return transport_company_name;
	}
	public void setTransport_company_name(String transportCompanyName) {
		transport_company_name = transportCompanyName;
	}
	


}
