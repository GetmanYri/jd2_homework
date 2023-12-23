package getman.homework.data.pojo.forTask8;

import getman.homework.data.dao.DaoForTask8.HierarchyDaoImpl;
import getman.homework.data.pojo.forTask8.perSubclass.Engineer;
import getman.homework.data.pojo.forTask8.singleTable.BankAccountVisaSingleTable;
import getman.homework.data.pojo.forTask8.singleTable.ClientSingleTable;
import getman.homework.data.util.HibernateSessionFactory;

public class Main {
    public static void main(String[] args) {
        ClientSingleTable person=new ClientSingleTable(null,23,"HOP","Kolp");
        BankAccountVisaSingleTable bank=new BankAccountVisaSingleTable(null,45,"bank","bank",12312);
        //BankAccountMasterSingleTable address=new BankAccountMasterSingleTable(null,32,"address","address","ggg","fff","fswf");

        HierarchyDaoImpl hierarchyDao=new HierarchyDaoImpl(HibernateSessionFactory.getSessionFactory());
//String id=hierarchyDao.saveSingleTable(bank);
  //      bank=hierarchyDao.getVisaClientById("297e308a8c21546d018c215471540000");
       // System.out.println(hierarchyDao.getPersonById(id).getAccountNumberVisa());
//        System.out.println(bank.getAccountVisa());

        Engineer engineer=new Engineer(null,"lop","LLLo",125,"dddd");
hierarchyDao.savePerSubclass(engineer);
    }
}
