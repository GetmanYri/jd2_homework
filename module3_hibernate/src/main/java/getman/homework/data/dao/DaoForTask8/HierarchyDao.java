package getman.homework.data.dao.DaoForTask8;

import getman.homework.data.pojo.forTask8.perSubclass.Employee;
import getman.homework.data.pojo.forTask8.perSubclass.Engineer;
import getman.homework.data.pojo.forTask8.perSubclass.Manager;
import getman.homework.data.pojo.forTask8.singleTable.BankAccountMasterSingleTable;
import getman.homework.data.pojo.forTask8.singleTable.BankAccountVisaSingleTable;
import getman.homework.data.pojo.forTask8.singleTable.ClientSingleTable;

public interface HierarchyDao {
    String saveSingleTable(Object object);

    ClientSingleTable getClientById(String id);

    BankAccountVisaSingleTable getVisaClientById(String id);

    BankAccountMasterSingleTable getMasterClientById(String id);

    String savePerSubclass(Object object);

    Employee getEmployeeById(String id);

    Engineer getEngineerById(String id);

    Manager getManagerById(String id);


}
