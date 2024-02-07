package com.prajwal.todo_app.util;

public class Response<T> {
    private ResponseStatus status = ResponseStatus.SUCCESS;
    private String msg;

    private T data;

    public Response(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public Response(Exception e) {
        status = ResponseStatus.FAILURE;
        msg = e.getMessage();
    }
    public Response(Exception e, T data) {
        status = ResponseStatus.FAILURE;
        msg = e.getMessage();
        this.data=data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
