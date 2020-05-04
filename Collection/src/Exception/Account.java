package Exception;

public class Account {
    int balance = 0;
    public int getBalance(){
        return balance;
    }

    public void deposit(int money){
        balance+=money;
    }
    public void withdraw(int money) throws OverdraftException{
        if(money>balance){
            throw new OverdraftException("sorry,余额不足");
        }
        else {
            balance-=money;
        }
    }

    public class OverdraftException extends Exception{
        public OverdraftException(){

        }
        public OverdraftException(String msg){
            super(msg);
        }
    }

    public static class CheckingAccount extends Account{
        int overdraftProtection;
        public CheckingAccount(){
            overdraftProtection = 500;
        }

        @Override
        public void withdraw(int money) throws OverdraftException {
            if(money<=balance) balance-=money;
            else if(money>balance+overdraftProtection) throw new OverdraftException("余额和可透支余额都不足");
            else {
                overdraftProtection = overdraftProtection-(money-balance);
                balance = 0;
            }
        }

    }
    public static void main(String[] args) {
        CheckingAccount checkAc = new CheckingAccount();
        System.out.println("余额为："+checkAc.balance);
        checkAc.deposit(100);
        System.out.println("余额为："+checkAc.balance);
        try {
            checkAc.withdraw(1000);
        }catch (OverdraftException e){
            e.printStackTrace();
        }finally {
            System.out.println("余额为:"+checkAc.balance);
            System.out.println("剩余额度:"+checkAc.overdraftProtection);
        }
    }
}
