package main;

public class Account {
	private double balance;
	private int agency;
	private int number;
	private Client client;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAgency() {
		return agency;
	}
	public void setAgency(int agency) {
		this.agency = agency;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void deposit(double value) {
		this.balance+=value;
	}  
	
	public boolean withdraw(double value) {
		if(value<=balance) {
			this.balance-=value;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean transfer(double value,Account destinationAccount) {
		if(this.withdraw(value)) {
			destinationAccount.deposit(value);
			return true;
		}
		return false;
	}
}
