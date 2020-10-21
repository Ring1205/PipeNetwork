package com.zxycloud.pipework.base;


/**
 * @author leiming
 * @date 2018/12/20.
 */
public class BaseBean {
    private String code;
    private String message;
    private int totalPages;
    private int totalRecords;

    public boolean isSuccessful() {
        return "000000".equals(code);
    }

    /**
     * 未激活
     * <p>
     * 用于Wi-Fi设备的验证，当Wi-Fi设备为未激活时，
     * 需要配置Wi-Fi，但当Wi-Fi已经已经分配时，则不
     * 进入Wi-Fi配置流程
     *
     * @return 是否是未激活，当code为“D00004”时为未激活
     */
    public boolean notActive() {
        return "D00004".equals(code);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public String getCode() {
        return code;
    }
}
