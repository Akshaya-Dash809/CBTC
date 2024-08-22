package banky;

import java.io.Serializable;

class Account implements Serializable {
	private static final long serialVersionUID =1L;
	private String accountNumber;
	private String accountHolder;
	private double balance;
	
	public Account(String accountNumber,String accountHolder) {
		this.accountNumber=accountNumber;
		this.accountHolder=accountHolder;
		this.balance=0.0;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getAccountHolder() {
		return accountHolder;
	}
	
	public double getBalance() {
		return balance;
	}
	public void deposit(double amount) {
		if(amount>0) {
			balance += amount;
			System.out.println("Deposit successful.");
		}
		else {
			System.out.println("Invalid deposit amount.");
		}
	}
	
	public void withdraw(double amount) {
		if(amount > 0 && amount<=balance) {
			balance -= amount;
			System.out.println("Withdrawal Successful");
		}
		else {
			System.out.println("Invalid withdrawal amount or insufficiant funds.");
		}
	}
	public void transfer(Account targetAccount,double amount) {
		if(amount>0 && amount <= balance) {
			balance -= amount;
			targetAccount.deposit(amount);
			System.out.println("Transfer successful. ");
		}
		else {
			System.out.println("Invalid transfer amount or insufficient funds. ");
		}
	}

	@Override
	public String toString() {
		return "Account Number: " + accountNumber + ", Account Holder: "+accountHolder+", Balance: $" +balance;
	}
}
