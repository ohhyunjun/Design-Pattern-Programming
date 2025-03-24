import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.ArrayList;

public class Account {
    protected int balance = 0;
    protected final String account_num;
    ArrayList<String> history = new ArrayList<String>();
    
    public Account(String account_num, int balance) {
        this.account_num = account_num;
        this.balance = balance;
        System.out.printf("계좌를 개설하였습니다. 계좌:%s, %d원\n", this.account_num, balance);
    }
    
    public void deposit(String account_num, int add_money) {
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
            if (other_account.account_num != null) {
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
		super(account_num, balance);
		this.regular_money = regular_money;
		System.out.printf("정기예금계좌를 개설하였습니다. 계좌:%s,잔액: %d원, 정기입금액: %d\n", super.account_num, super.balance, this.regular_money);
	}
	@Override
	public void deposit(int add_money) {
		if(regular_money==add_money) {
			super.balance+=regular_money;
			System.out.printf("계좌: %s, 잔액: %d\n", this.account_num, balance);
	        history.add(this.account_num + "| 입금:" + add_money + ", 총액:" + balance);
		}else {
			System.out.println("정해진 입금액이 아닙니다.");
		}
    }
	
	
	
}
/*
class AssetManagementAccount extends Account{
	
}

class SecuritiesDepositAccount extends Account{
	
}
*/