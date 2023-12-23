package getman.homework.task2;

public class Bankomat {
    private CashWithdrawal currency;


    private Bankomat(){}

    public static Bankomat getBankomat(){
        return new Bankomat();
    }

    public void cashWithdrawal(){
        System.out.println("Currency: "+currency.withdrawal());

    }

    public void setCurrency(CashWithdrawal currency) {
        this.currency = currency;
    }
    public void init(){
        System.out.println("It's init method bankomat");
    }
    public void destroy(){
        System.out.println("It's destroy method bankomat");

    }
}
