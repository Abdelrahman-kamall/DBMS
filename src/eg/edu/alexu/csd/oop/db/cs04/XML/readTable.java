package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class readTable {

	private String database;
	private String tableName;
	private Object[] selected;
	private Object[][] condition;

	public readTable(String database, String tableName, Object[][] condition, Object[] selected) {
		this.database = database;
		this.tableName = tableName;
		this.selected = selected;
		this.condition = condition;
		readXML("dbs\\" + database + "\\" + tableName + ".xml");
	}

	public void readXML(String path) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new File(path));
			document.getDocumentElement().normalize();

			// get Table name
			System.out.println("Root element :" + document.getDocumentElement().getNodeName());
			// get rows
			NodeList rows = document.getElementsByTagName("row");

			// loop over all rows
			for (int i = 0; i < rows.getLength(); i++) {
				// get row from rows list.
				Node nNode = rows.item(i);
				// Check rows has elements.
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// Access cols in this row.
					Element col = (Element) nNode;
					if (condition != null) {
						// if()
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
