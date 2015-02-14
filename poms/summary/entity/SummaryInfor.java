package hit.poms.summary.entity;

public class SummaryInfor {
	private Long pub_id;
	private Long substation_id;
	private String pub_name;
	private String substation_name;
	private String summary_date;
	public SummaryInfor(Long pubId, Long substationId, String pubName,
			String substationName, String summaryDate, Long orderNum,
			Long realNum) {
		super();
		pub_id = pubId;
		substation_id = substationId;
		pub_name = pubName;
		substation_name = substationName;
		summary_date = summaryDate;
		order_num = orderNum;
		real_num = realNum;
	}
	public SummaryInfor() {
		super();
	}
	@Override
	public String toString() {
		return "SummaryInfor [pub_id=" + pub_id + ", pub_name=" + pub_name
				+ ", real_num=" + real_num + ", order_num=" + order_num
				+ ", substation_id=" + substation_id + ", substation_name="
				+ substation_name + ", summary_date=" + summary_date + "]";
	}
	public Long getPub_id() {
		return pub_id;
	}
	public void setPub_id(Long pubId) {
		pub_id = pubId;
	}
	public Long getSubstation_id() {
		return substation_id;
	}
	public void setSubstation_id(Long substationId) {
		substation_id = substationId;
	}
	public String getPub_name() {
		return pub_name;
	}
	public void setPub_name(String pubName) {
		pub_name = pubName;
	}
	public String getSubstation_name() {
		return substation_name;
	}
	public void setSubstation_name(String substationName) {
		substation_name = substationName;
	}
	public String getSummary_date() {
		return summary_date;
	}
	public void setSummary_date(String summaryDate) {
		summary_date = summaryDate;
	}
	public Long getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Long orderNum) {
		order_num = orderNum;
	}
	public Long getReal_num() {
		return real_num;
	}
	public void setReal_num(Long realNum) {
		real_num = realNum;
	}
	private Long order_num;
	private Long real_num;
	
	
}
