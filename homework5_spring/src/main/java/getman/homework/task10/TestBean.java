package getman.homework.task10;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContextTask10.xml"
        );
        Bankomat bankomat = context.getBean("bankomat", Bankomat.class);
        bankomat.cashWithdrawal();
        context.close();
    }
}
