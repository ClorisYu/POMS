package hit.poms.deliverManager.orderNum.entity;

public class PrintSend {
	private Long pub_id;
	private Long pub_num;
	private Long print_id;
	private String transport_company_name;
	private Long sub_id;
	private String send_time;
	
	
	public PrintSend() {
		super();
	}
	public PrintSend(Long pubId, Long pubNum, Long printId,
			String transportCompanyName, Long subId, String sendTime) {
		super();
		pub_id = pubId;
		pub_num = pubNum;
		print_id = printId;
		transport_company_name = transportCompanyName;
		sub_id = subId;
		send_time = sendTime;
	}
	@Override
	public String toString() {
		return "PrintSend [print_id=" + print_id + ", pub_id=" + pub_id
				+ ", pub_num=" + pub_num + ", send_time=" + send_time
				+ ", sub_id=" + sub_id + ", transport_company_name="
				+ transport_company_name + "]";
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
	public Long getPrint_id() {
		return print_id;
	}
	public void setPrint_id(Long printId) {
		print_id = printId;
	}
	public String getTransport_company_name() {
		return transport_company_name;
	}
	public void setTransport_company_name(String transportCompanyName) {
		transport_company_name = transportCompanyName;
	}
	public Long getSub_id() {
		return sub_id;
	}
	public void setSub_id(Long subId) {
		sub_id = subId;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}
	
}
