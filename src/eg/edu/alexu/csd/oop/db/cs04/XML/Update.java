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
	private String database;
	private String tableName;
	private Object[][] update_value;
	private Object[][] condition;
	private int count = 0;

	public Update(String database, String tableName, Object[][] update_value, Object[][] condition) {
		this.database = database;
		this.tableName = tableName;
		this.condition = condition;
		this.update_value = update_value;
		count = update();
	}

	private int update() {
		int counter = 0;
		try {
			String filepath = "dbs\\" + database + "\\" + tableName + ".xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			// Get the root element
			NodeList rows = doc.getElementsByTagName("row");
			if (rows.getLength() < 0) {
				return 0;
			}
			// loop over all rows
			for (int i = 0; i < rows.getLength(); i++) {
				// get row from rows list.
				Node nNode = rows.item(i);
				// Check rows has elements.
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// Access cols in this row.
					Element col = (Element) nNode;
					boolean flag = false;
					if (condition != null) {
						String colName = "";
						boolean f = false;
						for (int j = 0; j < condition.length; j++) {
							if (!condition[j][0].equals("0")) {
								colName = condition[j][0].toString();
							}
						}
						NodeList cols = col.getChildNodes();
						for (int j = 0; j < cols.getLength(); j++) {
							if (cols.item(j).getNodeName().equals(colName)) {
								break;
							}
							if (j == cols.getLength() - 1) {
								f = true;
							}
						}
						if (!f) {
							flag = updateWithCondition(col);
						}
					} else {
						flag = updateWithoutCondition(col);
					}
					if (flag) {
						counter++;
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
		return counter;
	}

	private boolean updateWithoutCondition(Element col) {
		// TODO Auto-generated method stub
		for (int j = 0; j < update_value[0].length; j++) {
			// Update cols.
			col.getElementsByTagName(update_value[0][j].toString()).item(0)
					.setTextContent(update_value[1][j].toString());
		}
		return true;
	}

	private boolean updateWithCondition(Element col) {
		// TODO Auto-generated method stub
		// Check update's condition.
		if (!condition[0][0].equals("0")) {
			if (col.getElementsByTagName(condition[0][0].toString()).item(0).getTextContent()
					.equals(condition[0][1].toString())) {
				// Loop over cols to update.
				return updateWithoutCondition(col);
			}
		} else if (!condition[1][0].equals("0")) {
			if (col.getElementsByTagName(condition[1][0].toString()).item(0).getTextContent()
					.compareTo(condition[1][1].toString()) > 0) {
				// Loop over cols to update.
				return updateWithoutCondition(col);
			}
		} else if (!condition[2][0].equals("0")) {
			if (col.getElementsByTagName(condition[2][0].toString()).item(0).getTextContent()
					.compareTo(condition[2][1].toString()) < 0) {
				// Loop over cols to update.
				return updateWithoutCondition(col);
			}
		} else {
			return updateWithoutCondition(col);
		}
		return false;
	}

	public int getCount() {
		return count;
	}
}