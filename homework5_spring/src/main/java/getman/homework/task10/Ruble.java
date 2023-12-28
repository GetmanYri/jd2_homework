package getman.homework.task10;

import getman.homework.task2.CashWithdrawal;
import getman.homework.task9.BankomatAnnotation;
import org.springframework.stereotype.Component;

@Component
@BankomatAnnotation
public class Ruble implements CashWithdrawal {
    @Override
    public String withdrawal() {
        return "Ruble";
    }
}
