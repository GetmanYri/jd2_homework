package getman.homework.task8;

import getman.homework.task8.Bankomat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContextTask8.xml"
        );
        Bankomat bankomat = context.getBean("bankomat", Bankomat.class);
        bankomat.cashWithdrawal();
        context.close();
    }
}
