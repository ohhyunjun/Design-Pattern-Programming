
public class Main {

	public static void main(String[] args) {
		
		Account guest1 = new Account("123-456", 0); //일반계좌로 객체 생성함.
		Account guest2 = new Account("123-777", 500);
		guest1.deposit(5000); // 5000원 입금함
		guest1.withdraw(2000); // 2000원 출력함
		guest1.transfer(guest2, 1000); // 1000원 이체함
		System.out.println("");
		guest1.printHistory(); // guest1 거래내역 출력
		guest2.printHistory();
		System.out.println("");
		
		FixedDepositAccount guest3 = new FixedDepositAccount("712-449", 5000, 5000);
		//정기예금계좌 객체 생성 (계좌번호, 초기금액, 정기입금금액)
		guest3.deposit(5000);
		guest3.deposit(3000);
		System.out.println("");
		guest3.printHistory();
		System.out.println("");
		
		AssetManagementAccount guest4 = new AssetManagementAccount("123-123", 0, 10000);
		//자산관리계좌 객체 생성 (계좌번호, 초기금액, 상한선금액)
		guest4.deposit(8000); 
		guest4.deposit(12000);
		System.out.println("");
		guest4.printHistory();
		System.out.println("");
		
		SecuritiesDepositAccount  guest5 = new SecuritiesDepositAccount("724-659", 5700, "144-725");
		//증권예탁계좌 객체 생성 (계좌번호, 초기금액, 증권계좌번호). 증권게좌는 초기금액 0으로 하였음.
		guest5.receive_securities("144-725", 2000);
		guest5.transfer_securities("144-725", guest4, 1000);
		System.out.println("");
		guest5.printHistory();
		guest4.printHistory();
		
	}

}
