import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");
        EntityManager entityManager = factory.createEntityManager();
        
        entityManager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setName("FromJpa");
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        
        System.out.println("Employee inserted successfully!");
        
        entityManager.close();
        factory.close();
    }
}
