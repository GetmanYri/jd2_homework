package getman.homework.data.task2;

import getman.homework.data.dao.DaoForTask2_3.PersonDao;
import getman.homework.data.dao.DaoForTask2_3.PersonDaoImp;
import getman.homework.data.pojo.forTask2_3.BankAccount;
import getman.homework.data.pojo.forTask2_3.Person;
import getman.homework.data.util.HibernateSessionFactory;
import getman.homework.data.util.Scanner;

public class DBOperationMain {
    private final static PersonDao personDao = new PersonDaoImp(HibernateSessionFactory.getSessionFactory());

    public static void main(String[] args) {
        while (true) {
            action(choosingAction());

        }
    }

    private static Person setPerson() {
        Person person = new Person();
        try {
            System.out.println("Enter a name");
            person.setName(Scanner.scannerString());
            System.out.println("Enter the age");
            person.setAge(Scanner.scannerInt());
            System.out.println("Enter a surname");
            person.setSurname(Scanner.scannerString());

        } catch (Exception e) {
            System.out.println("Неверный ввод данных пользователя");
            e.printStackTrace();
            System.exit(1);
        }
        return person;

    }

    private static BankAccount setBankAccount() {

        BankAccount bankAccount = new BankAccount();
        try {
            System.out.println("Enter the bank account");
            System.out.println("(Skip - enter 1;)");
            long action = Scanner.scannerLong();
            if (action == 1) {
                return bankAccount = null;
            }
            bankAccount.setAccountNumber(action);

        } catch (Exception e) {
            System.out.println("Неверный ввод данных банковского счета");
            System.exit(1);
            e.printStackTrace();
        }
        return bankAccount;
    }

    private static int choosingAction() {
        int action = 0;
        System.out.println("Select an action");
        System.out.println("1 - Save new user");
        System.out.println("2 - Find user");
        System.out.println("3 - Delete user");
        System.out.println("4 - exit");
        try {
            action = Scanner.scannerInt();
        } catch (Exception e) {
            System.out.println("Incorrect data entered: 'Select an action'");
            e.printStackTrace();
            System.exit(3);
        }
        return action;
    }

    private static String save(Person person, BankAccount bankAccount) {
        if (person == null) {
            System.out.println("person is null");
            System.exit(2);
        }
        if (bankAccount == null) {
            personDao.save(person);
            return "person saved";
        }

        bankAccount.setPerson(person);
        personDao.save(person);
        personDao.save(bankAccount);

        return "Person and bank account saved";
    }

    private static String find() {
        Person person;
        String id = readId();
        person = personDao.getPersonWithBankAccountById(id);

        if (person == null) {
            return "Person with id " + id + " not found";
        }
        return person.toString();
    }

    private static String deleteById() {
        boolean found;
        String id = readId();
        found = personDao.deletePerson(id);
        if (found) {
            return "person with id " + id + "deleted";
        }
        return "person with id " + id + " not found";
    }


    private static void action(int action) {
        switch (action) {
            case 1:
                System.out.println(save(setPerson(), setBankAccount()));
                break;
            case 2:
                System.out.println(find());
                break;
            case 3:
                System.out.println(deleteById());
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect entered");
        }
    }

    private static String readId() {
        System.out.println("enter id person");
        return Scanner.scannerString();
    }


}
