package com.zj.bda.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 非结构化标签
 */

@Table(name = "BDATAG_TAG_RESULT_UNSTR")
@NoArgsConstructor
@AllArgsConstructor
public class UnStrTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_ZJ_UNSTRTAG_ID.nextval from dual")
	private Integer tagId;

	/**
	 * 对象唯一标识
	 */
	private String keyValue;

	/**
	 * 对象类型（1.用户，2.设备，3.台区）
	 */
	private String tagType;

	/**
	 * 标签内容
	 */
	private String tag;

	/**
	 * 是否启用，1.启用，0.停用
	 */
	private String active = "1";

	/**
	 * 启用时间
	 */
	private Date actTime;

	/**
	 * 停用时间
	 */
	private Date inactTime;

	@Transient
	private Boolean assigned;

	@Transient
	private String formDate;
	
	//public UnStrTag(){}
	
//	public UnStrTag(Integer tagId, String keyValue, String tagType, String tag, String active, Date actTime,
//			Date inactTime) {
//		super();
//		this.tagId = tagId;
//		this.keyValue = keyValue;
//		this.tagType = tagType;
//		this.tag = tag;
//		this.active = active;
//		this.actTime = actTime;
//		this.inactTime = inactTime;
//	}


	public String getFormDate() {
		return formDate;
	}

	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}

	public Boolean getAssigned() {
		return assigned;
	}

	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	public Date getInactTime() {
		return inactTime;
	}

	public void setInactTime(Date inactTime) {
		this.inactTime = inactTime;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
}
