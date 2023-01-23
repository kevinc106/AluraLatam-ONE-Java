package main;

public class TestOfficer {
	public static void main(String[] args) {
		Officer diego = new Officer();
		diego.setName("Diego");
		diego.setDocument("123");
		diego.setSalary(1200);
		System.out.println(diego.getSalary());
	}
}
