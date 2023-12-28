package getman.homework.task10;

import getman.homework.task2.CashWithdrawal;
import getman.homework.task9.BankomatAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bankomat {
    @Autowired
    private CashWithdrawal currency;
    //@Autowired
   // private CashWithdrawal currency2;


    private Bankomat() {
    }

    public static Bankomat getBankomat() {
        return new Bankomat();
    }

    public void cashWithdrawal() {
        System.out.println("Currency: " + currency.withdrawal());

    }

    public void setCurrency(CashWithdrawal currency) {
        this.currency = currency;
    }

    public void init() {
        System.out.println("It's init method bankomat");
    }

    public void destroy() {
        System.out.println("It's destroy method bankomat");

    }
}
