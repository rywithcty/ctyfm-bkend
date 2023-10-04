package org.xf.iform.core.common;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = -1508814310440955725L;

	private Integer code;

	private String message;

	private T data;

	public enum ResponsCodeType {
		SUCCESS(0, "請求成功"),
		SYSTEM_BUSY(101, "系統繁忙"),
		REQUEST_TIME_OUT(102, "請求逾時"),
		PARAMETER_ERROR(400, "參數錯誤"),
		UNAUTHORIZED(401, "用戶認證失敗"),
		NETWORK_ERROR(404, "網路異常"),
		CUSTOM_ERROR(600, "錯誤"),

		NODATA_EXISTS(601, "查無資料"),
		FAILURE(500, "例外錯誤");

		private Integer code;
		private String message;

		private ResponsCodeType(Integer code, String message) {
			this.code = code;
			this.message = message;
		}

		public Integer getCode() {
			return this.code;
		}

		public String getMessage() {
			return this.message;
		}
	}

	public static boolean success(BaseResponse<?> response) {
		return response == null ? false : ResponsCodeType.SUCCESS.getCode().equals(response.getCode());
	}

	public BaseResponse() {
		this.code = ResponsCodeType.SUCCESS.getCode();
		this.message = ResponsCodeType.SUCCESS.getMessage();
	}

	public BaseResponse(T data) {
		this.code = ResponsCodeType.SUCCESS.getCode();
		this.message = ResponsCodeType.SUCCESS.getMessage();
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
