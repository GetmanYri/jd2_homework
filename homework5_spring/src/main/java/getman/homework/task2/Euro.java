package getman.homework.task2;

import getman.homework.task9.BankomatAnnotation;


public class Euro implements CashWithdrawal {
    @Override
    public String withdrawal() {
        return"Euro";
    }
}
