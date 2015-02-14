package hit.poms.dao.entity;

public class CompanyMessage {
	private String type_1;
	private String type_2;
	private String type_3;
	private String type_4;
	private String type_5;
	private String content_1;
	private String content_2;
	private String content_3;
	private String content_4;
	private String content_5;
	@Override
	public String toString() {
		return "CompanyMessage [content_1=" + content_1 + ", content_2="
				+ content_2 + ", content_3=" + content_3 + ", content_4="
				+ content_4 + ", content_5=" + content_5 + ", type_1=" + type_1
				+ ", type_2=" + type_2 + ", type_3=" + type_3 + ", type_4="
				+ type_4 + ", type_5=" + type_5 + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	public CompanyMessage(String type_1, String type_2, String type_3,
			String type_4, String type_5, String content_1, String content_2,
			String content_3, String content_4, String content_5) {
		super();
		this.type_1 = type_1;
		this.type_2 = type_2;
		this.type_3 = type_3;
		this.type_4 = type_4;
		this.type_5 = type_5;
		this.content_1 = content_1;
		this.content_2 = content_2;
		this.content_3 = content_3;
		this.content_4 = content_4;
		this.content_5 = content_5;
	}

	public CompanyMessage() {
		super();
	}

	public String getType_1() {
		return type_1;
	}
	public void setType_1(String type_1) {
		this.type_1 = type_1;
	}
	public String getType_2() {
		return type_2;
	}
	public void setType_2(String type_2) {
		this.type_2 = type_2;
	}
	public String getType_3() {
		return type_3;
	}
	public void setType_3(String type_3) {
		this.type_3 = type_3;
	}
	public String getType_4() {
		return type_4;
	}
	public void setType_4(String type_4) {
		this.type_4 = type_4;
	}
	public String getType_5() {
		return type_5;
	}
	public void setType_5(String type_5) {
		this.type_5 = type_5;
	}
	public String getContent_1() {
		return content_1;
	}
	public void setContent_1(String content_1) {
		this.content_1 = content_1;
	}
	public String getContent_2() {
		return content_2;
	}
	public void setContent_2(String content_2) {
		this.content_2 = content_2;
	}
	public String getContent_3() {
		return content_3;
	}
	public void setContent_3(String content_3) {
		this.content_3 = content_3;
	}
	public String getContent_4() {
		return content_4;
	}
	public void setContent_4(String content_4) {
		this.content_4 = content_4;
	}
	public String getContent_5() {
		return content_5;
	}
	public void setContent_5(String content_5) {
		this.content_5 = content_5;
	}
	

}
