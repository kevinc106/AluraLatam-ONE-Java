package main;

public class TestBonificationController {

	public static void main(String[] args) {
		Officer diego = new Officer();
		diego.setName("Diego");
		diego.setDocument("123");
		diego.setSalary(1200);
		
		Manager juan = new Manager();
		juan.setName("Juan");
		juan.setDocument("456");
		juan.setSalary(4000);
		
		Designer alba = new Designer();
		alba.setName("Alba");
		alba.setDocument("555");
		alba.setSalary(1000);
		
		BonificationController bonificationController = new BonificationController();
		bonificationController.register(diego);
		System.out.println(bonificationController.getSum());
		bonificationController.register(juan);
		System.out.println(bonificationController.getSum());
		bonificationController.register(alba);
		System.out.println(bonificationController.getSum());
		
	}

}
