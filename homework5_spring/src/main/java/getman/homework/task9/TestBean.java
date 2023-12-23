package getman.homework.task9;

import getman.homework.task9.Bankomat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContextTask9.xml"
        );
        Bankomat bankomat = context.getBean("bankomat", Bankomat.class);
        bankomat.cashWithdrawal();
        context.close();
    }
}
