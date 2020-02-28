package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Enter department's name: ");
		Department department = new Department(sc.nextLine());
		
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		WorkerLevel level = WorkerLevel.valueOf(sc.next());
		System.out.print("Base salary: ");
		double salary = sc.nextDouble();
		
		Worker worker = new Worker(name, level, salary, department);
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		sc.nextLine();
		
		for (int i = 1; i <= n; i++) {
			System.out.println("Enter contract #" + i + " data:");
			System.out.print("Date (DD/MM/YYYY): ");
			Date date = sdf1.parse(sc.nextLine());
			System.out.print("Value per hour: ");
			double valueHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();
			sc.nextLine();
			
			worker.addContract(new HourContract(date, valueHour, hours));
		}
		System.out.println();
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		Date date = sdf2.parse(sc.nextLine());
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		System.out.println(worker);
		double income = worker.income(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		System.out.println("Income for " + sdf2.format(date) + ": " 
							+ String.format("%.2f", income));

	}
}
