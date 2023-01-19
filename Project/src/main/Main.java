package main;

public class Main {
	public static void main(String[] args) {
		Account firstAccount = new Account();
        firstAccount.setBalance(200);
        firstAccount.deposit(100); 
        boolean result = firstAccount.withdraw(50); 
        
        Account secondAccount = new Account();
        secondAccount.setBalance(100);
        firstAccount.transfer(100, secondAccount); 
        
        firstAccount.setClient(new Client());
        firstAccount.getClient().name="oscar";
        System.out.println(firstAccount.getClient().name);
	}
}
