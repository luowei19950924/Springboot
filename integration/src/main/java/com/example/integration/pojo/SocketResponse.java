package com.example.integration.pojo;

/**
 * 服务器向浏览器响应数据的封装实体类
 * @author luowei
 * @date 2019/5/14 19:37
 */
public class SocketResponse {

    private String responserMessage;

    public SocketResponse(String responserMessage) {
        this.responserMessage = responserMessage;
    }

    public String getResponserMessage() {
        return responserMessage;
    }

    public void setResponserMessage(String responserMessage) {
        this.responserMessage = responserMessage;
    }
}
