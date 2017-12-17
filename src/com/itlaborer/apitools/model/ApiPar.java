package com.itlaborer.apitools.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * API参数类
 * @author liudewei[793554262@qq.com]
 * @version 1.1
 * @since 1.0
 */

public class ApiPar {
	@JSONField(ordinal = 1)
	private String name;
	@JSONField(ordinal = 2)
	private String tip;
	@JSONField(ordinal = 3)
	private String value;
	@JSONField(ordinal = 4)
	private boolean isnull;



	public ApiPar() {

	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public ApiPar(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * 
	 * @param name
	 * @param tip
	 * @param value
	 */
	public ApiPar(String name, String tip, String value) {
		this.name = name;
		this.tip = tip;
		this.value = value;
	}
	
	/**
	 * 
	 * @param name
	 * @param tip
	 * @param value
	 * @param isnull
	 */
	public ApiPar(String name, String tip, String value,boolean isnull) {
		this.name = name;
		this.tip = tip;
		this.value = value;
		this.isnull=isnull;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public boolean isIsnull() {
		return isnull;
	}

	public void setIsnull(boolean isnull) {
		this.isnull = isnull;
	}

}
