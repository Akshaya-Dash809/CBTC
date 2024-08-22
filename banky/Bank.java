package banky;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Bank {
private Map<String,Account> accounts;


public Bank() {
	accounts = new HashMap<>();
	//loadAccounts();
}
public void createAccount(String accountNumber,String accountHolder) {
if(accounts.containsKey(accountNumber)) {
	System.out.println("Account number already exists. ");
}
	else {
		Account newAccount=new Account(accountNumber,accountHolder);
		accounts.put(accountNumber, newAccount);
		System.out.println("Account created successfully. ");
	}
}

public Account getAccount(String accountNumber) {
	return accounts.get(accountNumber);
}
public void saveAccounts() {
	try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("accounts.dat"))){
		oos.writeObject(accounts);
		System.out.println("Accounts saved successfully. ");
	}
	catch(IOException e) {
		System.out.println("Error saving accounts: "+e.getMessage());
	}
}

@SuppressWarnings("unchecked")
public void loadAccounts() {
	try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("accounts.dat"))){
		accounts=(Map<String,Account>)ois.readObject();
		System.out.println("Account loaded successfully");
	}
	catch(IOException | ClassNotFoundException e) {
		System.out.println("No existing accounts found.");
	}
}

public static void main(String[] args) {
	Bank bank=new Bank();
	Scanner scanner=new Scanner(System.in);
	String accountNumber,accountHolder;
	double amount;
	int choice;
	do {
		System.out.println("\nBanky System");
		System.out.println("1. Create Account");
		System.out.println("2. Deposit Funds");
		System.out.println("3. Withdraw Funds");
		System.out.println("4. Transfer Funds");
		System.out.println("5. View Account");
		System.out.println("6. Exit");
		System.out.println("Enter Your Choice: ");
		choice=scanner.nextInt();
		scanner.nextLine();
		
		switch(choice) {
		case 1:
			System.out.println("Enter account number: ");
			accountNumber=scanner.nextLine();
			System.out.println("Enter account holder name: ");
			accountHolder=scanner.nextLine();
			bank.createAccount(accountNumber, accountHolder);
		    break;
		    
		case 2:
			System.out.println("Enter account number: ");
			accountNumber=scanner.nextLine();
			Account depositAccount=bank.getAccount(accountNumber);
			if(depositAccount != null) {
				System.out.println("Enter deposit amount: ");
				amount=scanner.nextDouble();
				depositAccount.deposit(amount);
			}
			else {
				System.out.println("Account NOt Found !!!");
			}
			break;
		case 3:
			System.out.println("Enter account number: ");
			accountNumber=scanner.nextLine();
			Account withdrawAccount=bank.getAccount(accountNumber);
			if(withdrawAccount != null) {
				System.out.println("Enter withdrawal amount: ");
				amount = scanner.nextDouble();
				withdrawAccount.withdraw(amount);
			}
			else {
				System.out.println("Account not found.");
			}
			break;
		case 4:
			System.out.println("Enter source account number: ");
			accountNumber=scanner.nextLine();
			Account sourceAccount = bank.getAccount(accountNumber);
			if(sourceAccount != null) {
				System.out.println("Enter target account number: ");
				String targetAccountNumber=scanner.nextLine();
				Account targetAccount=bank.getAccount(targetAccountNumber);
				if(targetAccount != null) {
					System.out.println("Enter transfer amount: ");
					amount = scanner.nextDouble();
					sourceAccount.transfer(targetAccount, amount);
				}
				else {
					System.out.println("Target acount notfound !!!");
				}
			}
				else {
					System.out.println("Source account not found. ");
				}
				break;
		case 5:
			System.out.println("Enter account number: ");
            accountNumber=scanner.nextLine();
            Account viewAccount = bank.getAccount(accountNumber);
            if(viewAccount != null) {
            	System.out.println(viewAccount);
            }
            else {
            	System.out.println("Account not found. ");
            }
            break;
		case 6:
			bank.saveAccounts();
			System.out.println("Exiting the system. ");
			break;
			default:
				System.out.println("Invalid choice. Please try again.");
			
		}
		
	}
	while(choice != 6);
	scanner.close();
}

}
