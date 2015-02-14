package hit.poms.search.entity;

public class Change_reason {
	private String change_id;
	private String customer_id;
	private String order_id;
	private String change_type;
	private String reason_type;
	private String reason;
	public Change_reason() {
		super();
	}
	public Change_reason(String changeId, String customerId, String orderId,
			String changeType, String reasonType, String reason) {
		super();
		change_id = changeId;
		customer_id = customerId;
		order_id = orderId;
		change_type = changeType;
		reason_type = reasonType;
		this.reason = reason;
	}
	public String getChange_id() {
		return change_id;
	}
	public void setChange_id(String changeId) {
		change_id = changeId;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customerId) {
		customer_id = customerId;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String orderId) {
		order_id = orderId;
	}
	public String getChange_type() {
		return change_type;
	}
	public void setChange_type(String changeType) {
		change_type = changeType;
	}
	public String getReason_type() {
		return reason_type;
	}
	public void setReason_type(String reasonType) {
		reason_type = reasonType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
