package com.idsr.mobile.models.APIModels;

public class TCLResponse {
	private TCL TCL;

	public TCLResponse(TCLResponse.TCL TCL) {
		this.TCL = TCL;
	}

	public TCLResponse() {
	}

	public TCLResponse.TCL getTCL() {
		return TCL;
	}

	public void setTCL(TCLResponse.TCL TCL) {
		this.TCL = TCL;
	}

	public class TCL{
		private String TCLID;
		private String userID;
		private String month;
		private String year;

		public TCL(String TCLID, String userID, String month, String year) {
			this.TCLID = TCLID;
			this.userID = userID;
			this.month = month;
			this.year = year;
		}


		public String getTCLID() {
			return TCLID;
		}

		public void setTCLID(String TCLID) {
			this.TCLID = TCLID;
		}

		public String getUserID() {
			return userID;
		}

		public void setUserID(String userID) {
			this.userID = userID;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}
	}
}
