package com.zj.bda.common.concurrent.lock.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dongguabai
 * @date 2018-07-20 11:02
 */
@Table(name = "oracle_lock")
@Setter
@Getter
@AllArgsConstructor
public class OracleLockEntity {

    @Id
    @Column(name = "id")
    private String id;
}
