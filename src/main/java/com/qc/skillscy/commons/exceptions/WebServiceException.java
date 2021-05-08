package com.qc.skillscy.commons.exceptions;

import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.codes.HTTPCodes;
import com.qc.skillscy.commons.dto.ErrorResponse;
import com.qc.skillscy.commons.dto.StatusIndicator;
import com.qc.skillscy.commons.loggers.CommonLogger;

import java.text.MessageFormat;

public class WebServiceException extends Exception {

    private ApplicationCodes applicationCode;
    private HTTPCodes httpCode;
    private WebExceptionType exceptionType;

    public WebServiceException(ApplicationCodes appCode, HTTPCodes httpCode) {
        super(appCode.logMessage());
        this.applicationCode = appCode;
        this.httpCode = httpCode;
        this.exceptionType = WebExceptionType.INTERNAL_ERROR;
        CommonLogger.error(WebServiceException.class, this.applicationCode);
    }

    public WebServiceException(ApplicationCodes appCode, HTTPCodes httpCode, WebExceptionType exceptionType) {
        super(appCode.logMessage());
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
