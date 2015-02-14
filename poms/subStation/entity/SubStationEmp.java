package hit.poms.subStation.entity;

public class SubStationEmp {

	public SubStationEmp() {
		super();
	}
	
	public SubStationEmp(String id, String name, String tel, String gender,
			String job) {
		super();
		Id = id;
		Name = name;
		Tel = tel;
		Gender = gender;
		Job = job;
	}
	
	@Override
	public String toString() {
		return "SubStationEmp [Gender=" + Gender + ", Id=" + Id + ", Job="
				+ Job + ", Name=" + Name + ", Tel=" + Tel + "]";
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}
	
	private String Id;
	private String Name;
	private String Tel;
	private String Gender;
	private String Job;
}
