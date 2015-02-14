package hit.poms.dao.entity;

import java.util.Date;

/**
 * 作者：dsz
 * 时间：2014-07-15 AM
 * @author new
 *功能：附赠卡信息表的实体类
 */

public class BonusCard {
	private Long pub_num;//可以订阅刊物id
	private String start_time;//起始时间
	private String end_time;//终止时间
	private Long gift_num;//附赠品编号
	
	
	@Override
	public String toString() {
		return "BonusCard [end_time=" + end_time + ", gift_num=" + gift_num
				+ ", pub_num=" + pub_num + ", start_time=" + start_time + "]";
	}
	
	
	public BonusCard(Long pubNum, String startTime, String endTime, Long giftNum) {
		super();
		pub_num = pubNum;
		start_time = startTime;
		end_time = endTime;
		gift_num = giftNum;
	}
	
	
	public BonusCard() {
		super();
	}
	
	
	public Long getPub_num() {
		return pub_num;
	}
	public void setPub_num(Long pubNum) {
		pub_num = pubNum;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String startTime) {
		start_time = startTime;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	public Long getGift_num() {
		return gift_num;
	}
	public void setGift_num(Long giftNum) {
		gift_num = giftNum;
	}
	


}
