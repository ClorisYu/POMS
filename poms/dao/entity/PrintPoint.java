package hit.poms.dao.entity;

/**
 * 作者：dsz
 * 时间：2014-07-15 PM
 * @author new
 *功能：印刷站信息表的实体类
 */

public class PrintPoint {
	private Long print_id;//印刷厂编号
	private String print_name;//印刷厂名字
	private String print_address;//印刷厂地址
	
	@Override
	public String toString() {
		return "PrintPoint [print_address=" + print_address + ", print_id="
				+ print_id + ", print_name=" + print_name
				+ ", getPrint_address()=" + getPrint_address()
				+ ", getPrint_id()=" + getPrint_id() + ", getPrint_name()="
				+ getPrint_name() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	public PrintPoint(Long printId, String printName, String printAddress) {
		super();
		print_id = printId;
		print_name = printName;
		print_address = printAddress;
	}
	
	
	public PrintPoint() {
		super();
	}
	
	public Long getPrint_id() {
		return print_id;
	}
	public void setPrint_id(Long printId) {
		print_id = printId;
	}
	public String getPrint_name() {
		return print_name;
	}
	public void setPrint_name(String printName) {
		print_name = printName;
	}
	public String getPrint_address() {
		return print_address;
	}
	public void setPrint_address(String printAddress) {
		print_address = printAddress;
	}

}
