package getman.homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestExampleJPA {

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("getman.homework");

        EntityManager entityManager = emf.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void main() {



    }
}