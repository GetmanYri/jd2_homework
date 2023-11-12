package getman.homework.data.dao;

import getman.homework.data.pojo.BankAccount;
import getman.homework.data.pojo.Person;

public interface PersonDao {
    //String savePerson(Person person);
    String save(Person person);
    String save(BankAccount bankAccount);
    boolean deletePerson(String id);
    Person getPersonById(String id);
    Person loadPersonById(String id);
    void savePersonWithId(Person person);
    void saveBankAccountWithId(BankAccount bankAccount);
    void saveFlushManual(Person person);

}
