package main;

public class TestOfficer {
	public static void main(String[] args) {
		Officer diego = new Officer();
		Manager juan = new Manager();
		diego.setName("Diego");
		diego.setDocument("123");
		diego.setSalary(1200);
		juan.setName("Juan");
		juan.setDocument("456");
		juan.setSalary(4000);
		System.out.println(diego.getSalary());
		System.out.println(diego.getBonification());
		System.out.println(juan.getBonification());
		
	}
}
