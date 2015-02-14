package hit.poms.bean;

import java.util.Date;

public class emp_sell {
private long emp_id;
private long pub_id;
private long emp_order;
private long emp_delivery;
private String final_time;
private long customer_id;
private double get_money;
public long getEmp_id() {
	return emp_id;
}
public void setEmp_id(long empId) {
	emp_id = empId;
}
public long getPub_id() {
	return pub_id;
}
public void setPub_id(long pubId) {
	pub_id = pubId;
}
public long getEmp_order() {
	return emp_order;
}
public void setEmp_order(long empOrder) {
	emp_order = empOrder;
}
public long getEmp_delivery() {
	return emp_delivery;
}
public void setEmp_delivery(long empDelivery) {
	emp_delivery = empDelivery;
}
public String getFinal_time() {
	return final_time;
}
public void setFinal_time(String finalTime) {
	final_time = finalTime;
}
public long getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(long customerId) {
	customer_id = customerId;
}
public double getGet_money() {
	return get_money;
}
public void setGet_money(double getMoney) {
	get_money = getMoney;
}

}
