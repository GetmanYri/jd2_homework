package getman.homework.data.dao;

import getman.homework.data.pojo.Person;

public interface PersonDao {
    String savePerson(Person person);
    boolean deletePerson(String id);
    Person getPersonById(String id);
    Person loadPersonById(String id);
    void savePersonById(Person person);

}
