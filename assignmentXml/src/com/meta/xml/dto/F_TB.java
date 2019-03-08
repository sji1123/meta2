package com.meta.xml.dto;

public class F_TB {
    private String ROWID;
    private String VOLUME;
    private String FILE_NAME;
    private String RELEASE_NAME;
    private String SIMILAR_RATE;
    private String FILE_PATH;
    private String P_ID;
    private String EXCLUSION;
    private String COMMENT;
    
    public F_TB() {}

	public F_TB(String rOWID, String vOLUME, String fILE_NAME, String rELEASE_NAME, String sIMILAR_RATE, String fILE_PATH,
			String p_ID, String eXCLUSION, String cOMMENT) {
		super();
		ROWID = rOWID;
		VOLUME = vOLUME;
		FILE_NAME = fILE_NAME;
		RELEASE_NAME = rELEASE_NAME;
		SIMILAR_RATE = sIMILAR_RATE;
		FILE_PATH = fILE_PATH;
		P_ID = p_ID;
		EXCLUSION = eXCLUSION;
		COMMENT = cOMMENT;
	}

	public String getROWID() {
		return ROWID;
	}

	public void setROWID(String rOWID) {
		ROWID = rOWID;
	}

	public String getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}

	public String getRELEASE_NAME() {
		return RELEASE_NAME;
	}

	public void setRELEASE_NAME(String rELEASE_NAME) {
		RELEASE_NAME = rELEASE_NAME;
	}

	public String getSIMILAR_RATE() {
		return SIMILAR_RATE;
	}

	public void setSIMILAR_RATE(String sIMILAR_RATE) {
		SIMILAR_RATE = sIMILAR_RATE;
	}

	public String getFILE_PATH() {
		return FILE_PATH;
	}

	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}

	public String getP_ID() {
		return P_ID;
	}

	public void setP_ID(String p_ID) {
		P_ID = p_ID;
	}

	public String getEXCLUSION() {
		return EXCLUSION;
	}

	public void setEXCLUSION(String eXCLUSION) {
		EXCLUSION = eXCLUSION;
	}

	public String getCOMMENT() {
		return COMMENT;
	}

	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}

	@Override
	public String toString() {
		return "F_TB [ROWID=" + ROWID + ", VOLUME=" + VOLUME + ", FILE_NAME=" + FILE_NAME + ", RELEASE_NAME="
				+ RELEASE_NAME + ", SIMILAR_RATE=" + SIMILAR_RATE + ", FILE_PATH=" + FILE_PATH + ", P_ID=" + P_ID
				+ ", EXCLUSION=" + EXCLUSION + ", COMMENT=" + COMMENT + "]";
	}
    
}
