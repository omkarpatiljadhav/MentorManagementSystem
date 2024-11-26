package com.nit.mentors.testMentors;

import java.util.List;
import java.util.Scanner;

import com.nit.mentors.helpers.MentorIdGenerator;
import com.nit.mentors.mentorDAO.MentorDAOOperation;
import com.nit.mentors.mentorService.MentorService;
import com.nit.mentors.mentorServiceImpl.MentorServiceImple;
import com.nit.mentors.models.Address;
import com.nit.mentors.models.Mentor;

public class TestMentor {
	private static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

	public static boolean isValidDetails(String name, String designation, String country, String state, String district,
			String phno, double salary) {
		int count = 0;

		if (name.trim().length() >= 5) {
			if (isValidString(name.toCharArray()))
				count++;
			else
				System.err.println("Invalid name");
		} else
			System.err.println("Name must be in 5 characters");

		if (designation.trim().length() >= 5) {
			if (isValidString(name.toCharArray()))
				count++;
			else
				System.err.println("Invalid designation");

		} else {
			System.err.println("Designation must be in 5 characters and it must be a valid designation");
		}
		if (country.trim().length() >=2) {
			if (isValidString(country.toCharArray()))
				count++;
			else
				System.err.println("Invalid country");
		} else {
			System.err.println("Country name must be in 4 characters");
		}
		if (state.trim().length() >= 4) {
			if (isValidString(state.toCharArray()))
				count++;
			else
				System.err.println("Invalid state");
		} else {
			System.err.println("State name must be in 4 characters");
		}
		if (district.trim().length() >= 4) {
			if (isValidString(district.toCharArray()))
				count++;
			else
				System.err.println("Invalid district");
		} else {
			System.err.println("District name must be in 4 characters");
		}
		if (phno.trim().length() == 10) {
			boolean flag = true;
			for (char c : phno.toUpperCase().toCharArray()) {
				if (c >= 'A' && c <= 'Z') {

					flag = false;
					break;

				}
			}
			if (flag == true)
				count++;
			else {
				System.err.println("Invalid phno");
				count--;
			}

		} else {
			System.err.println("Invalid length of phone no");
		}
		if (salary > 0) {
			count++;
		} else {
			System.err.println("Invalid salary");
		}

		return count == 7 ? true : false;

	}

	public static boolean isValidString(char[] arr) {
		for (char c : arr) {
			if (c >= '0' && c <= '9') {

				return false;

			}
		}
		return true;
	}

	// client app /crome /User
	public static void main(String[] args) {

		MentorService mentorService = new MentorServiceImple(new MentorDAOOperation());

		boolean flag = true;
		/*
		 * \u001B[32m-green \u001B[33m-yellow \u001B[0m-reset \u001B[44m-blue
		 */
		System.out.println(
				"\u001B[32m******\u001B[0m\u001B[33mWelcome To Mentor Management System\u001B[0m\u001B[32m*****\u001B[0m");
		System.out.println("\u001B[33m==============================================\u001B[0m");

		while (flag) {
			System.out.println("1.Save Mentor\n" + "2.Check mentor\n" + "3.Update Mentor\n" + "4.delete Mentor\n"
					+ "5.Find All Mentor\n" + "6.Exit");
			System.out.print("Enter your choice : ");

			try {
				int choice = Integer.parseInt(scanner.nextLine());
				Mentor mentor = null;
				Address address = null;

				switch (choice) {
				case 1: {

					System.out.print("Enter name : ");
					String name = scanner.nextLine();

					System.out.print("Enter designation : ");
					String designation = scanner.nextLine();

					System.out.print("Enter salary : ");
					double salary;
					try
					{
					 salary = Double.parseDouble(scanner.nextLine());
					}
					catch(Exception e)
					{
						throw new Exception("Invalid Salary...");
					}

					System.out.print("Enter mobileNo : ");
					String phno = scanner.nextLine();

					System.out.print("Enter country : ");
					String country = scanner.nextLine();

					System.out.print("Enter state : ");
					String state = scanner.nextLine();

					System.out.print("Enter district : ");
					String district = scanner.nextLine();

					System.out.println();
					if (isValidDetails(name, designation, country, state, district, phno, salary)) {

						final String ID = MentorIdGenerator.getIdOfMentor();
						address = new Address(country, state, district);
						mentor = new Mentor(ID, name, designation, salary, Long.parseLong(phno), address);
						// System.out.println(mentor);
						if (mentorService.registerMentor(mentor)) {

							System.out.println(
									"\u001B[32mCongratulation..Mr. " + name + " with id : " + ID + "\u001B[0m");
							System.out.println("\u001B[32mRegistration Successfull..\u001B[0m");
						} else {
							System.err.println("Soory ! Some Technical Issue Came Try After Sometime.");
						}

					} else {
						System.err.println("\nPlease check !  you providing above wrong data");
					}
					break;
				}
				case 2: {
					System.out.print("Enter Mentor Id : ");
					String id = scanner.nextLine();

					Mentor vm = mentorService.checkMentor(id);

					if (vm != null) {
						Address ad = vm.getAddress();

						String mobileNo = String.valueOf(vm.getMobileNo());

						System.out.println(
								"\u001B[32mId     Name     Designation     Salary    MobileNo    County    State       District\u001B[0m");
						System.out.println(
								"\u001B[33m================================================================================================\u001B[0m");
						System.out.printf("%s     %s     %s     %.2f     %s    %s    %s    %s\n", vm.getMentorId(),
								vm.getMentorName(), vm.getDesignation(), vm.getSalary(), mobileNo, ad.getCountry(),
								ad.getState(), ad.getDistrict());
						System.out.println(
								"\u001B[33m================================================================================================\u001B[0m");

					} else {
						System.err.println("Mentor doesn't exist with id : " + id);
					}
					break;

				}

				case 3: {
					System.out.print("Enter Mentor Id : ");
					String id = scanner.nextLine();

					Mentor m = mentorService.checkMentor(id);
					if (m != null) {
						System.out.print("Enter name : ");
						String name = scanner.nextLine();

						System.out.print("Enter designation : ");
						String designation = scanner.nextLine();

						System.out.print("Enter salary : ");
						double salary = Double.parseDouble(scanner.nextLine());

						System.out.print("Enter mobileNo : ");
						String phno = scanner.nextLine();

						System.out.print("Enter country : ");
						String country = scanner.nextLine();

						System.out.print("Enter state : ");
						String state = scanner.nextLine();

						System.out.print("Enter district : ");
						String district = scanner.nextLine();

						if (isValidDetails(name, designation, country, state, district, phno, salary)) {
							m.setMentorName(name);
							m.setDesignation(designation);
							m.setMobileNo(choice);
							m.setSalary(salary);
							m.setAddress(new Address(country, state, district));
							if (mentorService.updateMentor(m)) 
							{

								System.out.println(
										"\u001B[32mCongratulation..Mr. " + name + " with id : " + id + "\u001B[0m");
								System.out.println("\u001B[32mUpdate Information  Successfull..\u001B[0m");
							} else {
								System.err.println("Soory ! Some Technical Issue Came Try After Sometime.");
							}
						} else {
							System.err.println("\nPlease check !  you providing above wrong data");
						}

					}

					else {
						System.err.println("Mentor doesn't exist with id : " + id);
					}

					break;
				}

				case 4: {

					System.out.print("Enter Mentor Id : ");
					String id = scanner.nextLine();
					Mentor m = mentorService.checkMentor(id);
					if (m != null) {
						if (mentorService.deleteMentor(id)) {
							System.out.println("\u001B[32mCongratulation..Mr. " + m.getMentorName() + " with id : " + id
									+ "\u001B[0m");
							System.out.println("\u001B[32mDelete Information  Successfully..\u001B[0m");
						} else {
							System.err.println("\nSomething went wrong..try again");
						}
					} else {
						System.err.println("Mentor doesn't exist with id : " + id);
					}

					break;
				}

				case 5: {
					List<Mentor> allMentor = mentorService.getAllMentor();

					System.out.println(
							"\u001B[32mId     Name     Designation     Salary    MobileNo    County    State       District\u001B[0m");
					System.out.println(
							"\u001B[33m================================================================================================\u001B[0m");

					if (allMentor != null) {
						allMentor.forEach(m -> {

							Address add = m.getAddress();
							System.out.printf("%s     %s     %s     %.2f     %s    %s    %s    %s\n", m.getMentorId(),
									m.getMentorName(), m.getDesignation(), m.getSalary(), m.getMobileNo(),
									add.getCountry(), add.getState(), add.getDistrict());

						});
						System.out.println(
								"\u001B[33m================================================================================================\u001B[0m");
					} else {
						System.err.println("No Mentor is available....");
					}

					break;
				}
				case 6: {
					flag = false;
					break;
				}

				default: {
					System.err.println("Enter a valid choice");
					System.out.println("\u001B[33mPlease provide valid input\u001B[0m");
				}

				}// end of switch
			} // end of try
			catch (Exception e) 
			{
				System.err.println(e.getMessage());

			}
		} // while

		System.out.println("\u001B[32m*******Thank you Phir melenge*******\u001B[0m");

	}

}
