package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Update {

	private String tableName;
	private Object[][] update_value;
	private Object[][] condition;

	public Update(String tableName, Object[][] update_value, Object[][] condition) {
		this.tableName = tableName;
		this.condition = condition;
		this.update_value = update_value;
		update();
	}

	private void update() {

		try {
			String filepath = "dbs\\db1\\" + tableName + ".xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			NodeList rows = doc.getElementsByTagName("row");

			// loop over all rows
			for (int i = 0; i < rows.getLength(); i++) {
				// get row from rows list.
				Node nNode = rows.item(i);
				// Check rows has elements.
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// Access cols in this row.
					Element col = (Element) nNode;

					if (condition != null) {
						updateWithCondition(col);
					} else {
						updateWithoutCondition(col);
					}
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateWithoutCondition(Element col) {
		// TODO Auto-generated method stub
		for (int j = 0; j < update_value[0].length; j++) {
			// Update cols.
			col.getElementsByTagName(update_value[0][j].toString()).item(0)
					.setTextContent(update_value[1][j].toString());
		}
	}

	private void updateWithCondition(Element col) {
		// TODO Auto-generated method stub

		// Check update's condition.
		if (!condition[0][0].equals("0")) {
			if (col.getElementsByTagName(condition[0][0].toString()).item(0).getTextContent()
					.equals(condition[0][1].toString())) {
				// Loop over cols to update.
				updateWithoutCondition(col);
				/*
				 * for (int j = 0; j < update_value[0].length; j++) { // Update cols.
				 * col.getElementsByTagName(update_value[0][j].toString()).item(0)
				 * .setTextContent(update_value[1][j].toString()); }
				 */
			}
		} else if (!condition[1][0].equals("0")) {
			if (col.getElementsByTagName(condition[1][0].toString()).item(0).getTextContent()
					.compareTo(condition[1][1].toString()) > 0) {
				// Loop over cols to update.
				updateWithoutCondition(col);
			}

		} else if (!condition[2][0].equals("0")) {
			if (col.getElementsByTagName(condition[2][0].toString()).item(0).getTextContent()
					.compareTo(condition[2][1].toString()) < 0) {
				// Loop over cols to update.
				updateWithoutCondition(col);
			}

		} else {
			updateWithoutCondition(col);
		}
	}
}
