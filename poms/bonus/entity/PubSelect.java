package hit.poms.bonus.entity;

public class PubSelect {
	private String pub_id;
	private String pub_name;
	public String getPub_id() {
		return pub_id;
	}
	public void setPub_id(String pubId) {
		pub_id = pubId;
	}
	public String getPub_name() {
		return pub_name;
	}
	public void setPub_name(String pubName) {
		pub_name = pubName;
	}
	@Override
	public String toString() {
		return "PubSelect [pub_id=" + pub_id + ", pub_name=" + pub_name + "]";
	}
	public PubSelect() {
		super();
	}
	public PubSelect(String pubId, String pubName) {
		super();
		pub_id = pubId;
		pub_name = pubName;
	}
	
}
