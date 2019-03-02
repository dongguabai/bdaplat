package com.zj.bda.persistence.entity;

import lombok.*;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 非结构化标签
 */

@Table(name = "BDATAG_TAG_RESULT_UNSTR")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
/*@EqualsAndHashCode(of = {"",""})*/
public class UnStrTag implements Serializable{

	@Id
	@KeySql(sql = "select SEQ_ZJ_UNSTRTAG_ID.nextval from dual",order = ORDER.BEFORE)
	private String tagId;

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

	public UnStrTag(String keyValue, String tagType, String tag, String active, Date actTime, Date inactTime) {
		super();
		this.keyValue = keyValue;
		this.tagType = tagType;
		this.tag = tag;
		this.active = active;
		this.actTime = actTime;
		this.inactTime = inactTime;
	}
	
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


}
