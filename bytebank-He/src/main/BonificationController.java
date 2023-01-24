package main;

public class BonificationController {
	private double sum;
	
	public void register(Officer officer) {
		this.sum = officer.getBonification();  
	}
	
	public double getSum() {
		return this.sum;
	}
}
