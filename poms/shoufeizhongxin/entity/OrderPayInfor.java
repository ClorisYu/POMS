package hit.poms.shoufeizhongxin.entity;

public class OrderPayInfor {
	private String order_id;
	private String customer_id;
	private String customer_name;
	private String if_paied;
	private String start_time;
	private String deliver_time;
	private String if_deliver;
	private String pay_money;
	private String pay_way;
	private String end_time;
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	
	public OrderPayInfor(String orderId, String customerId,
			String customerName, String ifPaied, String startTime,
			String deliverTime, String ifDeliver, String payMoney,
			String payWay, String endTime) {
		super();
		order_id = orderId;
		customer_id = customerId;
		customer_name = customerName;
		if_paied = ifPaied;
		start_time = startTime;
		deliver_time = deliverTime;
		if_deliver = ifDeliver;
		pay_money = payMoney;
		pay_way = payWay;
		end_time = endTime;
	}
	@Override
	public String toString() {
		return "OrderPayInfor [customer_id=" + customer_id + ", customer_name="
				+ customer_name + ", deliver_time=" + deliver_time
				+ ", end_time=" + end_time + ", if_deliver=" + if_deliver
				+ ", if_paied=" + if_paied + ", order_id=" + order_id
				+ ", pay_money=" + pay_money + ", pay_way=" + pay_way
				+ ", start_time=" + start_time + "]";
	}
	public String getDeliver_time() {
		return deliver_time;
	}
	public void setDeliver_time(String deliverTime) {
		deliver_time = deliverTime;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String payWay) {
		pay_way = payWay;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String orderId) {
		order_id = orderId;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customerId) {
		customer_id = customerId;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}
	public String getIf_paied() {
		return if_paied;
	}
	public void setIf_paied(String ifPaied) {
		if_paied = ifPaied;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String startTime) {
		start_time = startTime;
	}
	public String getIf_deliver() {
		return if_deliver;
	}
	public void setIf_deliver(String ifDeliver) {
		if_deliver = ifDeliver;
	}
	public String getPay_money() {
		return pay_money;
	}
	public void setPay_money(String payMoney) {
		pay_money = payMoney;
	}
	public OrderPayInfor() {
		super();
	}
	
	
}
