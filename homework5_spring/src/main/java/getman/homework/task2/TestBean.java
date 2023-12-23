package getman.homework.task2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(
                "applicationContextTask2.xml"
        );
Bankomat bankomat=context.getBean("bankomat",Bankomat.class);
bankomat.cashWithdrawal();
context.close();
    }
}
