// erstellt am 23.11.2023
package Paket;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// ausgeben und verändern einer XML datei  
public class DOMParserUebung {
		static final String FilePATH_IN = "Order.xml";
		private static Document doc;
		
	public static void main(String[]args) {
		read();
		printAndChange();
		serialize();
	}
	
	public static void read() {
		
		DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		
		try {
			DocumentBuilder builder = factory. newDocumentBuilder();
			doc = builder.parse(new File(FilePATH_IN));
		}
		catch(ParserConfigurationException | SAXException| IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printAndChange() {
		Node ordernode = doc.getElementsByTagName("Order").item(0);
		System.out.println("Order");
		System.out.println("Priority: "
		+ordernode.getAttributes().getNamedItem("priority").getTextContent());
		System.out.println("OrderID: "+ doc.getElementsByTagName("OrderID").
				item(0).getTextContent());
		NodeList products= doc.getElementsByTagName("Product");
		//System.out.println("Product Name: "+ products.item(0));
		float total=0;
		for(int i = 0;i< products.getLength();i++) {
			Node product= products.item(i);
			
				System.out.println("\nProduct Name: "+ product.getChildNodes()
				.item(0).getTextContent());
				
				float price = new Float(product.getChildNodes().item(1)
						.getTextContent()).floatValue();
				System.out.println("Price: "+price);
				
				int quantity=new Float(product.getChildNodes().item(2).getTextContent()
						).intValue();
				
				System.out.println("Quality: "+quantity);
				
				float producttotal= price * quantity;
				System.out.println("Product Total: "+ producttotal);
				total+= producttotal;
				
				// hier wird xml geändert
				Node newnode= doc.createElement("ProductTotal");
				newnode.setTextContent(""+producttotal);
				product.appendChild(newnode);
				
				
	//	NodeList products = doc.getElementsByTagName("");
		}
		System.out.println();
		System.out.println("Grand total: "+total);
	
	}
	public static void serialize() {
		DOMSource source = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new File("src/Paket/Order.xml"));
		
		TransformerFactory tf= TransformerFactory.newInstance();
		try {
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "OrderNew.dtd");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			serializer.transform(source, streamResult);
		}catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}

}

// die Order.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE Order SYSTEM "Order.dtd" >
<Order priority="high">
  <OrderID>4711</OrderID>
  <Date>11.11.2011</Date>
  <Sender>
    <Name>Daimler AG</Name>
    <Address>
      <Street>Mercedesstraße 137</Street>
      <ZIP>70327</ZIP>
      <City>Stuttgart</City>
    </Address>
  </Sender>
  <Receiver>
    <Name>Robert Bosch GmbH</Name>
    <Address>
      <Street>Robert-Bosch-Platz 1</Street>
      <ZIP>70839</ZIP>
      <City>Gerlingen</City>
    </Address>
  </Receiver>
  <Productlist>
    <Product>
      <Productname>Zuendkerzen A111</Productname>
      <Price>10.00</Price>
      <Quantity>300</Quantity>
    </Product>
        <Product>
      <Productname>ABS X111</Productname>
      <Price>1000.00</Price>
      <Quantity>50</Quantity>
    </Product>
  </Productlist>
</Order>
