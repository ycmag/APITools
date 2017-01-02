package com.itlaborer.apitools.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

public final class Base64Utils {

	// 使用jdk8自带的base64工具类编码
	public static String encodeString(String string) {
		if (StringUtils.isEmpty(string)) {
			return "";
		} else {
			try {
				return Base64.getEncoder().encodeToString(string.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	// 使用jdk8自带的base64工具类解码
	public static String decodeString(String string) {
		if (StringUtils.isEmpty(string)) {
			return "";
		} else {
			try {
				return new String(Base64.getDecoder().decode(string), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		}
	}

}