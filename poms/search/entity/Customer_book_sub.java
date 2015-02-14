package hit.poms.search.entity;

public class Customer_book_sub {
	private String order_id;
	private String if_paied;
	private String pub_id;
	private String substation_id;
	private String customer_id;
	private String if_toudi;
	private String start_time;
	private String end_time;
	private String pub_money;
	private String pub_need_num;
	private String gift_type;
	private String discount;
	private String gift_num;
	private String paied_money;
	private String pay_way;
	private String pub_price;
	private String pub_month_price;
	private String pub_year_price;
	private String pub_type;
	private String pub_debook;
	private String substation_name;
	private String customer_name;
	private String customer_grade;
	private String customer_address;
	private String customer_phone;
	private String pub_name;
	private String pub_time;
	
	public Customer_book_sub() {
		super();
	}
	
	public Customer_book_sub(String orderId,String ifPaied, String pubId, String substationId,
			String customerId, String ifToudi, String startTime, String endTime,
			String pubMoney, String pubNeedNum, String giftType,
			String discount, String giftNum, String paiedMoney, String payWay) {
		super();
		order_id = orderId;
		if_paied = ifPaied;
		pub_id = pubId;
		substation_id = substationId;
		customer_id = customerId;
		if_toudi = ifToudi;
		start_time = startTime;
		end_time = endTime;
		pub_money = pubMoney;
		pub_need_num = pubNeedNum;
		gift_type = giftType;
		this.discount = discount;
		gift_num = giftNum;
		paied_money = paiedMoney;
		pay_way = payWay;
	}

	public Customer_book_sub(String orderId, String ifPaied, String pubId,
			String subId, String customerId, String startTime, String endTime,
			String pubMoney, String pubNeedNum, String giftType,
			String discount, String giftNum, String paiedMoney) {
		super();
		order_id = orderId;
		if_paied = ifPaied;
		pub_id = pubId;
		substation_id = subId;
		customer_id = customerId;
		start_time = startTime;
		end_time = endTime;
		pub_money = pubMoney;
		pub_need_num = pubNeedNum;
		gift_type = giftType;
		this.discount = discount;
		gift_num = giftNum;
		this.paied_money = paiedMoney;
	}

	public String getIf_toudi() {
		return if_toudi;
	}

	public void setIf_toudi(String ifToudi) {
		if_toudi = ifToudi;
	}

	public String getPay_way() {
		return pay_way;
	}

	public void setPay_way(String payWay) {
		pay_way = payWay;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}

	public String getPub_debook() {
		return pub_debook;
	}

	public void setPub_debook(String pubDebook) {
		pub_debook = pubDebook;
	}

	public String getPub_type() {
		return pub_type;
	}

	public void setPub_type(String pubType) {
		pub_type = pubType;
	}

	public String getPub_price() {
		return pub_price;
	}

	public void setPub_price(String pubPrice) {
		pub_price = pubPrice;
	}

	public String getPub_month_price() {
		return pub_month_price;
	}

	public void setPub_month_price(String pubMonthPrice) {
		pub_month_price = pubMonthPrice;
	}

	public String getPub_year_price() {
		return pub_year_price;
	}

	public void setPub_year_price(String pubYearPrice) {
		pub_year_price = pubYearPrice;
	}

	public String getPub_time() {
		return pub_time;
	}

	public void setPub_time(String pubTime) {
		pub_time = pubTime;
	}

	public String getPub_name() {
		return pub_name;
	}

	public void setPub_name(String pubName) {
		pub_name = pubName;
	}
	
	public String getCustomer_grade() {
		return customer_grade;
	}

	public void setCustomer_grade(String customerGrade) {
		customer_grade = customerGrade;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customerAddress) {
		customer_address = customerAddress;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customerPhone) {
		customer_phone = customerPhone;
	}

	public String getSubstation_name() {
		return substation_name;
	}

	public void setSubstation_name(String substationName) {
		substation_name = substationName;
	}
	
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String orderId) {
		order_id = orderId;
	}

	public String getIf_paied() {
		return if_paied;
	}
	
	public void setIf_paied(String ifPaied) {
		if_paied = ifPaied;
	}

	public String getSubstation_id() {
		return substation_id;
	}
	
	public void setSubstation_id(String subId) {
		substation_id = subId;
	}

	public String getPub_id() {
		return pub_id;
	}
	
	public void setPub_id(String pubId) {
		pub_id = pubId;
	}
	
	public String getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(String customerId) {
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
	
	public String getPub_money() {
		return pub_money;
	}
	
	public void setPub_money(String pubMoney) {
		pub_money = pubMoney;
	}
	
	public String getPub_need_num() {
		return pub_need_num;
	}
	
	public void setPub_need_num(String pubNeedNum) {
		pub_need_num = pubNeedNum;
	}
	
	public String getGift_type() {
		return gift_type;
	}
	
	public void setGift_type(String giftType) {
		gift_type = giftType;
	}
	
	public String getDiscount() {
		return discount;
	}
	
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public String getGift_num() {
		return gift_num;
	}
	
	public void setGift_num(String giftNum) {
		gift_num = giftNum;
	}

	public String getPaied_money() {
		return paied_money;
	}

	public void setPaied_money(String paiedMoney) {
		this.paied_money = paiedMoney;
	}
	
}
