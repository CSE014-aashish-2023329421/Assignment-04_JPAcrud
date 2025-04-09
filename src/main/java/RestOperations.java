import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RestOperations {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("emp");

    public static void createEmployee(String name) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setName(name);
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Employee added successfully.");
    }

    public static void readEmployees() {
        EntityManager entityManager = factory.createEntityManager();
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        for (Employee e : employees) {
            System.out.println("ID: " + e.getId() + ", Name: " + e.getName());
        }
        entityManager.close();
    }

    public static void updateEmployee(int id, String newName) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            employee.setName(newName);
            entityManager.merge(employee);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteEmployee(int id) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Insert Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    createEmployee(name);
                    break;
                case 2:
                    readEmployees();
                    break;
                case 3:
                    System.out.print("Enter employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    updateEmployee(updateId, newName);
                    break;
                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteEmployee(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    factory.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
