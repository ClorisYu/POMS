package hit.poms.search.entity;

public class Sub_log {
	private String new_order_num;
	private String end_order_num;
	private String start_time;
	private String end_time;
	private String substation_id;
	private String substation_name;
	public Sub_log() {
		super();
	}
	public Sub_log(String newOrderNum, String endOrderNum, String startTime,
			String endTime, String substationId) {
		super();
		new_order_num = newOrderNum;
		end_order_num = endOrderNum;
		start_time = startTime;
		end_time = endTime;
		substation_id = substationId;
	}
	public String getNew_order_num() {
		return new_order_num;
	}
	public void setNew_order_num(String newOrderNum) {
		new_order_num = newOrderNum;
	}
	public String getEnd_order_num() {
		return end_order_num;
	}
	public void setEnd_order_num(String endOrderNum) {
		end_order_num = endOrderNum;
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
	public String getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(String substationId) {
		substation_id = substationId;
	}
	public String getSubstation_name() {
		return substation_name;
	}
	public void setSubstation_name(String substationName) {
		substation_name = substationName;
	}

}
