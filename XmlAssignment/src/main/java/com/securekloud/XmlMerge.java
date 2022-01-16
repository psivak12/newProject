package com.securekloud;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;


public class XmlMerge {

	public static void main(String[] args) throws IOException{
		try {
			SAXBuilder saxBuilder = new SAXBuilder();		
			Document finalXml = mergeXml(saxBuilder.build(XmlData.xml1Filepath), saxBuilder.build(XmlData.xml2Filepath));			
			if(finalXml != null) {				
				printXml(finalXml);		        
		        loadXml(finalXml);
			}			        
		} catch (Exception e) {
			e.printStackTrace();
		}       
	}
	
	public static Document mergeXml(Document xml1, Document xml2) {
		try {
			List<Element> xmlData1 = xml1.getRootElement().getChildren();
	        List<Element> xmlData2 = xml2.getRootElement().getChildren();
	        for (Element e1 : xmlData1) {
	            for (Element e2 : xmlData2) {
	                if (e1.getAttributeValue("name").equals(e2.getAttributeValue("name"))) {
	                    e1.addContent(e2.removeContent());
	                }
	            }
	        }
	        xml1.getRootElement().setName(XmlData.tag);
	        return xml1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void printXml(Document finalXml) {	
        try {
        	XMLOutputter out = new XMLOutputter();
            out.output(finalXml, System.out);
			out.output(finalXml, new FileOutputStream(XmlData.outputXmlFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadXml(Document finalXml) {
		try {    	   
	    	Element rootNode = finalXml.getRootElement();	    	
	    	XmlLoad xmlload = new XmlLoad();
	    	
			xmlload.getConnection();
			
			List<Element> children = rootNode.getChildren();
	        Iterator<Element> iterator = children.iterator();
	        
	        while (iterator.hasNext()) {
	          Element child = (Element) iterator.next();
	          System.out.println(child.getAttributeValue("name")+ " - " +child.getChildText("address")+ " - " +child.getChildText("phonenumber")+ " - " +child.getChildText("salary")+ " - " +child.getChildText("pension"));
	          xmlload.addToDB(child.getAttributeValue("name"), child.getChildText("address"), child.getChildText("phonenumber"), child.getChildText("salary"), child.getChildText("pension"));
	        }
	        
			xmlload.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}


