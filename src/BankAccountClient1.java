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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BankAccountClient1 {
	private BankAccountClient1() {};
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			AccountInterface stub;
			Registry registry = LocateRegistry.getRegistry(null);		
			AccountInterface stub1 = (AccountInterface)registry.lookup("BK_ACCT_Server1");
			//Registry registry2 = LocateRegistry.getRegistry(null);	
			AccountInterface stub2 = (AccountInterface)registry.lookup("BK_ACCT_Server2");
			
//			stub1.login("batman123", "1234567890", "111222333");
//			System.out.println(stub1.getName());
//			System.out.println(stub1.getBalance());
//			stub1.withdraw(10000);
//			stub1.deposit(15000); 
//			System.out.println(stub1.getBalance());	
			
//			stub2.login("spiderman123", "0987654321", "444555666");
//			System.out.println(stub2.getName());
//			System.out.println(stub2.getBalance());
//			stub2.withdraw(10000);
//			stub2.deposit(15000);
//			System.out.println(stub2.getBalance());
			Scanner input = new Scanner(System.in);
			boolean exit = false;
			int option1, option2, option3, option4;
			do {
				System.out.println("1. Log in\n2. Exit\n");
				try {
					option1 = input.nextInt();
					System.out.println();
				}catch(Exception e) {
					System.out.println("Please try again.....");
					continue;
				}
				switch(option1) {
				case 1:
					Boolean exit1 = false;
					System.out.print("Username: ");
					String username = input.next();
					System.out.print("Account No: ");
					String accountNo = input.next();
					System.out.print("Password: ");
					String password = input.next();
					if(stub1.login(username, password, accountNo).equals("success101")) {
						stub = stub1;
					}else if(stub2.login(username, password, accountNo).equals("success101")){
						stub = stub2;
					}else {
						System.out.println("Invalid Credential or Password, please try again...");
						continue;
					}
					do {
						System.out.println("1. Print out Balance\n2. Withdraw\n3. Deposit\n4. Transfer\n5. Log out");
						option2 = input.nextInt();
						switch(option2) {
						case 1:
							System.out.println("\n*******************");
							System.out.println("Username: " + stub.getName());
							System.out.println("Balance: $" + stub.getBalance());
							System.out.println("*******************\n");
							break;
						case 2:
							System.out.print("Please enter amount: ");
							float withdraw = input.nextFloat();
							System.out.println(stub.withdraw(withdraw));
							System.out.println();
							break;
						case 3:
							System.out.print("Please enter amount: ");
							float deposit = input.nextFloat();
							System.out.println(stub.deposit(deposit));
							System.out.println();
							break;
						case 4:
							System.out.print("Please enter amount: ");
							float transfer = input.nextFloat();
							System.out.println(stub.transfer(transfer, stub == stub1? stub2:stub1));
							System.out.println();
							break;
						case 5:
							exit1 = true;
							break; 
						default:
							System.out.println("Invalid Entry, please try again...\n");
						}
					}while(!exit1);
					break;
				case 2:
					System.out.println("Bye!");
					exit = true;
					break;
				default:
					System.out.println("Enter either 1 or 2");
				}
			}while(!exit);
			
		}catch(Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
