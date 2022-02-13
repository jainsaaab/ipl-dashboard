package com.jainsaab.ipldashboard.constant;

import java.time.ZoneId;

public interface Constants {
	ZoneId INDIA_TIMEZONE = ZoneId.of(ZoneId.SHORT_IDS.get("IST"));
	String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
}