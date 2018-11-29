package eg.edu.alexu.csd.oop.db.cs04.XML;

public class Main {
	public static void main(String[] args) {
		// createTable ct = new createTable("table1","name","mark");
		// Object[][] x = { { "column_name1", "column_name2", "column_name3" }, {
		// "11111111", 22222222, "33333333" } };

		Object[][] cond = { { "column_name3", "value_3" }, { "0", "0" }, { "0", "0" } };
		Delete up = new Delete("table1", cond);
		System.out.print(up.getcount());
	}
}
