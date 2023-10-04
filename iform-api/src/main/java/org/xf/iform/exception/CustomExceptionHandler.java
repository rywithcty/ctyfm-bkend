package org.xf.iform.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xf.iform.core.exception.CustomException;
import org.xf.iform.core.common.BaseResponse;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

	@ResponseBody
	@ExceptionHandler({CustomException.class})
	protected ResponseEntity<Object> customExceptionHandler(CustomException ex) {
		BaseResponse<Object> response = new BaseResponse<Object>();
		switch (ex.getErrorCode()) {
			case PARAMETER_ERROR:
			case NODATA_EXISTS:
			case FAILURE:
				try {
					response.setCode(ex.getErrorCode().getCode());
				} catch (Exception exp) {
					response.setCode(BaseResponse.ResponsCodeType.CUSTOM_ERROR.getCode());
				}
				break;
			default:
				response.setCode(BaseResponse.ResponsCodeType.CUSTOM_ERROR.getCode());
		}


		response.setMessage(ex.getMessage());
//		log.error("自定Exception", ex);
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}


	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> notValidExceptionHandler(MethodArgumentNotValidException ex) {
		List<String> errorMsg = ex.getBindingResult().getFieldErrors().stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.toList());

		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setCode(BaseResponse.ResponsCodeType.PARAMETER_ERROR.getCode());
		response.setMessage(errorMsg.toString());

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@ResponseBody
	@ExceptionHandler({AuthenticationException.class})
	protected ResponseEntity<Object> exceptionHandler(AuthenticationException ex) {
		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setCode(BaseResponse.ResponsCodeType.UNAUTHORIZED.getCode());
		response.setMessage(BaseResponse.ResponsCodeType.UNAUTHORIZED.getMessage() +" " + ex.getMessage());
		log.error("用戶認證失敗", ex);
		//throw ex;
		return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
	}

	@ResponseBody
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<Object> ExceptionHandler(Exception ex) {
		BaseResponse<Object> response = new BaseResponse<Object>();
		response.setCode(BaseResponse.ResponsCodeType.FAILURE.getCode());
		response.setMessage("發生例外錯誤:" + ex.getMessage());
		log.error("INTERNAL_SERVER_ERROR", ex);
		
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
