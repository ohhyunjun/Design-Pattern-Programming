
public class Main {

	public static void main(String[] args) {
		Account guest1 = new Account("123-777", 500);
		Account guest2 = new Account("123-456", 10000);
		guest2.deposit(15000);
		guest2.withdraw(10000);
		System.out.println("");
		guest2.printHistory();
		System.out.println("");
		guest2.transfer(guest1, 5000);
		System.out.println("");
		guest1.printHistory();
		System.out.println("");
		
		FixedDepositAccount guest3 = new FixedDepositAccount("111-765", 50000, 5000);
		guest3.deposit(3000);
		
	}

}
