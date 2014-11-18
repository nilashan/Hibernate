import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.krishantha.training.hibernate.object.Alert;
import com.krishantha.training.hibernate.object.AllocationData;
import com.krishantha.training.hibernate.object.AllocationHistory;
import com.krishantha.training.hibernate.object.Employee;
import com.krishantha.training.hibernate.util.HibernateUtilities;

public class Application {

	public static void main(String args[]) {

	save();
		// load();
		loadQueryBased();
	}
	
	static void loadQueryBased()
	{
		Session session=HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		/*Query query = session.createQuery("from Employee").setFirstResult(0).setMaxResults(2);*/
		
		/*Query query=session.getNamedQuery("getEmployees");
		List<Employee> employees=query.list();
		for(Employee employee:employees)
		{
			System.out.println(employee.getName());
		}*/
		
		Criteria criteria=session.createCriteria(Employee.class);
		criteria.add(Restrictions.eqOrIsNull("age", 26));
			List<Employee> employees=criteria.list();	
			for(Employee employee:employees)
			{
				System.out.println(employee.getName());
			}
		session.getTransaction().commit();
		session.close();

	}
	
	

	static void save() {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();

		Employee luke = new Employee(1, "Luke", 32, new AllocationData("col",
				"01"), new AllocationHistory(new Date(), "p1"),
				new Alert("Hi"));
		Employee gihan = new Employee(1, "Gihan", 23, new AllocationData(
				"Galle", "01"), new AllocationHistory(new Date(), "p1"),
				new Alert("Good"));
		Employee menuka = new Employee(1, "Menuka", 22, new AllocationData(
				"Badulla", "01"), new AllocationHistory(new Date(), "p1"),
				new Alert("Bad"));
		Employee dilini = new Employee(1, "Dilini", 25, new AllocationData(
				"Kadawatha", "01"), new AllocationHistory(new Date(), "p1"),
				new Alert("Go"));
		Employee ruchira = new Employee(1, "Ruchira", 26, new AllocationData(
				"Colombo", "01"), new AllocationHistory(new Date(), "p1"),
				new Alert("Come"));
		Employee shashin = new Employee(1, "Shashin", 26, new AllocationData(
				"Galle", "01"), new AllocationHistory(new Date(), "p1"),
				new Alert("Come"));

		session.save(luke);
		session.save(gihan);
		session.save(menuka);
		session.save(dilini);
		session.save(ruchira);
		session.save(shashin);

		session.getTransaction().commit();
		session.close();
	}

	static void load() {
		Session session = HibernateUtilities.getSessionFactory().openSession();

		session.beginTransaction();

		Employee employee = (Employee) session.load(Employee.class, 1);
		employee.addAllocationHistory(new AllocationHistory(new Date(), "ROC"));

		System.out.println(employee.getName() + "  " + employee.getAge()
				+ " years old");

		employee.addAllocationHistory(new AllocationHistory(new Date(), "BT"));

		for (AllocationHistory allocationHistory : employee
				.getAllocationHistories()) {
			System.out.println(allocationHistory.getAllocationDate() + "-"
					+ allocationHistory.getProjectCode());
		}

		
		
		session.getTransaction().commit();
		session.close();
	}
}
