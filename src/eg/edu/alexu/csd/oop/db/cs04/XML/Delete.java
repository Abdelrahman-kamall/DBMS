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

public class Delete {

	private String tableName;
	private Object[] condition;

	public Delete(String tableName, Object[] condition) {
		this.tableName = tableName;
		this.condition = condition;
		delete();
	}

	private void delete() {
		// TODO Auto-generated method stub
		try {
			String filepath = "dbs\\db1\\" + tableName + ".xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			NodeList rows = doc.getElementsByTagName("row");

			// loop over all rows
			for (int i = rows.getLength() - 1; i >= 0; i--) {
				// get row from rows list.
				Node nNode = rows.item(i);
				// Check rows has elements.
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// Access cols in this row.
					Element col = (Element) nNode;

					if (condition != null) {
						deleteWithCondition(nNode, col);
					} else {
						deleteWithoutCondition(nNode, col);
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

	private void deleteWithoutCondition(Node nNode, Element col) {
		// TODO Auto-generated method stub
		// delete parent of col.
		nNode.getParentNode().removeChild(col);
	}

	private void deleteWithCondition(Node nNode, Element col) {
		// TODO Auto-generated method stub
		// Check delete's condition.
		if (col.getElementsByTagName(condition[0].toString()).item(0).getTextContent()
				.equals(condition[1].toString())) {
			// delete parent of col.
			nNode.getParentNode().removeChild(col);
		}
	}
}
