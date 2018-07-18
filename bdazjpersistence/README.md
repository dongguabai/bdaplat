1.insert--->  insertSelective()、insert()（主键字段可为空）
2.update--->  updateByPrimaryKeySelective()，只更新非空的值，其他的值不更新
3.select：
    3.1 selectByExample()：
    SELECT tag_id,key_value,tag_type,tag,active,act_time,inact_time FROM BDATAG_TAG_RESULT_UNSTR WHERE ( tag like ? and tag_id = ? ) or ( tag_id = ? and tag like ? ) 
