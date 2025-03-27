import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.ArrayList;

public class Account {
    protected int balance = 0;
    protected final String account_num;//계좌번호 설정시 다신 변경 못하도록 final로 지정.
    ArrayList<String> history = new ArrayList<String>();// 거래내역은 지속적으로 추가되기에 ArryaList로 생성함.
    
    public Account(String account_num, int balance) {
        this.account_num = account_num;
        this.balance = balance;
        System.out.printf("계좌를 개설하였습니다. \n");
    }
    
    public void deposit(String account_num, int add_money) {//계좌 이체로 인한 이체받은 계좌측 거래내역을 저장하기 위해 만듬.
        balance += add_money;
        history.add(this.account_num + "| 입금:" + add_money + ", 총액:" + balance);
    }
    
    public void deposit(int add_money) {
        balance += add_money;
        System.out.printf("계좌: %s, 잔액: %d\n", this.account_num, balance);
        history.add(this.account_num + "| 입금:" + add_money + ", 총액:" + balance);
    }
    
    public void withdraw(int sub_money) {
        if (balance >= sub_money) {
            balance -= sub_money;
            System.out.printf("계좌: %s, 잔액: %d\n", this.account_num, balance);
            history.add(this.account_num + "| 출금:" + sub_money + ", 총액:" + balance);
        } else {
            System.out.println("출금액보다 잔액이 작아 출금이 불가능 합니다.");
        }
    }
    
    public void transfer(Account other_account, int tran_money) {
        if (balance >= tran_money) {
            balance -= tran_money;
            if (other_account.account_num != null) {//null인 계좌에 이체하는것을 막기 위해 만듬.
            	other_account.deposit(this.account_num, tran_money);
                System.out.printf("계좌: %s, 잔액: %d\n", this.account_num, balance);
                System.out.println("송금이 성공적으로 이루어졌습니다.");
                history.add(this.account_num + "| 송금:" + tran_money + ", 총액:" + balance);
                other_account.history.add(other_account.account_num + "| "+this.account_num+"으로부터 입금:" + tran_money + ", 총액:" + other_account.balance);
			}else {
				System.out.println("없는 송금인입니다.");
			}
        } else {
            System.out.println("송금액보다 잔액이 작아 송금이 불가능 합니다.");
        }
    }
    public void printHistory() {
    	if (history.isEmpty()) {
            System.out.println("거래 내역이 없습니다.");
        } else {
            System.out.println("거래내역 계좌: " + this.account_num);
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}

class FixedDepositAccount extends Account{
	private int regular_money;
	public FixedDepositAccount(String account_num, int balance, int regular_money) {
		super(account_num, balance);//부모 클래스에 선언방식을 정의 하였기에 꼭 자식클래스는 보모클래스를 먼저 호출해야함. super()를 통해
		this.regular_money = regular_money;
		System.out.printf("정기예금계좌:%s,잔액: %d원, 정기입금액: %d\n", super.account_num, super.balance, this.regular_money);
	}
	@Override
	public void deposit(int add_money) {//일반계좌의 입금을 오버라이드함.
		if(regular_money==add_money) {
			super.balance+=regular_money; 
			System.out.printf("정기예금계좌: %s, 잔액: %d\n", this.account_num, balance);
	        history.add(this.account_num + "| 입금:" + add_money + ", 총액:" + balance);
		}else {
			System.out.println("정해진 입금액이 아닙니다.");
		}
    }	
}

class AssetManagementAccount extends Account{
	private int max_money;
	public AssetManagementAccount(String account_num, int balance, int max_money) {
		super(account_num, balance);
		this.max_money = max_money;
		System.out.printf("자산관리계좌:%s,잔액: %d원, 입금한도: %d\n", super.account_num, super.balance, this.max_money);
	}
	@Override
	public void deposit(int add_money) {
		if (add_money<= max_money && add_money>0) {
			super.balance+=add_money; 
			System.out.printf("자산관리계좌: %s, 잔액: %d\n", this.account_num, balance);
	        history.add(this.account_num + "| 입금:" + add_money + ", 총액:" + balance);
		}else {
			System.out.println("입금 한도금액보다 큽니다.");
		}
	}	
}

class SecuritiesDepositAccount extends Account{
	protected final String stack_account;
	protected int stack_balance = 0;
	public SecuritiesDepositAccount(String account_num, int balance, String stack_account) {
		super(account_num, balance);
		this.stack_account = stack_account;
		System.out.printf("일반계좌:%s,잔액: %d원/ 증권계좌:%s,잔액: %d원\n", super.account_num, super.balance, this.stack_account, this.stack_balance);
	}
	public void receive_securities(String account_num, int add_money) {
		if (account_num.equals(super.account_num)) { // 계좌번호가 일반계좌일 경우.
			super.deposit(add_money);
		}else if (account_num.equals(this.stack_account)) {
			this.stack_balance+=add_money;
			System.out.printf("증권계좌: %s, 잔액: %d\n", this.stack_account, this.stack_balance);
	        history.add(this.stack_account + "| 입금:" + add_money + ", 총액:" + this.stack_balance);
		}
    }
	
	public void transfer_securities(String account_num, Account other_account, int tran_money) {
        if (account_num.equals(super.account_num)) { // 이체에서 일반계좌에서 이체인지 증권계좌에서 이체인지 구분하기 위해.
        	super.transfer(other_account, tran_money);
		}else if (account_num.equals(stack_account)) {
			if (stack_balance >= tran_money) {
	        	stack_balance -= tran_money;
	            if (other_account.account_num != null) {
	            	other_account.deposit(this.account_num, tran_money);
	                System.out.printf("증권계좌: %s, 잔액: %d\n", this.stack_account, stack_balance);
	                System.out.println("송금이 성공적으로 이루어졌습니다.");
	                history.add(this.stack_account + "| 송금:" + tran_money + ", 총액:" + stack_balance);
	                other_account.history.add(other_account.account_num + "| "+this.stack_account+"으로부터 입금:" + tran_money + ", 총액:" + other_account.balance);
				}else {
					System.out.println("없는 송금인입니다.");
				}
	        } else {
	            System.out.println("송금액보다 잔액이 작아 송금이 불가능 합니다.");
	        }
		}
    }
}
