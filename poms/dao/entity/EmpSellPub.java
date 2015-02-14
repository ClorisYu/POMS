package hit.poms.dao.entity;

import java.util.Date;


/**
 * 作者：dsz
 * 时间：2014-07-15 PM
 * @author new
 *功能：员工刊物对照表的实体类
 */

public class EmpSellPub {
	private Long emp_id;//员工ID
	private Long pub_id;//刊物ID
	private Long emp_order;//征订量
	private Long emp_delivery;//投递量
	private String fin_time;//时间（相应工作完成时间）
	private Long customer_id;//客户ID，征订或投递的对象
	private Double get_money;//此次所收到的金额
	
	@Override
	public String toString() {
		return "EmpSellPub [customer_id=" + customer_id + ", emp_delivery="
				+ emp_delivery + ", emp_id=" + emp_id + ", emp_order="
				+ emp_order + ", fin_time=" + fin_time + ", get_money="
				+ get_money + ", pub_id=" + pub_id + ", getCustomer_id()="
				+ getCustomer_id() + ", getEmp_delivery()=" + getEmp_delivery()
				+ ", getEmp_id()=" + getEmp_id() + ", getEmp_order()="
				+ getEmp_order() + ", getFin_time()=" + getFin_time()
				+ ", getGet_money()=" + getGet_money() + ", getPub_id()="
				+ getPub_id() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	public EmpSellPub(Long empId, Long pubId, Long empOrder, Long empDelivery,
			String finTime, Long customerId, Double getMoney) {
		super();
		emp_id = empId;
		pub_id = pubId;
		emp_order = empOrder;
		emp_delivery = empDelivery;
		fin_time = finTime;
		customer_id = customerId;
		get_money = getMoney;
	}
	
	
	public EmpSellPub() {
		super();
	}
	
	
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long empId) {
		emp_id = empId;
	}
	public Long getPub_id() {
		return pub_id;
	}
	public void setPub_id(Long pubId) {
		pub_id = pubId;
	}
	public Long getEmp_order() {
		return emp_order;
	}
	public void setEmp_order(Long empOrder) {
		emp_order = empOrder;
	}
	public Long getEmp_delivery() {
		return emp_delivery;
	}
	public void setEmp_delivery(Long empDelivery) {
		emp_delivery = empDelivery;
	}
	public String getFin_time() {
		return fin_time;
	}
	public void setFin_time(String finTime) {
		fin_time = finTime;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customerId) {
		customer_id = customerId;
	}
	public Double getGet_money() {
		return get_money;
	}
	public void setGet_money(Double getMoney) {
		get_money = getMoney;
	}
	

}
