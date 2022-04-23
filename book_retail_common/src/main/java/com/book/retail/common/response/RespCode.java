package com.book.retail.common.response;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午6:32
 * @description: 通用返回码
 */
public enum RespCode {
    // 通用
    SUCCESS("0000", "Success"),
    FAIL("0001", "系统繁忙,请稍后重试"),
    PARAM_ILLEGAL("2002", "参数错误"),


    // 业务相关
    BOOK_NOT_EXIST("6001", "抱歉，您购买的图书已下架"),
    BOOK_NUM_NOT_ENOUGH("6002", "抱歉，您购买的图书库存不足"),
    USER_NOT_EXIST("6003", "抱歉，您还未注册信息，请先注册您的个人信息"),
    USER_INFO_HAS_NULL("6004", "请完整填写您的个人信息"),


    NO_AUTH("0002", "没有权限"),
    NO_DATA("0003", "没有相关数据"),
    PRODUCT_BACKLOG("0004", "抱歉，暂不支持多商家销量预测"),
    FILE_TOO_BIG("0005", "上传文件应小于10M"),
    // 通用错误：2开头
    USER_NOT_LOGIN("2001", "用户未登陆"),

    ES_NO_SUCH_INDEX("2003", "there is no such index in es"),
    RPC_TIME_OUT("2004", "远程服务繁忙"),
    NULL_POINT_EXCEPTION("2005", "空指针异常"),
    REPEAT_SUBMIT_REQUEST("2006", "手速太快了，请稍后再试"),

    DATA_FORMAT_ERROR("3000", "数据格式错误"),

    // 业务相关
    ACTIVITY_NOT_EXIST("6001", "活动不存在"),
    SKU_LIST_NOT_EXIST("6002", "请重新上传清单"),
    SKU_LIST_EMPTY("6003", "清单数据为空"),
    TASK_DURING_UPDATE("6004", "节点在修改中"),
    AUDIT_PROCESSING("6005", "审核未完成"),
    AUDIT_NOT_EXIST("6006", "审核不存在"),
    AUDIT_NOT_PROCESSING("6007", "不在待审核状态"),
    ADD_FAIL("6008", "新增记录失败"),
    RESOURCE_LOCATION_EMPTY("6009", "资源位为空"),
    TOUCH_EMPTY("6010", "触达为空"),
    CONFIRM_RESOURCE_LOCATION_EMPTY("6011", "确认资源位为空"),
    ACTIVITY_FIXED("6012", "活动不允许修改"),
    CONFIRM_RESOURCE_LOCATION_DATE_ERROR("6013", "活动商家同一时间资源位冲突"),
    VENDER_NOT_EXIST("6014", "商家不存在"),
    BRAND_NOT_EXIST("6015", "品牌不存在"),
    TRADE_NOT_EXIST("6016", "业态不存在"),
    ACTIVITY_LIST_EMPTY("6017", "数据为空"),
    BACK_FILL_EMPTY("6018", "回填信息为空"),
    ACTIVITY_LIST_EMPTY_DATA("6019", "必填数据存在空值"),
    ACTIVITY_LIST_VENDER_ERROR("6020", "请保持填写商家和活动商家一致"),
    ACTIVITY_NOT_EXIST_OR_FIXED("6021", "活动不存在或不可编辑"),
    ACTIVITY_LIST_BRAND_ERROR("6022", "请保持填写品牌和活动品牌一致"),
    GMV_SHOULD_BE_NUM("6023", "GMV预估填写值需为数字！"),
    MORE_THAN_THREE_SKU("6024", "请填写三个及以上制图商品SKU"),
    ACTIVITY_TIME_NOT_SAME("6025", "活动时间不一致，请修改后上传"),
    HAVE_BLANK_DATA("6026", "必填字段不能为空，请修改后上传"),
    HAVE_REPEAT_DATA("6027", "{}存在相同的数据，请修改后上传"),
    NOT_NUMBER("6028", "中间仓价格请填写数字，请修改后上传"),
    NOT_SAME_BRAND_BUSINESS("6029", "请选择同一品牌商的商品上传"),
    NO_CALCULATE_SKU_DATA("6030", "需要计算的sku为空，请先上传相关数据"),
    ALREADY_USED("6031", "计算信息被使用，无法删除"),
    DATE_FORMAT_ERROR("6032", "开始时间或结束时间格式错误，请按照2020/01/01 19:30格式填写"),
    DATE_ERROR("6033", "清单开始时间与结束时间需在活动时间范围内"),
    VENDOR_PRICE_ERROR("6034", "商家售价请填写数字"),
    TEMPLATE_ERROR("6035", "解析失败，请下载并使用系统模版进行上传"),
    BRAND_BUSINESS_ERROR("6036", "国条码{}的商品不属于当前品牌商,请修改清单后重新上传"),
    CAN_NOT_MODIFY_BASE_INFO("6037", "目前无法修改活动基本信息"),
    COMPENSATION_PRICE_ERROR("6038", "后补至请填写数字或者百分比"),
    ATTACHMENT_DATA_ERROR("6039", "附件数据错误，请重新操作"),
    DEFAULT_COST_EXIST("6040", "品牌商已经存在默认费用类型，请重新选择品牌商"),
    DEFAULT_COST_NOT_EXIST("6041", "品牌商不存在默认费用类型，请新增后再修改"),
    ACTIVITY_DATE_ERROR("6042", "活动开始时间结束时间错误"),
    DATA_ERROR("6043", "数据格式错误，请按照对应格式填写"),
    START_TIME_BIGGER_END_TIME("6044", "活动开始时间大于结束时间"),
    CONTRACTION_NOT_EXIST("6045", "合同id查询不到相关合同信息"),
    ;


    RespCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static RespCode getByCode(String code) {
        for (RespCode item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
