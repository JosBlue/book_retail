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
 * @date: 2022/4/21 下午4:20
 * @description: 图书信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class BookInfo {

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
     * 图书名称
     */
    @Column
    String bookName;

    /**
     * 图书价格
     */
    @Column
    BigDecimal bookPrice;


    /**
     * 图书总数量（库存）
     */
    @Column
    Integer bookNum;

    public void setBaseInfo() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
        this.setSysVersion(1L);
        this.setYn(1);
    }

    @Override
    public String toString() {
        return "BookInfoEntity{" +
                "id=" + id +
                ", sysVersion=" + sysVersion +
                ", created=" + created +
                ", modified=" + modified +
                ", yn=" + yn +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookNum=" + bookNum +
                '}';
    }
}
