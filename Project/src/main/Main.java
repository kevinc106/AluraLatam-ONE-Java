package main;

public class Main {
	public static void main(String[] args) {
		Account firstAccount = new Account();
        firstAccount.setBalance(200);
        firstAccount.deposit(100);
        System.out.println(firstAccount.getBalance());
        boolean result = firstAccount.withdraw(50);
        System.out.println(firstAccount.getBalance());
        
        Account secondAccount = new Account();
        secondAccount.setBalance(100);
        firstAccount.transfer(100, secondAccount);
        System.out.println(firstAccount.getBalance());
        System.out.println(secondAccount.getBalance());
	}
}
