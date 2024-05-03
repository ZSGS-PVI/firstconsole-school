package schoolbookpannel;

import java.sql.SQLException;
import java.util.Scanner;

import schoolbookpannel.dto.Student;
import schoolbookpannel.student.AddStudentViewModel;
import schoolbookpannel.SchoolBookPannel;
public class SchoolBookPannel {
	private AddStudentViewModel addStudentViewModel;
	private Scanner in = new Scanner(System.in);
	static SchoolBookPannel schoolbookpannel;
	private Result result;

	public SchoolBookPannel() {
		result = new AddStudent();
		addStudentViewModel = new AddStudentViewModel(this);
	}

	public static void main(String[] args) throws SQLException {

		schoolbookpannel = new SchoolBookPannel();
		schoolbookpannel.init();

	}

	public void init() throws SQLException {

		System.out.println("--------WELCOME TO SCHOOL MANAGEMENT SYSTEM--------");
		System.out.println(" 1.Admin\n 2.Student \n 3.exit");

		int choice = in.nextInt(); // Move this line inside the loop

		switch (choice) {
		case 1:
			System.out.println("1. Login\n2. Sign Up");
			int adminChoice = in.nextInt();

			if (adminChoice == 1) {
				boolean loggedIn = login();
				if (loggedIn) {
					System.out.println("Login successful:");

					adminfun();

				} else {
					System.out.println("Login failed. Exiting....");
				}

			} else if (adminChoice == 2) {
				signUp();
				boolean loggedIn = login();
				if (loggedIn) {
					System.out.println("Login successful!");
					adminfun();
				} else {
					System.out.println("Login failed. Exiting....");
				}
			} else {
				System.out.println("Invalid choice.");
			}
			break;

		case 2:
			studentfun();
			break;

		case 3:
			System.out.println("Goodbye! Have a nice day!");
			System.exit(0);
			break;

		default:
			System.out.println("Invalid choice.");
			break;
		}
	}

	private boolean checkAdminExistence() {
		return addStudentViewModel.loginCount();

	}

	private void signUp() throws SQLException {

		System.out.println("You Already Have Account Then Login:");
		System.out.println("Enter the id:");
		int id = in.nextInt();
		System.out.println("Enter name:");
		String name = in.next();
		System.out.println("Enter Email:");
		String email = in.next();
		addStudentViewModel.isValidEmail(email);
		System.out.println("Enter Password:");
		String password = in.next();
		Student student = new Student(id, name, email, password);
		result.signup(student);

	}

	private boolean login() throws SQLException {

		System.out.println("Enter Email:");
		String email = in.next();
		System.out.println("Enter Password:");
		String password = in.next();
		in.nextLine();
		Student student = new Student(email, password);
		return addStudentViewModel.login(student);

	}

	private void adminfun() {
		// AddStudent student1 = new AddStudent();

		while (true) {

			System.out.println(" 1.AddStudent\n 2.ViewStudent \n 3.UpdateStudent \n 4.DeleteStudent \n 5.exit");
			int nextInt = in.nextInt();

			switch (nextInt) {
			case 1:
				result.addStudentInfo();
				break;
			case 2:
				result.viewStudent();
				break;
			case 3:
				result.updateStudent();
				break;
			case 4:
				result.deleteStudent();
				break;
			case 5:
				System.out.println("back to admin and Student!");
				try {
					schoolbookpannel.init();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Invalid choice.");
				break;
			}

		}

	}

	private void studentfun() throws SQLException {
		// Assuming schoolbookpannel has an instance of AddStudent
		//if (schoolbookpannel != null && schoolbookpannel.result != null) {
			System.out.println("Student only Read the file:");
			schoolbookpannel.result.viewStudent();
			init();
//		} else {
//			System.out.println("schoolbookpannel or addStudent is null");
//		}
	}
}
