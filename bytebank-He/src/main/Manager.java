package main;

public class Manager extends Officer { 
	 
	private String key;
	 
	
	public String getKey() {
		return key;
	} 


	public void setKey(String key) {
		this.key = key;
	} 
	
	public boolean loginSession(String key) {
		return this.key == key;
	}
	@Override
	public double getBonification() {
		return super.getSalary();
	}
	
	
}
