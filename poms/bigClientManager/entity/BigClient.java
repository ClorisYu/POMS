package hit.poms.bigClientManager.entity;

public class BigClient {
	private Long customer_id;
	private String customer_name;
	private Long order_id;
	private String if_paied;
	private Double pub_money;
	private Long pub_need_num;
	private Double paied_money;
	
	
	
	public BigClient(Long customerId, String customerName, Long orderId,
			String ifPaied, Double pubMoney, Long pubNeedNum, Double paiedMoney) {
		super();
		customer_id = customerId;
		customer_name = customerName;
		order_id = orderId;
		if_paied = ifPaied;
		pub_money = pubMoney;
		pub_need_num = pubNeedNum;
		paied_money = paiedMoney;
	}

	@Override
	public String toString() {
		return "BigClient [customer_id=" + customer_id + ", customer_name="
				+ customer_name + ", if_paied=" + if_paied + ", order_id="
				+ order_id + ", paied_money=" + paied_money + ", pub_money="
				+ pub_money + ", pub_need_num=" + pub_need_num + "]";
	}

	public Double getPub_money() {
		return pub_money;
	}

	public void setPub_money(Double pubMoney) {
		pub_money = pubMoney;
	}

	public Double getPaied_money() {
		return paied_money;
	}

	public void setPaied_money(Double paiedMoney) {
		paied_money = paiedMoney;
	}

	public BigClient() {
		super();
	}
	
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
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
	
	public Long getPub_need_num() {
		return pub_need_num;
	}
	public void setPub_need_num(Long pubNeedNum) {
		pub_need_num = pubNeedNum;
	}
	
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customerId) {
		customer_id = customerId;
	}
}
