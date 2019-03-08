package com.meta.xml.main;

import java.util.logging.Logger;

import com.meta.xml.readwrite.XmlRead;

public class Main {


	private final static Logger LOG = Logger.getGlobal();
	
	
	public static void main(String[] args) throws Exception {
		LOG.info("시작");
		long startTime = System.currentTimeMillis();
		
		new XmlRead("T_BASEFILE_TB.xml");
		
		long endTime = System.currentTimeMillis();
		LOG.info((endTime-startTime)+"ms");
		long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		LOG.info("끝");
		LOG.info("메모리사용량"+used/1024);	
	}
}
