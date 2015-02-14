package hit.poms.util;
public class Page {
	private int totalpage;
	private int currentpage;
	private int totalrecord;
	private int currentrecord;
	private int pagesize=6;
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalrecord,int pagesize) {
		if(totalrecord%pagesize==0){
			this.totalpage=totalrecord/pagesize;
		}
		else{
			this.totalpage=totalrecord/pagesize+1;
		}
	}
	
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentrecord,int pagesize) {
	if(currentrecord%pagesize==0){
		this.currentpage=currentrecord/pagesize;
	}
	else{
		this.currentpage=currentrecord/pagesize+1;
	}
		//this.currentpage = currentpage;
	}
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	public int getCurrentrecord() {
		return currentrecord;
	}
	public void setCurrentrecord(int currentrecord) {
		this.currentrecord = currentrecord;
	}
	

}
