package hit.poms.dao.entity;

import java.util.Date;

/**
 * 作者：dsz
 * 时间：2014-07-15 AM
 * @author new
 *功能：分站刊物对照表的实体类
 */


public class SubSellPub {
	private Long substation_id;//与分站表对照
	private Long pub_id;//与刊物表对照
	private Long pub_send_num;//此类刊物卖出数量
	private String send_time;//卖出时间
	private Double get_money;//所收金额
	
	
	@Override
	public String toString() {
		return "SubSellPub [get_money=" + get_money + ", pub_id=" + pub_id
				+ ", pub_send_num=" + pub_send_num + ", send_time=" + send_time
				+ ", substation_id=" + substation_id + "]";
	}
	
	
	public SubSellPub(Long substationId, Long pubId, Long pubSendNum,
			String sendTime, Double getMoney) {
		super();
		substation_id = substationId;
		pub_id = pubId;
		pub_send_num = pubSendNum;
		send_time = sendTime;
		get_money = getMoney;
	}
	
	
	public SubSellPub() {
		super();
	}
	
	
	public Long getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(Long substationId) {
		substation_id = substationId;
	}
	public Long getPub_id() {
		return pub_id;
	}
	public void setPub_id(Long pubId) {
		pub_id = pubId;
	}
	public Long getPub_send_num() {
		return pub_send_num;
	}
	public void setPub_send_num(Long pubSendNum) {
		pub_send_num = pubSendNum;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String sendTime) {
		send_time = sendTime;
	}
	public Double getGet_money() {
		return get_money;
	}
	public void setGet_money(Double getMoney) {
		get_money = getMoney;
	}
	

}
