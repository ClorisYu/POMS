package hit.poms.empManager.businessReport.entity;

public class Report {
	private String emp_name;
	private Long emp_id;
	private Long pub_id;
	private String pub_name;
	private Long emp_order;
	private Long emp_delivery;
	private String fin_time;
	
	
	public Report() {
		super();
	}
	
	public Report(String empName, Long empId, Long pubId, String pubName,
			Long empOrder, Long empDelivery, String finTime) {
		super();
		emp_name = empName;
		emp_id = empId;
		pub_id = pubId;
		pub_name = pubName;
		emp_order = empOrder;
		emp_delivery = empDelivery;
		fin_time = finTime;
	}

	@Override
	public String toString() {
		return "Report [emp_delivery=" + emp_delivery + ", emp_id=" + emp_id
				+ ", emp_name=" + emp_name + ", emp_order=" + emp_order
				+ ", fin_time=" + fin_time + ", pub_id=" + pub_id
				+ ", pub_name=" + pub_name + "]";
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String empName) {
		emp_name = empName;
	}
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long empId) {
		emp_id = empId;
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
	
	public Long getEmp_order() {
		return emp_order;
	}

	public void setEmp_order(Long empOrder) {
		emp_order = empOrder;
	}

	public Long getEmp_delivery() {
		return emp_delivery;
	}

	public void setEmp_delivery(Long empDelivery) {
		emp_delivery = empDelivery;
	}

	public String getFin_time() {
		return fin_time;
	}
	public void setFin_time(String finTime) {
		fin_time = finTime;
	}
	
}
