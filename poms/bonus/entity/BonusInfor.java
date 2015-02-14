package hit.poms.bonus.entity;

public class BonusInfor {
	private String pub_id;
	private String card_type;
	private String card_id;
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String cardId) {
		card_id = cardId;
	}
	private String start_time;
	private String end_time;
	private String card_infor;
	
	@Override
	public String toString() {
		return "BonusInfor [card_id=" + card_id + ", card_infor=" + card_infor
				+ ", card_type=" + card_type + ", end_time=" + end_time
				+ ", pub_id=" + pub_id + ", start_time=" + start_time + "]";
	}
	public String getPub_id() {
		return pub_id;
	}
	public void setPub_id(String pubId) {
		pub_id = pubId;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String cardType) {
		card_type = cardType;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String startTime) {
		start_time = startTime;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	public String getCard_infor() {
		return card_infor;
	}
	public void setCard_infor(String cardInfor) {
		card_infor = cardInfor;
	}
	public BonusInfor() {
		super();
	}
	public BonusInfor(String pubId, String cardType, String cardId,
			String startTime, String endTime, String cardInfor) {
		super();
		pub_id = pubId;
		card_type = cardType;
		card_id = cardId;
		start_time = startTime;
		end_time = endTime;
		card_infor = cardInfor;
	}
	
	
}
