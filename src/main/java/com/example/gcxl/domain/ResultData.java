package com.example.gcxl.domain;

import lombok.Data;

/**
 * @author Eason
 * @ClassName:ResultData
 * @data:2022/4/12 18:46
 */
@Data
public class ResultData<E> {
    /** 状态码 */
    private Integer state;
    /** 状态描述信息 */
    private String message;
    /** 数据 */
    private E data;

    public ResultData() {
        super();
    }

    public ResultData(Integer state) {
        super();
        this.state = state;
    }

    public ResultData(ReturnCode returnCode) {
        this.state = returnCode.getCode();
        this.message = returnCode.getMessage();
    }
    public ResultData(ReturnCode returnCode,E data) {
        this.state = returnCode.getCode();
        this.message = returnCode.getMessage();
        this.data=data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /** 出现异常时调用 */
    public ResultData(Throwable e) {
        super();
        // 获取异常对象中的异常信息
        this.message = e.getMessage();
    }

    public ResultData(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }

}
