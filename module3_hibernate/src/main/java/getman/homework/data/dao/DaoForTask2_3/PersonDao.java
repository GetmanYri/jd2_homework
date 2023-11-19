package getman.homework.data.dao.DaoForTask2_3;

import getman.homework.data.pojo.forTask2_3.BankAccount;
import getman.homework.data.pojo.forTask2_3.Person;

public interface PersonDao {
    //String savePerson(Person person);
    String save(Person person);
    String save(BankAccount bankAccount);
    boolean deletePerson(String id);
    Person getPersonById(String id);
    Person getPersonWithBankAccountById(String id);
    Person loadPersonById(String id);

    void savePersonWithId(Person person);
    void saveBankAccountWithId(BankAccount bankAccount);
    Person refreshPerson(Person person);
    void saveFlushManual(Person person);



}
