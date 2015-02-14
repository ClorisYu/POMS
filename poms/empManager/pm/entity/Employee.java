package hit.poms.empManager.pm.entity;

/**
 * 员工信息
 * @author new
 *
 */
public class Employee {
	
	Long emp_id;
	String emp_name;
	String emp_gender;
	Long emp_dept_id;
	String emp_post;
	String emp_idnum;
	int age;
	String emp_tel;
	
	@Override
	public String toString() {
		return "Employee [age=" + age + ", emp_dept_id=" + emp_dept_id
				+ ", emp_gender=" + emp_gender + ", emp_id=" + emp_id
				+ ", emp_idnum=" + emp_idnum + ", emp_name=" + emp_name
				+ ", emp_post=" + emp_post + ", emp_tel=" + emp_tel + "]";
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Long empId, String empName, String empGender,
			Long empDeptId, String empPost, String empIdnum, int age,
			String empTel) {
		super();
		emp_id = empId;
		emp_name = empName;
		emp_gender = empGender;
		emp_dept_id = empDeptId;
		emp_post = empPost;
		emp_idnum = empIdnum;
		this.age = age;
		emp_tel = empTel;
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
	public String getEmp_idnum() {
		return emp_idnum;
	}
	public void setEmp_idnum(String empIdnum) {
		emp_idnum = empIdnum;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmp_tel() {
		return emp_tel;
	}
	public void setEmp_tel(String empTel) {
		emp_tel = empTel;
	}

}
