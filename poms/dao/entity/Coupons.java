package hit.poms.dao.entity;

import java.sql.Date;
/**
 * 作者：dsz
 * 时间：2014-07-15 PM
 * @author new
 *功能：代金券表的实体类
 */
public class Coupons {
	private Double money;//可以代替现金多少
	private String start_time;//起始时间
	private String end_time;//终止时间
	private Long gift_num;//附赠品编号
	
	@Override
	public String toString() {
		return "Coupons [end_time=" + end_time + ", gift_num=" + gift_num
				+ ", money=" + money + ", start_time=" + start_time
				+ ", getEnd_time()=" + getEnd_time() + ", getGift_num()="
				+ getGift_num() + ", getMoney()=" + getMoney()
				+ ", getStart_time()=" + getStart_time() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public Coupons(Double money, String startTime, String endTime, Long giftNum) {
		super();
		this.money = money;
		start_time = startTime;
		end_time = endTime;
		gift_num = giftNum;
	}
	public Coupons() {
		super();
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
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
