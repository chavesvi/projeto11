package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a path to the file:");
		String strPath = sc.nextLine();
		
		File path = new File(strPath);
		List<Product> products = new ArrayList<>();
	
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while (line != null) {
				String[] list = line.split(";");
				double price = Double.valueOf(list[1]).doubleValue();
				int quantity = Integer.valueOf(list[2]).intValue();
			    products.add(new Product(list[0], price, quantity));
			    line = br.readLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		String parent = path.getParent();
		
		boolean success = new File(parent + "\\out").mkdir();
		System.out.println("Directory created successfully: " + success);
		
		parent = parent + "\\out\\summary.csv";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(parent))){
			for (Product p : products) {
				bw.write(p.getName() + ";" + p.totalValue());
				bw.newLine();
			}
	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		reader(new File(parent));
		
		sc.close();
	}
	
	public static void reader(File path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
