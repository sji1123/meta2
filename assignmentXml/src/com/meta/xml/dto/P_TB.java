package com.meta.xml.dto;

public class P_TB {
	private String P_ID;
	private String PROJECT_NAME;
	private String LICENSE_ID;
	private String LICENSE_NAME;
	private String LICENSE_DISPLAY;
	private String SIZE;
	private String TOPIC_CD;
	private String TOPIC_NAME;
	  
	public P_TB() {}

	public P_TB(String p_ID, String pROJECT_NAME, String lICENSE_ID, String lICENSE_NAME, String lICENSE_DISPLAY,
			String sIZE, String tOPIC_CD, String tOPIC_NAME) {
		super();
		P_ID = p_ID;
		PROJECT_NAME = pROJECT_NAME;
		LICENSE_ID = lICENSE_ID;
		LICENSE_NAME = lICENSE_NAME;
		LICENSE_DISPLAY = lICENSE_DISPLAY;
		SIZE = sIZE;
		TOPIC_CD = tOPIC_CD;
		TOPIC_NAME = tOPIC_NAME;
	}
	
	public P_TB(String p_ID, String lICENSE_ID) {
		super();
		P_ID = p_ID;
		LICENSE_ID = lICENSE_ID;
	}

	public String getP_ID() {
		return P_ID;
	}

	public void setP_ID(String p_ID) {
		P_ID = p_ID;
	}

	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}

	public String getLICENSE_ID() {
		return LICENSE_ID;
	}

	public void setLICENSE_ID(String lICENSE_ID) {
		LICENSE_ID = lICENSE_ID;
	}

	public String getLICENSE_NAME() {
		return LICENSE_NAME;
	}

	public void setLICENSE_NAME(String lICENSE_NAME) {
		LICENSE_NAME = lICENSE_NAME;
	}

	public String getLICENSE_DISPLAY() {
		return LICENSE_DISPLAY;
	}

	public void setLICENSE_DISPLAY(String lICENSE_DISPLAY) {
		LICENSE_DISPLAY = lICENSE_DISPLAY;
	}

	public String getSIZE() {
		return SIZE;
	}

	public void setSIZE(String sIZE) {
		SIZE = sIZE;
	}

	public String getTOPIC_CD() {
		return TOPIC_CD;
	}

	public void setTOPIC_CD(String tOPIC_CD) {
		TOPIC_CD = tOPIC_CD;
	}

	public String getTOPIC_NAME() {
		return TOPIC_NAME;
	}

	public void setTOPIC_NAME(String tOPIC_NAME) {
		TOPIC_NAME = tOPIC_NAME;
	}

	@Override
	public String toString() {
		return "P_TB [P_ID=" + P_ID + ", PROJECT_NAME=" + PROJECT_NAME + ", LICENSE_ID=" + LICENSE_ID
				+ ", LICENSE_NAME=" + LICENSE_NAME + ", LICENSE_DISPLAY=" + LICENSE_DISPLAY + ", SIZE=" + SIZE
				+ ", TOPIC_CD=" + TOPIC_CD + ", TOPIC_NAME=" + TOPIC_NAME + "]";
	}
	
}
