package getman.homework.data.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateTestSessionFactory {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                //File file=new File("C:\\YRI\\jd2_homework\\jd2_homework\\module3_topic2_hibernate\\src\\main\\resources\\hibernate.cfg.xml");

                /*Configuration conf = new Configuration().configure(file);
                sessionFactory=conf.buildSessionFactory();*/
                /*StandardServiceRegistryBuilder builder=
                        new StandardServiceRegistryBuilder().applySettings(conf.getProperties());*/
                //conf.setPhysicalNamingStrategy(new MyPhysicalNamingStrategy());
                // sessionFactory=conf.buildSessionFactory(builder.build());
               sessionFactory = new Configuration()
                        .configure("test.hibernate.cfg.xml")
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
