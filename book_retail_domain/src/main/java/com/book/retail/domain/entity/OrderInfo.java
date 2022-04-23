package com.book.retail.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
 * @date: 2022/4/21 下午4:03
 * @description: 订单信息表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class OrderInfo {

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
     * 用户表id
     */
    @Column
    Long userInfoId;


    /**
     * 图书表id
     */
    @Column
    Long bookId;


    /**
     * 购买数量
     */
    @Column
    Integer num;


    /**
     * 订单总价
     */
    @Column
    BigDecimal totalPrice;

    public void setBaseInfo() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
        this.setSysVersion(1L);
        this.setYn(1);
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", sysVersion=" + sysVersion +
                ", created=" + created +
                ", modified=" + modified +
                ", yn=" + yn +
                ", userInfoId=" + userInfoId +
                ", bookId=" + bookId +
                ", num=" + num +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
