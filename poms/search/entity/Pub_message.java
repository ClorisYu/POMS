package hit.poms.search.entity;

public class Pub_message {
	private String pub_id;
	private String pub_name;
	private String print_num;
	private String sell_num;
	private String rest_num;
	private String image;
	private String pub_price;
	private String pub_type;
	private String pub_debook;
	private String pub_redirect;
	private String pub_month_price;
	private String pub_year_price;
	private String pub_release_rate;
	private String pub_time;
	private String delivery_unit;
	private String discount1;
	private String discount2;
	
	public Pub_message() {
		super();
	}

	public Pub_message(String pubId, String sellNum, String restNum) {
		super();
		pub_id = pubId;
		sell_num = sellNum;
		rest_num = restNum;
	}

	public Pub_message(String pubId, String pubName, String printNum, String sellNum,
			String restNum, String image, String pubPrice, String pubType,
			String pubDebook, String pubRedirect, String pubMonthPrice,
			String pubYearPrice, String pubReleaseRate, String pubTime,
			String deliveryUnit) {
		super();
		pub_id = pubId;
		pub_name = pubName;
		print_num = printNum;
		sell_num = sellNum;
		rest_num = restNum;
		this.image = image;
		pub_price = pubPrice;
		pub_type = pubType;
		pub_debook = pubDebook;
		pub_redirect = pubRedirect;
		pub_month_price = pubMonthPrice;
		pub_year_price = pubYearPrice;
		pub_release_rate = pubReleaseRate;
		pub_time = pubTime;
		delivery_unit = deliveryUnit;
	}

	public String getDiscount1() {
		return discount1;
	}

	public void setDiscount1(String discount1) {
		this.discount1 = discount1;
	}

	public String getDiscount2() {
		return discount2;
	}

	public void setDiscount2(String discount2) {
		this.discount2 = discount2;
	}

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

	public String getPrint_num() {
		return print_num;
	}

	public void setPrint_num(String printNum) {
		print_num = printNum;
	}

	public String getSell_num() {
		return sell_num;
	}

	public void setSell_num(String sellNum) {
		sell_num = sellNum;
	}

	public String getRest_num() {
		return rest_num;
	}

	public void setRest_num(String restNum) {
		rest_num = restNum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPub_price() {
		return pub_price;
	}

	public void setPub_price(String pubPrice) {
		pub_price = pubPrice;
	}

	public String getPub_type() {
		return pub_type;
	}

	public void setPub_type(String pubType) {
		pub_type = pubType;
	}

	public String getPub_debook() {
		return pub_debook;
	}

	public void setPub_debook(String pubDebook) {
		pub_debook = pubDebook;
	}

	public String getPub_redirect() {
		return pub_redirect;
	}

	public void setPub_redirect(String pubRedirect) {
		pub_redirect = pubRedirect;
	}

	public String getPub_month_price() {
		return pub_month_price;
	}

	public void setPub_month_price(String pubMonthPrice) {
		pub_month_price = pubMonthPrice;
	}

	public String getPub_year_price() {
		return pub_year_price;
	}

	public void setPub_year_price(String pubYearPrice) {
		pub_year_price = pubYearPrice;
	}

	public String getPub_release_rate() {
		return pub_release_rate;
	}

	public void setPub_release_rate(String pubReleaseRate) {
		pub_release_rate = pubReleaseRate;
	}

	public String getPub_time() {
		return pub_time;
	}

	public void setPub_time(String pubTime) {
		pub_time = pubTime;
	}

	public String getDelivery_unit() {
		return delivery_unit;
	}

	public void setDelivery_unit(String deliveryUnit) {
		delivery_unit = deliveryUnit;
	}
	
}
