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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;

@SuppressWarnings("serial")
public class BankAccountServer extends Account {

	public BankAccountServer(String username, String pwd, String accountNo, float balance) throws RemoteException {
		super(username, pwd, accountNo, balance);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			Account bankAccount1 = new Account("batman123", "1234567890", "111222333", 200000);
			
			AccountInterface stub1 = (AccountInterface)UnicastRemoteObject.exportObject(bankAccount1, 0);
			Registry registry1 = LocateRegistry.getRegistry();
			
			registry1.bind("BK_ACCT_Server1", stub1);  
			System.err.println("BK_ACCT_Server1 is Ready");
		}catch(ExportException ex) {
			System.err.println("Server exception: " + ex.toString());
			ex.printStackTrace();
		}catch(Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		
		try {
			Account bankAccount2 = new Account("spiderman123", "0987654321", "444555666", 150000);
			
			AccountInterface stub2 = (AccountInterface)UnicastRemoteObject.exportObject(bankAccount2, 0);
			Registry registry2 = LocateRegistry.getRegistry();
			
			registry2.bind("BK_ACCT_Server2", stub2);
			System.err.println("BK_ACCT_Server2 is Ready");
		}catch(ExportException ex) {
			System.err.println("Server exception: " + ex.toString());
			ex.printStackTrace();
		}catch(Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
		
		
	}

}
