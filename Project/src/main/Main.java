package main;

public class Main {
	public static void main(String[] args) {
		Account firstAccount = new Account(111);
        firstAccount.setBalance(200);
        firstAccount.deposit(100); 
        boolean result = firstAccount.withdraw(50); 
        
        Account secondAccount = new Account(222);
        secondAccount.setBalance(100);
        firstAccount.transfer(100, secondAccount); 
        
        firstAccount.setClient(new Client());
        firstAccount.getClient().setName("oscar");
        System.out.println(firstAccount.getClient().getName());
        System.out.println("Total "+Account.getTotal());
        
        
	}
}
