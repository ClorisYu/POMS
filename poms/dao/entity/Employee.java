package hit.poms.dao.entity;

/**
 * ���ߣ�dsz
 * ʱ�䣺2014-07-15 PM
 * @author new
 *���ܣ�Ա����Ϣ���ʵ����
 */

public class Employee {
	private Long emp_id;//Ա�����
	private String emp_name;//Ա������
	private String emp_gender;//Ա���Ա�
	private Long emp_dept_id;//Ա�� ���ڲ��ŵı��
	private String emp_post;//Ա����ְ��
	private Double emp_salary;//Ա���Ĺ���
	private Double emp_bonus;//Ա���Ľ���
	private String emp_password;//Ա����¼ϵͳ������
	private String emp_Idnum;//Ա�����֤����
	private Long emp_order;//������
	private Long emp_delivery;//Ͷ����
	private Long emp_age;//Ա������
	private String emp_tel;//Ա���ĵ绰����
	private String if_online;//是否在线
	
	
	@Override
	public String toString() {
		return "Employee [emp_Idnum=" + emp_Idnum + ", emp_age=" + emp_age
				+ ", emp_bonus=" + emp_bonus + ", emp_delivery=" + emp_delivery
				+ ", emp_dept_id=" + emp_dept_id + ", emp_gender=" + emp_gender
				+ ", emp_id=" + emp_id + ", emp_name=" + emp_name
				+ ", emp_order=" + emp_order + ", emp_password=" + emp_password
				+ ", emp_post=" + emp_post + ", emp_salary=" + emp_salary
				+ ", emp_tel=" + emp_tel + ", if_online=" + if_online
				+ ", getEmp_Idnum()=" + getEmp_Idnum() + ", getEmp_age()="
				+ getEmp_age() + ", getEmp_bonus()=" + getEmp_bonus()
				+ ", getEmp_delivery()=" + getEmp_delivery()
				+ ", getEmp_dept_id()=" + getEmp_dept_id()
				+ ", getEmp_gender()=" + getEmp_gender() + ", getEmp_id()="
				+ getEmp_id() + ", getEmp_name()=" + getEmp_name()
				+ ", getEmp_order()=" + getEmp_order() + ", getEmp_password()="
				+ getEmp_password() + ", getEmp_post()=" + getEmp_post()
				+ ", getEmp_salary()=" + getEmp_salary() + ", getEmp_tel()="
				+ getEmp_tel() + ", getIf_online()=" + getIf_online()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public Employee(Long empId, String empName, String empGender, Long empDeptId,
			String empPost, Double empSalary, Double empBonus,
			String empPassword, String empIdnum, Long empOrder,
			Long empDelivery, Long empAge, String empTel, String ifOnline) {
		super();
		emp_id = empId;
		emp_name = empName;
		emp_gender = empGender;
		emp_dept_id = empDeptId;
		emp_post = empPost;
		emp_salary = empSalary;
		emp_bonus = empBonus;
		emp_password = empPassword;
		emp_Idnum = empIdnum;
		emp_order = empOrder;
		emp_delivery = empDelivery;
		emp_age = empAge;
		emp_tel = empTel;
		if_online = ifOnline;
	}
	public Employee() {
		super();
	}
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long empId) {
		emp_id = empId;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String empName) {
		emp_name = empName;
	}
	public String getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(String empGender) {
		emp_gender = empGender;
	}
	public Long getEmp_dept_id() {
		return emp_dept_id;
	}
	public void setEmp_dept_id(Long empDeptId) {
		emp_dept_id = empDeptId;
	}
	public String getEmp_post() {
		return emp_post;
	}
	public void setEmp_post(String empPost) {
		emp_post = empPost;
	}
	public Double getEmp_salary() {
		return emp_salary;
	}
	public void setEmp_salary(Double empSalary) {
		emp_salary = empSalary;
	}
	public Double getEmp_bonus() {
		return emp_bonus;
	}
	public void setEmp_bonus(Double empBonus) {
		emp_bonus = empBonus;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String empPassword) {
		emp_password = empPassword;
	}
	public String getEmp_Idnum() {
		return emp_Idnum;
	}
	public void setEmp_Idnum(String empIdnum) {
		emp_Idnum = empIdnum;
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
	public Long getEmp_age() {
		return emp_age;
	}
	public void setEmp_age(Long empAge) {
		emp_age = empAge;
	}
	public String getEmp_tel() {
		return emp_tel;
	}
	public void setEmp_tel(String empTel) {
		emp_tel = empTel;
	}
	public String getIf_online() {
		return if_online;
	}
	public void setIf_online(String ifOnline) {
		if_online = ifOnline;
	}
	
	
	
	

}
