package cn.wolfcode.query;

import lombok.Data;

@Data
public class JsonResoult {
    private String msg;  //
    private Object data; //失败返回的数据
    private boolean success;

    public JsonResoult(String msg, Object data, boolean success) {
        this.msg = msg;
        this.data = data;
        this.success = success;
    }

    public JsonResoult() {
    }

    public JsonResoult(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public JsonResoult(String msg) {

    }

    public static JsonResoult success(String msg ) {
        JsonResoult jsonResoult = new JsonResoult(msg);
        return jsonResoult;
    }

//    成功返回信息
    public static JsonResoult success(String msg ,Object data) {
        JsonResoult jsonResoult = new JsonResoult(msg, true);
        jsonResoult.setData(data);
        return jsonResoult;
    }

//    失败返回信息
    public static JsonResoult fail(String msg,boolean success) {
        JsonResoult jsonResoult = new JsonResoult(msg ,false );
        return jsonResoult;
    }
}
