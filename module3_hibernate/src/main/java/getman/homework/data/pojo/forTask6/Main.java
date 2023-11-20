package getman.homework.data.pojo.forTask6;

import getman.homework.data.dao.DaoForTask6.GeneratedIdDaoImpl;
import getman.homework.data.util.HibernateSessionFactory;

public class Main {
    public static void main(String[] args) {
        GeneratedIdDaoImpl dao=new GeneratedIdDaoImpl(HibernateSessionFactory.getSessionFactory());
        IdNative idNative =new IdNative("fggfg");
        IdIdentity idIdentity=new IdIdentity("fsd");
        IdSequence idSequence=new IdSequence("fdsaf");
        IdHiLo idHiLo=new IdHiLo("HiLo");
        IdHiLo idHiLo2=new IdHiLo("HiLo2");
        IdUuid idUuid=new IdUuid("UUID");
        IdGUID idGUID =new IdGUID("UUIDHex");
        //System.out.println(dao.save(idIdentity));
        //System.out.println(dao.getId(idIdentity));

       // System.out.println(dao.saveAndGetUUIDHex(idGUID));

       // System.out.println(dao.save(idSequence));
        //System.out.println(dao.getId(idSequence));
/*for(int i=0; i<6;i++) {
    System.out.println(dao.saveAndGetHiLo(idHiLo));
    //System.out.println(dao.getId(idHiLo));
}*/
        //System.out.println(dao.saveAndGetHiLo(idHiLo));
    }
}
