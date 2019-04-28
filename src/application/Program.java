package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		System.out.print("Enter salary: ");
		double enterSalary = sc.nextDouble();
		
		System.out.println();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<Employee>();
			
			String line = br.readLine();
			while(line !=null) {
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);
				
				list.add(new Employee(name, email, salary));
				
				line = br.readLine();
			}
			
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() >= enterSalary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			for(String email: emails) {
				System.out.println(email);
			}
			
			System.out.println("Email of people whose salary is more than: " + String.format("$%.2f", enterSalary));
			System.out.println();
			
			double sum = list.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M':" + String.format("$%.2f", sum));
			
			
		}
		catch(IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		sc.close();

	}

}
