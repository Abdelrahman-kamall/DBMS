package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String query = scan.nextLine();
		for (; scan.hasNextLine();) {
			query += scan.nextLine();
		}
		
		while (!query.equals("exit")) {
			//call abdallah's new class;
			query = "";
			query = scan.nextLine();
			for (; scan.hasNextLine();) {
				query += scan.nextLine();
			}
		}
	}
}
