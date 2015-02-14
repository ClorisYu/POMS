package hit.poms.correctdistribution.entity;

public class Correction {
	private Long order_id = 0L;
	private Long customer_id = 0L;
	private String customer_name = null;
	private String start_time = null;
	private String end_time = null;
	private String address = null;
	private String if_toudi = null;
	private Long  sub_id = 0L;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIf_toudi() {
		return if_toudi;
	}
	public void setIf_toudi(String ifToudi) {
		if_toudi = ifToudi;
	}
	public Long getSub_id() {
		return sub_id;
	}
	public void setSub_id(Long subId) {
		sub_id = subId;
	}
	public Correction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Correction(Long orderId, Long customerId, String customerName,
			String startTime, String endTime, String address, String ifToudi,
			Long subId) {
		super();
		order_id = orderId;
		customer_id = customerId;
		customer_name = customerName;
		start_time = startTime;
		end_time = endTime;
		this.address = address;
		if_toudi = ifToudi;
		sub_id = subId;
	}
	
}
