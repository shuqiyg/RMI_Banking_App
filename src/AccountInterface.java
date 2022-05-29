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

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountInterface extends Remote {
	public String login(String username, String pwd, String accountNo) throws RemoteException;
	public String getName() throws RemoteException; // returns account holders name
	public float getBalance() throws RemoteException; // return account balance
	// withdraw amount from balance. Consider validations for balance
	public String withdraw(float amt) throws RemoteException;
	// Deposit amount to account. Deposit should not be more than a certain threshold that you can decide
	public String deposit(float amt) throws RemoteException;
	// transfer amount to provide account. Proper validation should be followed.
	 public String transfer(float amt, AccountInterface src) throws RemoteException;
} 

