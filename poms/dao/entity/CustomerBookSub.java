package hit.poms.dao.entity;

import java.util.Date;

/**
 * 作者：dsz
 * 时间：2014-07-15 PM
 * @author new
 *功能：顾客刊物对照表的实体类
 */
public class CustomerBookSub {
	private Long pub_id;//所订刊物编号
	private Long customer_id;//顾客编号
	private String start_time;//起始时间
	private String end_time;//到期时间
	private Double pub_money;//所订购刊物的金额
	private String customer_address;;//顾客地址
	private Long pub_need_num;//所需数量
	private char gift_type;//附赠品类型(0:没有；1:附赠卡2:代金券)
	private double discount;//根据顾客类型和顾客订购的刊物数量、时间来确定折扣，可以为空
	private Long gift_num;//附赠品编号
	
	
	@Override
	public String toString() {
		return "CustomerBookSub [customer_address=" + customer_address
				+ ", customer_id=" + customer_id + ", discount=" + discount
				+ ", end_time=" + end_time + ", gift_num=" + gift_num
				+ ", gift_type=" + gift_type + ", pub_id=" + pub_id
				+ ", pub_money=" + pub_money + ", pub_need_num=" + pub_need_num
				+ ", start_time=" + start_time + "]";
	}
	
	
	public CustomerBookSub(Long pubId, Long customerId, String startTime,
			String endTime, Double pubMoney, String customerAddress,
			Long pubNeedNum, char giftType, double discount, Long giftNum) {
		super();
		pub_id = pubId;
		customer_id = customerId;
		start_time = startTime;
		end_time = endTime;
		pub_money = pubMoney;
		customer_address = customerAddress;
		pub_need_num = pubNeedNum;
		gift_type = giftType;
		this.discount = discount;
		gift_num = giftNum;
	}
	
	
	public CustomerBookSub() {
		super();
	}
	
	
	public Long getPub_id() {
		return pub_id;
	}
	public void setPub_id(Long pubId) {
		pub_id = pubId;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customerId) {
		customer_id = customerId;
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
	public Double getPub_money() {
		return pub_money;
	}
	public void setPub_money(Double pubMoney) {
		pub_money = pubMoney;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customerAddress) {
		customer_address = customerAddress;
	}
	public Long getPub_need_num() {
		return pub_need_num;
	}
	public void setPub_need_num(Long pubNeedNum) {
		pub_need_num = pubNeedNum;
	}
	public char getGift_type() {
		return gift_type;
	}
	public void setGift_type(char giftType) {
		gift_type = giftType;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Long getGift_num() {
		return gift_num;
	}
	public void setGift_num(Long giftNum) {
		gift_num = giftNum;
	}
	

	

}
