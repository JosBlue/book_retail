package com.book.retail.domain.enums;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午4:00
 * @description: 用户类型枚举
 */
public enum UserTypeEnum {

    GOLD(1, "金牌会员", 3),
    SILVER(2, "银牌会员", 2),
    COPER(3, "铜牌会员", 1),
    ;

    private Integer id;


    private String desc;

    private Integer weight;

    UserTypeEnum(Integer id, String desc, Integer weight) {
        this.id = id;
        this.desc = desc;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }


    public String getDesc() {
        return desc;
    }

    public Integer getWeight() {
        return weight;
    }

    /**
     * 根据类型id获取枚举描述
     *
     * @param id 类型id
     * @return 描述信息
     */
    public static String getDescById(Integer id) {
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value.getDesc();
            }
        }
        return "未定义";
    }

    /**
     * 根据类型id获取枚举信息
     *
     * @param id 类型id
     * @return {@link UserTypeEnum}
     */
    public static UserTypeEnum getUserTypeEnumById(Integer id) {
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        return UserTypeEnum.COPER;
    }


    /**
     * 根据类型id获取枚举权重信息
     *
     * @param id 类型id
     * @return 权重
     */
    public static Integer getUserWeightEnumById(Integer id) {
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (value.getId().equals(id)) {
                return value.weight;
            }
        }
        return UserTypeEnum.COPER.getWeight();
    }
}
