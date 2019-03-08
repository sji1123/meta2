package com.meta.xml.readwrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.meta.xml.dto.F_TB;
import com.meta.xml.dto.P_TB;

public class XmlRead {
	//xml문서를  파싱하는 일이 많기 때문에 반복을 줄이기 위해 변수선언을 클래스에서 해주었다.
    DocumentBuilderFactory objDocumentBuilderFactory = null;
    DocumentBuilder objDocumentBuilder = null;
    Document doc = null;
	XPath xpath = XPathFactory.newInstance().newXPath();

    public XmlRead(String filePath) {
    	try {
			ReadTbase(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
    };
    /*
     * @param filePath
     * @return null
     * 
     * T_BASEFILE_TB의  FILE_ID를 읽어 리스트에 담는다.
     * 
     * */
    private void ReadTbase(String filePath) throws Exception{
    	ArrayList<String> tBase = new ArrayList<String>();
    	try{
    		objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    		objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
    		doc = objDocumentBuilder.parse(filePath);
    		NodeList fileList = (NodeList)xpath.evaluate("//FILE_ID", doc, XPathConstants.NODESET);
    		for(int i = 0 ; i < fileList.getLength(); i++) {
    			tBase.add(fileList.item(i).getTextContent());
    		}
    		ReadFTB(tBase);
    	}catch(Exception ex){
    		throw ex;
    	}       
    }
    
    /*
     * @param ArrayList<String> tBase
     * @return void
     * 
     * T_BASEFILE_TB에서 읽어온 FILE_ID 리스트로 
     * F_*_TB.xml과 P_*_TB.xml 을 읽을수 있게 하였고
     * F_*_TB 의  SIMILAR_RATE / 100이 15이상인 ROW들을  NodeList에 담아주고 
     * NodeList 길이만큼 반복하여 Node에 들어있는 값들을 가져와 사용하였다.
     * */
	private void ReadFTB(ArrayList<String> tBase) throws ParserConfigurationException, XPathExpressionException {
		
		for(int j = 0 ; j < tBase.size(); j ++) {
			ArrayList<F_TB> farr = new ArrayList<F_TB>();
			ArrayList<String> arr = new ArrayList<String>();
			
			String fFileName = "F_"+tBase.get(j)+"_TB.xml";
			String pFileName = "P_"+tBase.get(j)+"_TB.xml";
			System.out.println(fFileName);
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    		try {
    			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
				doc = objDocumentBuilder.parse(fFileName);
				NodeList fList = (NodeList)xpath.evaluate("/TABLE/ROWS/ROW[SIMILAR_RATE div 100 >= 15]", doc, XPathConstants.NODESET);
				for(int i = 0 ; i < fList.getLength(); i++) {
					
					Node node = fList.item(i);
		
					String rowid = node.getChildNodes().item(1).getTextContent();
					String volume = node.getChildNodes().item(3).getTextContent();
					String file_name = node.getChildNodes().item(5).getTextContent();
					String release_name = node.getChildNodes().item(7).getTextContent();
					String similar_rate = node.getChildNodes().item(9).getTextContent();
					String file_path = node.getChildNodes().item(11).getTextContent();
			        String fpId = node.getChildNodes().item(13).getTextContent();
					String exclusion = node.getChildNodes().item(15).getTextContent();
					String comment = node.getChildNodes().item(17).getTextContent();
	
					if(!fpId.equals("")) {
						F_TB ftb = new F_TB(rowid,volume,file_name,release_name,similar_rate,file_path,fpId,exclusion,comment);
						arr.add(fpId);
						farr.add(ftb);
					}
				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		//arr리스트에서 중복값을 제거하고 다시 리스트에 담는다.
    		HashSet<String> arr2 = new HashSet<String>(arr);
    		ArrayList<String> arr3 = new ArrayList<String>(arr2);
    		
    		ArrayList<P_TB> parr = ReadPTB(pFileName, arr3);
    		ArrayList<F_TB> farr2 = SetComment(farr, parr);
    		String tFileName = "T_"+tBase.get(j)+"_TB.xml";
    		if(farr2.size()>0) {
    		WriteXML(farr2, tFileName);
    		}
		}
		
	}
	/*
	 * @param ArrayList<F_TB> farr2, String tFileName
	 * @return void
	 * 최종적으로 COMMENT가 셋팅된 farr2를 tFileName을 파일명으로 만든다.  
	 * */
	private void WriteXML(ArrayList<F_TB> farr2, String tFileName) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		    DocumentBuilder documentBuilder;
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document doc = documentBuilder.newDocument();
			
				Element rootElement = doc.createElement("TABLE");
				doc.appendChild(rootElement);
				Element child1 = doc.createElement("ROWS");
				rootElement.appendChild(child1);
				Element child2 = doc.createElement("ROW");
				
				for(F_TB f : farr2) {
					
					child1.appendChild(child2);
					Element ROWID = doc.createElement("ROWID");
					child2.appendChild(ROWID).setTextContent(f.getROWID());
		            Element VOLUME = doc.createElement("VOLUME");
		            child2.appendChild(VOLUME).setTextContent(f.getVOLUME());
		            Element FILE_NAME = doc.createElement("FILE_NAME");
		            child2.appendChild(FILE_NAME).setTextContent(f.getFILE_NAME());
		            Element RELEASE_NAME = doc.createElement("RELEASE_NAME");
		            child2.appendChild(RELEASE_NAME).setTextContent(f.getRELEASE_NAME());
		            Element SIMILAR_RATE = doc.createElement("SIMILAR_RATE");
		            child2.appendChild(SIMILAR_RATE).setTextContent(f.getSIMILAR_RATE());
		            Element FILE_PATH = doc.createElement("FILE_PATH");
		            child2.appendChild(FILE_PATH).setTextContent(f.getFILE_PATH());
		            Element P_ID = doc.createElement("P_ID");
		            child2.appendChild(P_ID).setTextContent(f.getP_ID());
		            Element EXCLUSION = doc.createElement("EXCLUSION");
		            child2.appendChild(EXCLUSION).setTextContent(f.getEXCLUSION());
		            Element COMMENT = doc.createElement("COMMENT");
		            child2.appendChild(COMMENT).setTextContent(f.getCOMMENT());
					
				} 
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    DOMSource source = new DOMSource(doc);

			    StreamResult result = new StreamResult(tFileName);
			    transformer.transform(source, result);
		
		} catch (ParserConfigurationException e) {
				e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
				e.printStackTrace();
		}
	}
	
	/*
	 * @param ArrayList<F_TB> farr, ArrayList<P_TB> parr
	 * @return ArrayList<F_TB>
	 * 조건을 만족하는 F_TB리스트와 P_TB리스트를 파라미터로 가져와서 PID값을 비교한후  일치할경우 LICENSEID를 FTB객체 COMMENT에 셋팅 한다.
	 * */
	private ArrayList<F_TB> SetComment(ArrayList<F_TB> farr, ArrayList<P_TB> parr) {
		for(int s = 0 ; s < farr.size(); s++) {
			for(int x = 0 ; x < parr.size(); x ++) {
				if(parr.get(x).getP_ID().equals(farr.get(s).getP_ID())){
					farr.get(s).setCOMMENT(parr.get(x).getLICENSE_ID());
				}
			}
		}
		ArrayList<F_TB> arr = new ArrayList<F_TB>(); 
		
		//COMMENT값이 없을 경우 리스트에서 제거한다.
		for(int i = 0 ; i < farr.size(); i ++) {
			if(!farr.get(i).getCOMMENT().equals("")) {
				arr.add(farr.get(i));
			}
		}
		return arr;
	}
	/*
	 * @param String pFileName, ArrayList<String> arr3
	 * @return ArrayList<P_TB>
	 * F_*_TB에서 조건을 만족하는 PID가 arr3 에 담겨있다.
	 * 해당 PID를 가진 노드를 찾아 PID와 LICENSEID를  P_TB객체에 넣어준다. 
	 * */
	private ArrayList<P_TB> ReadPTB(String pFileName, ArrayList<String> arr3) {
		objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		ArrayList<P_TB> parr = new ArrayList<P_TB>();
		try {
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(pFileName);
			
			for(int i = 0 ; i < arr3.size(); i++) {
				String node = arr3.get(i);
				String xPathName = "/TABLE/ROWS/ROW[P_ID = "+node+"]/LICENSE_ID";
				NodeList pNode = (NodeList)xpath.evaluate(xPathName, doc, XPathConstants.NODESET);
				P_TB ptb = new P_TB();
				ptb.setP_ID(arr3.get(i));
				ptb.setLICENSE_ID(pNode.item(0).getTextContent());
				if(!ptb.getLICENSE_ID().equals("")) {
					parr.add(ptb);
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return parr;
	}
    
}
