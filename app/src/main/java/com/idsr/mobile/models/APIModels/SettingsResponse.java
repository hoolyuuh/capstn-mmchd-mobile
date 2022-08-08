package com.idsr.mobile.models.APIModels;

import com.idsr.mobile.models.UserSettings;

public class SettingsResponse {
	private UserSettings userSettings;
	private SystemSettings systemSettings;

	public SettingsResponse() {
	}

	public UserSettings getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}

	public SystemSettings getSystemSettings() {
		return systemSettings;
	}

	public void setSystemSettings(SystemSettings systemSettings) {
		this.systemSettings = systemSettings;
	}

	public class SystemSettings {
		private int settingID;
		private int reportingDay;
		private int reportingMinute;

		public SystemSettings() {
		}

		public int getSettingID() {
			return settingID;
		}

		public void setSettingID(int settingID) {
			this.settingID = settingID;
		}

		public int getReportingDay() {
			return reportingDay;
		}

		public void setReportingDay(int reportingDay) {
			this.reportingDay = reportingDay;
		}

		public int getReportingMinute() {
			return reportingMinute;
		}

		public void setReportingMinute(int reportingMinute) {
			this.reportingMinute = reportingMinute;
		}
	}
}
