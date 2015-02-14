package hit.poms.subStation.entity;

public class SubStation {
	public SubStation() {
		super();
	}
	public SubStation(Long subStationID, String subStationName,
			String subStationLeaderId, String subStationLeaderName,
			String subStationLeaderTel, String subStationProvince,
			String subStationCity, String subStaitonTown,
			String subStationHrInfor, String subStationPrInfor,
			String subStationJdInfor, String subStationBjInfor, String flag) {
		super();
		this.subStationID = subStationID;
		this.subStationName = subStationName;
		this.subStationLeaderId = subStationLeaderId;
		this.subStationLeaderName = subStationLeaderName;
		this.subStationLeaderTel = subStationLeaderTel;
		this.subStationProvince = subStationProvince;
		this.subStationCity = subStationCity;
		this.subStaitonTown = subStaitonTown;
		subStation_hr_infor = subStationHrInfor;
		subStation_pr_infor = subStationPrInfor;
		subStation_jd_infor = subStationJdInfor;
		subStation_bj_infor = subStationBjInfor;
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "SubStation [flag=" + flag + ", subStaitonTown="
				+ subStaitonTown + ", subStationCity=" + subStationCity
				+ ", subStationID=" + subStationID + ", subStationLeaderId="
				+ subStationLeaderId + ", subStationLeaderName="
				+ subStationLeaderName + ", subStationLeaderTel="
				+ subStationLeaderTel + ", subStationName=" + subStationName
				+ ", subStationProvince=" + subStationProvince
				+ ", subStation_bj_infor=" + subStation_bj_infor
				+ ", subStation_hr_infor=" + subStation_hr_infor
				+ ", subStation_jd_infor=" + subStation_jd_infor
				+ ", subStation_pr_infor=" + subStation_pr_infor + "]";
	}
	public Long getSubStationID() {
		return subStationID;
	}
	public void setSubStationID(Long subStationID) {
		this.subStationID = subStationID;
	}
	public String getSubStationName() {
		return subStationName;
	}
	public void setSubStationName(String subStationName) {
		this.subStationName = subStationName;
	}
	public String getSubStationLeaderId() {
		return subStationLeaderId;
	}
	public void setSubStationLeaderId(String subStationLeaderId) {
		this.subStationLeaderId = subStationLeaderId;
	}
	public String getSubStationLeaderName() {
		return subStationLeaderName;
	}
	public void setSubStationLeaderName(String subStationLeaderName) {
		this.subStationLeaderName = subStationLeaderName;
	}
	public String getSubStationLeaderTel() {
		return subStationLeaderTel;
	}
	public void setSubStationLeaderTel(String subStationLeaderTel) {
		this.subStationLeaderTel = subStationLeaderTel;
	}
	public String getSubStationProvince() {
		return subStationProvince;
	}
	public void setSubStationProvince(String subStationProvince) {
		this.subStationProvince = subStationProvince;
	}
	public String getSubStationCity() {
		return subStationCity;
	}
	public void setSubStationCity(String subStationCity) {
		this.subStationCity = subStationCity;
	}
	public String getSubStaitonTown() {
		return subStaitonTown;
	}
	public void setSubStaitonTown(String subStaitonTown) {
		this.subStaitonTown = subStaitonTown;
	}
	public String getSubStation_hr_infor() {
		return subStation_hr_infor;
	}
	public void setSubStation_hr_infor(String subStationHrInfor) {
		subStation_hr_infor = subStationHrInfor;
	}
	public String getSubStation_pr_infor() {
		return subStation_pr_infor;
	}
	public void setSubStation_pr_infor(String subStationPrInfor) {
		subStation_pr_infor = subStationPrInfor;
	}
	public String getSubStation_jd_infor() {
		return subStation_jd_infor;
	}
	public void setSubStation_jd_infor(String subStationJdInfor) {
		subStation_jd_infor = subStationJdInfor;
	}
	public String getSubStation_bj_infor() {
		return subStation_bj_infor;
	}
	public void setSubStation_bj_infor(String subStationBjInfor) {
		subStation_bj_infor = subStationBjInfor;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private Long subStationID;//分社ID
	private String subStationName;//分社姓名
	private String subStationLeaderId;//分社负责人ID
	private String subStationLeaderName;//分社负责人姓名
	private String subStationLeaderTel;//分社负责人电话
	private String subStationProvince;//分社所处省份
	private String subStationCity;//分社所处城市
	private String subStaitonTown;//分社所处县
	private String subStation_hr_infor;
	private String subStation_pr_infor;
	private String subStation_jd_infor;
	private String subStation_bj_infor;
	private String flag;
}
