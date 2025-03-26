
public class Main {

	public static void main(String[] args) {
		Cash a1 = new Cash();
		a1.withdraw();
		a1.pay();
		System.out.println("");
		
		CreditCard a2 = new CreditCard();
		a2.transfer();
		a2.pay();
		System.out.println("");
		
		Point a3 = new Point();
		a3.check();
		a3.pay();
	}

}
