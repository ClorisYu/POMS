package hit.poms.bonus.entity;

public class Coupons {
	 private  String coupons_id;
	 private  String coupons_type;
	 private  String coupons_money;
	 private  String coupons_infor;
	 private  String start_time;
	 private  String end_time;
	public String getCoupons_id() {
		return coupons_id;
	}
	public void setCoupons_id(String couponsId) {
		coupons_id = couponsId;
	}
	public String getCoupons_type() {
		return coupons_type;
	}
	public void setCoupons_type(String couponsType) {
		coupons_type = couponsType;
	}
	public String getCoupons_money() {
		return coupons_money;
	}
	public void setCoupons_money(String couponsMoney) {
		coupons_money = couponsMoney;
	}
	public String getCoupons_infor() {
		return coupons_infor;
	}
	public void setCoupons_infor(String couponsInfor) {
		coupons_infor = couponsInfor;
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
	@Override
	public String toString() {
		return "Coupons [coupons_id=" + coupons_id + ", coupons_infor="
				+ coupons_infor + ", coupons_money=" + coupons_money
				+ ", coupons_type=" + coupons_type + ", end_time=" + end_time
				+ ", start_time=" + start_time + "]";
	}
	public Coupons() {
		super();
	}
	public Coupons(String couponsId, String couponsType, String couponsMoney,
			String couponsInfor, String startTime, String endTime) {
		super();
		coupons_id = couponsId;
		coupons_type = couponsType;
		coupons_money = couponsMoney;
		coupons_infor = couponsInfor;
		start_time = startTime;
		end_time = endTime;
	}
	 
}
