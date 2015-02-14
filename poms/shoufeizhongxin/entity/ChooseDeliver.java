package hit.poms.shoufeizhongxin.entity;

public class ChooseDeliver {
	private String emp_id;
	private String emp_name;
	private String destination;
	public ChooseDeliver(String empId, String empName, String destination) {
		super();
		emp_id = empId;
		emp_name = empName;
		this.destination = destination;
	}
	public ChooseDeliver() {
		super();
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String empId) {
		emp_id = empId;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String empName) {
		emp_name = empName;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "ChooseDeliver [destination=" + destination + ", emp_id="
				+ emp_id + ", emp_name=" + emp_name + "]";
	}
}
