package org.xf.iform.core.exception;

public class CustomException extends RuntimeException {
	/**
     * 自定義Exception
     */
    private static final long serialVersionUID = -2210316321702081289L;
    private ErrorCode errorCode;
    private String message;

    public CustomException (ErrorCode errorCode, String message) {
    	super();
    	this.errorCode = errorCode;
    	this.message = message;
    }

    public CustomException (String message) {
        super (message);
		this.errorCode = ErrorCode.CUSTOM_ERROR;
		this.message = message;
    }

    public CustomException (Throwable cause) {
        super (cause);
    }

    public CustomException (String message, Throwable cause) {
        super (message, cause);
    }

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomException [errorCode=" + errorCode + ", message=" + message + "]";
	}
	public enum ErrorCode {
		PARAMETER_ERROR(400, "參數錯誤"),
		CUSTOM_ERROR(600, "錯誤"),
		NODATA_EXISTS(601, "查無資料"),
		FAILURE(900, "例外錯誤");

		private Integer code;
		private String message;

		private ErrorCode(Integer code, String message) {
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
}
