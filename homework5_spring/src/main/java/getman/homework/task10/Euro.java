package getman.homework.task10;

import getman.homework.task2.CashWithdrawal;
import org.springframework.stereotype.Component;
@Component
public class Euro implements CashWithdrawal {
    @Override
    public String withdrawal() {
        return"Euro";
    }
}
