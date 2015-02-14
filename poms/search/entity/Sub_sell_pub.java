package hit.poms.search.entity;

public class Sub_sell_pub {
	private String substation_id;
	private String pub_id;
	private String pub_send_num;
	private String send_time;
	private String get_money;
	
	public Sub_sell_pub() {
		super();
	}
	public Sub_sell_pub(String substationId, String pubId, String pubSendNum,
			String sendTime, String getMoney) {
		super();
		substation_id = substationId;
		pub_id = pubId;
		pub_send_num = pubSendNum;
		send_time = sendTime;
		get_money = getMoney;
	}
	public String getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(String substationId) {
		substation_id = substationId;
	}
	public String getPub_id() {
		return pub_id;
	}
	public void setPub_id(String pubId) {
		pub_id = pubId;
	}
	public String getPub_send_num() {
		return pub_send_num;
	}
	public void setPub_send_num(String pubSendNum) {
		pub_send_num = pubSendNum;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}
	public String getGet_money() {
		return get_money;
	}
	public void setGet_money(String getMoney) {
		get_money = getMoney;
	}
	
	
	
}
