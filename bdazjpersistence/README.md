1.开启二级缓存
    mapper接口要使用@CacheNamespace
    配置文件要开启  cache-enabled: true
    
2.insert--->  insertSelective()、insert()（主键字段可为空）
3.update--->  updateByPrimaryKeySelective()，只更新非空的值，其他的值不更新
4.select：
    3.1 selectByExample()：
    SELECT tag_id,key_value,tag_type,tag,active,act_time,inact_time FROM BDATAG_TAG_RESULT_UNSTR WHERE ( tag like ? and tag_id = ? ) or ( tag_id = ? and tag like ? ) 
