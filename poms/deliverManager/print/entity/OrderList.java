package hit.poms.deliverManager.print.entity;

public class OrderList {
	private Long order_id;
	private String if_paied;
	private Long pub_id;
	private String pub_name;
	private Long customer_id;
	private String customer_name;
	private double pub_money;
	private String pub_need_num;
	private String customer_address;
	
	
	public OrderList() {
		super();
	}
	public OrderList(Long orderId, String ifPaied, Long pubId, String pubName,
			Long customerId, String customerName, double pubMoney,
			String pubNeedNum, String customerAddress) {
		super();
		order_id = orderId;
		if_paied = ifPaied;
		pub_id = pubId;
		pub_name = pubName;
		customer_id = customerId;
		customer_name = customerName;
		pub_money = pubMoney;
		pub_need_num = pubNeedNum;
		customer_address = customerAddress;
	}
	@Override
	public String toString() {
		return "OrderList [customer_address=" + customer_address
				+ ", customer_id=" + customer_id + ", customer_name="
				+ customer_name + ", if_paied=" + if_paied + ", order_id="
				+ order_id + ", pub_id=" + pub_id + ", pub_money=" + pub_money
				+ ", pub_name=" + pub_name + ", pub_need_num=" + pub_need_num
				+ "]";
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long orderId) {
		order_id = orderId;
	}
	public String getIf_paied() {
		return if_paied;
	}
	public void setIf_paied(String ifPaied) {
		if_paied = ifPaied;
	}
	public Long getPub_id() {
		return pub_id;
	}
	public void setPub_id(Long pubId) {
		pub_id = pubId;
	}
	public String getPub_name() {
		return pub_name;
	}
	public void setPub_name(String pubName) {
		pub_name = pubName;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customerId) {
		customer_id = customerId;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}
	public double getPub_money() {
		return pub_money;
	}
	public void setPub_money(double pubMoney) {
		pub_money = pubMoney;
	}
	public String getPub_need_num() {
		return pub_need_num;
	}
	public void setPub_need_num(String pubNeedNum) {
		pub_need_num = pubNeedNum;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customerAddress) {
		customer_address = customerAddress;
	}
	
	
}
