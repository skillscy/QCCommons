package exceptions;

import codes.ApplicationCodes;
import codes.HTTPCodes;
import dto.ErrorResponse;
import dto.StatusIndicator;
import loggers.CommonLogger;

public class WebServiceException extends Exception {

    private ApplicationCodes applicationCode;
    private HTTPCodes httpCode;
    private WebExceptionType exceptionType;

    public WebServiceException(ApplicationCodes appCode, HTTPCodes httpCode) {
        this.applicationCode = appCode;
        this.httpCode = httpCode;
        this.exceptionType = WebExceptionType.INTERNAL_ERROR;
        CommonLogger.error(WebServiceException.class, this.applicationCode);
    }

    public WebServiceException(ApplicationCodes appCode, HTTPCodes httpCode, WebExceptionType exceptionType) {
        this.applicationCode = appCode;
        this.httpCode = httpCode;
        this.exceptionType = exceptionType;
        CommonLogger.error(WebServiceException.class, this.applicationCode);
    }

    public WebExceptionType getExceptionType() {
        return exceptionType;
    }

    public StatusIndicator response() {
        StatusIndicator commonResponse = new StatusIndicator();
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorCode(httpCode.getHttpCode());
        errorResponse.setErrorMessage(httpCode.getHttpCodeDescription());
        commonResponse.setError(errorResponse);

        return commonResponse;
    }

}
