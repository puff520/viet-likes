package com.likes.common.model.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author abu
 * @Description
 * @revise
 * @time 2018年1月15日 下午4:07:59
 * @version 1.0
 */
public class CountryArea {

	private String areacode;
	private String parareacode;
	private String areaname;
	private String areafullname;
	private String cityvillagekind;
	private List<CountryArea> children = new ArrayList<CountryArea>();

	/**
	 * @return the areacode
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * @param areacode
	 *            the areacode to set
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * @return the parareacode
	 */
	public String getParareacode() {
		return parareacode;
	}

	/**
	 * @param parareacode
	 *            the parareacode to set
	 */
	public void setParareacode(String parareacode) {
		this.parareacode = parareacode;
	}

	/**
	 * @return the areaname
	 */
	public String getAreaname() {
		return areaname;
	}

	/**
	 * @param areaname
	 *            the areaname to set
	 */
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	/**
	 * @return the areafullname
	 */
	public String getAreafullname() {
		return areafullname;
	}

	/**
	 * @param areafullname
	 *            the areafullname to set
	 */
	public void setAreafullname(String areafullname) {
		this.areafullname = areafullname;
	}

	/**
	 * @return the cityvillagekind
	 */
	public String getCityvillagekind() {
		return cityvillagekind;
	}

	/**
	 * @param cityvillagekind
	 *            the cityvillagekind to set
	 */
	public void setCityvillagekind(String cityvillagekind) {
		this.cityvillagekind = cityvillagekind;
	}

	/**
	 * @return the children
	 */
	public List<CountryArea> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<CountryArea> children) {
		this.children = children;
	}

}
