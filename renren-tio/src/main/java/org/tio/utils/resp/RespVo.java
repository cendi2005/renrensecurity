package org.tio.utils.resp;

public class RespVo implements java.io.Serializable{
    private static final long serialVersionUID = 7484770835792828268L;

    public static RespVo fail() {
        RespVo resp = new RespVo(RespResult.fail);
        return resp;
    }

    public static RespVo fail(String msg) {
        return fail().msg(msg);
    }

    /**
     * @param args
     * @author tanyaowu
     */
    public static void main(String[] args) {
        RespVo.fail().code(null).data(null).msg(null);
    }

    public static RespVo ok() {
        RespVo resp = new RespVo(RespResult.ok);
        return resp;
    }

    public static RespVo ok(Object data) {
        return ok().data(data);
    }

    /**
     * 结果：成功、失败或未知
     */
    private RespResult result;

    /**
     * 消息，一般用于显示
     */
    private String msg;

    /**
     * 业务数据，譬如分页数据，用户信息数据等
     */
    private Object data;

    /**
     * 业务编码：一般是在失败情况下会用到这个，以便告知用户失败的原因是什么
     */
    private Object code;

    /**
     *
     * @author tanyaowu
     */
    private RespVo(RespResult respCode) {
        this.result = respCode;
    }

    public RespVo code(Object code) {
        this.setCode(code);
        return this;
    }

    public RespVo data(Object data) {
        this.setData(data);
        return this;
    }

    public Object getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public RespResult getResult() {
        return result;
    }

    public boolean isOk() {
        return this.result == RespResult.ok;
    }

    public RespVo msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //	public boolean isFail() {
    //		return this.result == RespResult.fail;
    //	}

    public void setResult(RespResult result) {
        this.result = result;
    }
}
