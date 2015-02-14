package hit.poms.util;

import java.util.ArrayList;
import java.util.List;

public class Manytreenode {
public Treenodebean bean;
public List<Manytreenode> list;
public Manytreenode(String string){
	bean=new Treenodebean();
	bean.setKey(string);
	list=new ArrayList<Manytreenode>();
	
}
public Treenodebean getBean() {
	return bean;
}
public void setBean(Treenodebean bean) {
	this.bean = bean;
}
public List<Manytreenode> getList() {
	return list;
}
public void setList(List<Manytreenode> list) {
	this.list = list;
}

}
