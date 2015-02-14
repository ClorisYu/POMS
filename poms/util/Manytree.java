package hit.poms.util;

public class Manytree {
public Manytreenode root;
public Manytree(String key){
	root = new Manytreenode(key);	
	root.getBean().setNodename("root");
}
public Manytreenode getRoot() {
	return root;
}
public void setRoot(Manytreenode root) {
	this.root = root;
}

}
