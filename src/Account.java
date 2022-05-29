/********************************************** 
Workshop # 10
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-04-13
**********************************************/ 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Account implements AccountInterface {
	String username, pwd,accountNo;
	float balance = 0;
	private boolean signin = false;
	public Account(String username, String pwd, String accountNo, float balance) throws RemoteException {
		//super();
		this.username = username;
		this.pwd = pwd;
		this.accountNo = accountNo;
		this.balance = balance; 
	}
	@Override
	public String login(String username, String pwd, String accountNo) throws RemoteException {
		// TODO Auto-generated method stub
		if(this.username.equals(username) && this.pwd.equals(pwd) && this.accountNo.equals(accountNo)) {
			signin = true;
			return "success101";
		}
		return "fail101";
	}
	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		if(signin) {
			return this.username;
		}
		return "Error";
	}
	@Override
	public float getBalance() throws RemoteException {
		// TODO Auto-generated method stub
		if(signin) {
			return this.balance;
		}
		return -1;
	}
	@Override
	public String withdraw(float amt) throws RemoteException {
		// TODO Auto-generated method stub
		String msg;
		if(signin) {
			if(balance >= amt) {
				balance = balance - amt;
				return msg = "Withdraw Completed.";
			}else {
				return msg = ("Insufficient fund to process the transaction.");
			}
		}else {
			return msg = ("Please Sign in first");
		}
	}
	@Override
	public String deposit(float amt) throws RemoteException {
		// TODO Auto-generated method stub
		String msg;
		//if(signin) {
			if(amt <= 50000) {
				balance += amt;	
				return msg = "Deposit Completed.";
			}else {
				return msg = ("Daily Deposit Limit is $50,000");
			}
		//}else {
			//return msg = ("Please Sign in first");
		//}
		
	}
	@Override
	public String transfer(float amt, AccountInterface src) throws RemoteException {
		// TODO Auto-generated method stub	
			String msg;
			if(signin){
				if(balance >= amt) {
					synchronized(this) {
						if(src.deposit(amt).equals("Deposit Completed.")) { 
							balance -= amt; 
							return msg = "Transfer Completed";
						}else {
							return msg = "Over the transfer limit on the other account.";
						}					
					}
				}else {
					return msg = ("Insufficient fund to process the transaction.");
				}
			}else {
				return msg = ("Please Sign in first");
			}		
	}
	
	public void logout() {
		if(signin) {
			signin = false;
			System.out.println("Client Log Out Successfully.");
		}else {
			System.out.println("Please Sign in first");
		}
	}
	
}
