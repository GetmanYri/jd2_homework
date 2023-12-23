package getman.homework.task8;

import getman.homework.task2.CashWithdrawal;
import org.springframework.beans.factory.annotation.Autowired;

public class Bankomat {
    @Autowired
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
