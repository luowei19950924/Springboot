package com.example.integration.pojo;

/**
 * 浏览器向服务端发送实体
 *
 * @author luowei
 * @date 2019/5/14 19:36
 */
public class SocketMessage {

    private String message;

    public SocketMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
