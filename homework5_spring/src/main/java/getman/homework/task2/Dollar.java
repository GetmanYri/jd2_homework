package getman.homework.task2;

import getman.homework.task9.BankomatAnnotation;

@BankomatAnnotation
public class Dollar implements CashWithdrawal{
    @Override
    public String withdrawal() {
        return "Dollar";
    }
}
