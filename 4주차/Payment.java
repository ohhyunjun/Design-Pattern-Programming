
public abstract class Payment {
	public static void processPayment() {
		
	}
	public abstract void pay();
	
}

class Cash extends Payment{
	String account;
	public void withdraw() {
		System.out.println("출금");
	}
	public void pay() {
		System.out.println("현금 지불");
	}
}

class CreditCard extends Payment{
	String cardNum;
	public void transfer() {
		System.out.println("이체");
	}
	
	public void pay() {
		System.out.println("신용카드 지불");
	}
}

class Point extends Payment{
	int point;
	public void check() {
		System.out.println("체크");
	}
	public void pay() {
		System.out.println("포인트 지불");
	}
}
