package hit.poms.deliverManager.desManager.entity;

public class DesEmp {
	private Long emp_id;
	private Long des_id;
	private String destination;
	
	
	public DesEmp() {
		super();
	}
	public DesEmp(Long empId, Long desId, String destination) {
		super();
		emp_id = empId;
		des_id = desId;
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "DesEmp [des_id=" + des_id + ", destination=" + destination
				+ ", emp_id=" + emp_id + "]";
	}
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long empId) {
		emp_id = empId;
	}
	public Long getDes_id() {
		return des_id;
	}
	public void setDes_id(Long desId) {
		des_id = desId;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
