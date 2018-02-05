//$Id$
package app.kazen.main;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import app.kazen.manager.Manager;

public class MainClass {

	static Scanner scan = new Scanner(System.in);

	static Manager manager = Manager.getInstance();

	private MainClass() {
		// Singleton Class
	}

	public static void main(String[] args) {
		new MainClass().mainMenu();
	}

	private void mainMenu() {

		while (true) {
			System.out.print("\nMain Menu:\nThe available options are:\n1. Add User. \n2. List User.\n0. Exit.\nEnter your choice :  ");
			int choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
			case 1:// accessing admin account
			{
				addUser();
				break;
			}
			case 2:// accessing user account
			{
				listUser();
				break;
			}
			case 0: {
				System.out.println("Closing the Program");
				return;
			}
			default: {
				System.out.println("The number is not in list");
				break;
			}
			}
		}

	}

	private void addUser() {
		System.out.println("Enter UserName: ");
		String userName = scan.nextLine();
		Boolean status = manager.addUser(userName);
		if (status) {
			System.out.println("User Added successfully");
		} else {
			System.out.println("user addition failed");
		}
	}

	private void listUser() {
		List<Map<String, Object>> usersList = manager.getUsers();
		for (Map<String, Object> user : usersList) {
			for (Entry<String, Object> entry : user.entrySet()) {
				System.out.println(entry.getKey() + "  ::  " + entry.getValue());
			}
		}
	}
}
