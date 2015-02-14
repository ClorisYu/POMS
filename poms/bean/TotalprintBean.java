package hit.poms.bean;

public class TotalprintBean {
//	<th>分社名</th>
//    <th>分社编号</th>
//    <th>报刊号</th>
//    <th>报刊名</th>
//    <th>数量</th>
	private String sub_name;
	private long sub_id;
	private String newspaper_name;
	private long newspaper_id;
	private int  count;
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String subName) {
		sub_name = subName;
	}
	public long getSub_id() {
		return sub_id;
	}
	public void setSub_id(long subId) {
		sub_id = subId;
	}
	public String getNewspaper_name() {
		return newspaper_name;
	}
	public void setNewspaper_name(String newspaperName) {
		newspaper_name = newspaperName;
	}
	public long getNewspaper_id() {
		return newspaper_id;
	}
	public void setNewspaper_id(long newspaperId) {
		newspaper_id = newspaperId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
