package com.book.retail.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.book.retail.domain.enums.UserTypeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import tk.mybatis.mapper.annotation.LogicDelete;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午3:56
 * @description: 用户基础信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Version
    @Column
    Long sysVersion;

    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    @Column
    LocalDateTime created;

    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    @Column
    LocalDateTime modified;

    @LogicDelete(isDeletedValue = -1, notDeletedValue = 1)
    @Column
    Integer yn;

    /**
     * 用户名称
     */
    @Column
    String userName;


    /**
     * 用户类型
     *
     * @see UserTypeEnum
     */
    @Column
    Integer userType;


    /**
     * 成员点（订单总价*会员值），单位精确到分
     */
    @Column
    BigDecimal score;


    public void setBaseInfo() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
        this.setSysVersion(1L);
        this.setYn(1);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", sysVersion=" + sysVersion +
                ", created=" + created +
                ", modified=" + modified +
                ", yn=" + yn +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", score=" + score +
                '}';
    }
}
