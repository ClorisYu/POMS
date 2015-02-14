package hit.poms.bean;

public class transportBean {
//	</tr>
//	<tr>
//	<td>公司名称</td>
//		<td><input type="text" name="compname"/></td></tr>
//	<tr>
//		<td>负责人</td>
//		<td><input type="text" name="leader"/></td>
//	</tr>
//	<tr>
//		<td>负责人联系方式</td>
//		<td><input type="text" name="leadercontract"/></td>
//	</tr>
//	<tr>
//		<td>公司地点</td>
//		<td><input type="text" name="address"/></td>
//	</tr>
//	<tr>
//		<td>可用时段</td>
//		<td><input type="text" name="avitime"/></td>
//	</tr>
//	<tr>
	private String  compname;
	private String leadername;
	private long leadercontract;
	private String address;
	private String davitime;
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname2) {
		this.compname = compname2;
	}
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public long getLeadercontract() {
		return leadercontract;
	}
	public void setLeadercontract(long leadercontract) {
		this.leadercontract = leadercontract;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDavitime() {
		return davitime;
	}
	public void setDavitime(String davitime) {
		this.davitime = davitime;
	}
	public transportBean(String compname, String leadername, long leadercontract,
			String address, String davitime) {
		super();
		this.compname = compname;
		this.leadername = leadername;
		this.leadercontract = leadercontract;
		this.address = address;
		this.davitime = davitime;
	}
	public transportBean() {
	}
	
}
