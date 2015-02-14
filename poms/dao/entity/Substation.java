package hit.poms.dao.entity;
/**
 * ���ߣ�dsz
 * ʱ�䣺2014-07-15 PM
 * @author new
 *���ܣ���վ���ʵ����
 */

public class Substation {
	private Long substation_id;//��վ���
	private String substation_name;//��վ����
	private Long substation_leader_id;//��վ�쵼�˱��
	private String substation_provice;//��վ����
	private String substation_city;//��վ����
	private String substation_town;//��վ����
	
	
	@Override
	public String toString() {
		return "Substation [substation_city=" + substation_city
				+ ", substation_id=" + substation_id
				+ ", substation_leader_id=" + substation_leader_id
				+ ", substation_name=" + substation_name
				+ ", substation_provice=" + substation_provice
				+ ", substation_town=" + substation_town
				+ ", getSubstation_city()=" + getSubstation_city()
				+ ", getSubstation_id()=" + getSubstation_id()
				+ ", getSubstation_leader_id()=" + getSubstation_leader_id()
				+ ", getSubstation_name()=" + getSubstation_name()
				+ ", getSubstation_provice()=" + getSubstation_provice()
				+ ", getSubstation_town()=" + getSubstation_town()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public Substation(Long substationId, String substationName,
			Long substationLeaderId, String substationProvice,
			String substationCity, String substationTown) {
		super();
		substation_id = substationId;
		substation_name = substationName;
		substation_leader_id = substationLeaderId;
		substation_provice = substationProvice;
		substation_city = substationCity;
		substation_town = substationTown;
	}
	public Substation() {
		super();
	}
	public Long getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(Long substationId) {
		substation_id = substationId;
	}
	public String getSubstation_name() {
		return substation_name;
	}
	public void setSubstation_name(String substationName) {
		substation_name = substationName;
	}
	public Long getSubstation_leader_id() {
		return substation_leader_id;
	}
	public void setSubstation_leader_id(Long substationLeaderId) {
		substation_leader_id = substationLeaderId;
	}
	public String getSubstation_provice() {
		return substation_provice;
	}
	public void setSubstation_provice(String substationProvice) {
		substation_provice = substationProvice;
	}
	public String getSubstation_city() {
		return substation_city;
	}
	public void setSubstation_city(String substationCity) {
		substation_city = substationCity;
	}
	public String getSubstation_town() {
		return substation_town;
	}
	public void setSubstation_town(String substationTown) {
		substation_town = substationTown;
	}
	
	
}
