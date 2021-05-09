package com.qc.skillscy.commons.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.codes.HTTPCodes;
import com.qc.skillscy.commons.dto.StatusIndicator;
import com.qc.skillscy.commons.exceptions.WebExceptionType;
import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.loggers.CommonLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QcUtils {

    private QcUtils() {
    }

    private static ObjectMapper objectMapper = null;
    private static String TEXT_COUNTER_NOT_LAST = "[A-Z][A-Y]";
    private static String TEXT_COUNTER_LAST = "[A-Z]Z";
    private static String TEXT_COUNTER_TO_EXPIRE = "[X-Z][A-Z]";

    public static ObjectMapper jsonConverter() {
        if (QcUtils.objectMapper == null) {
            CommonLogger.info(QcUtils.class, "Creating Jackson Json Converter object...");
            QcUtils.objectMapper = new ObjectMapper();
        }
        return QcUtils.objectMapper;
    }

    public static String generateNextID(String companyID) throws WebServiceException {
        Validator.notNull(companyID);
        Validator.checkQcID(companyID);

        String department = companyID.substring(0, 2); // substrings Q[1-3]
        String departmentName = "Q1".equals(department) ? "QC Employee ID" : ("Q2".equals(department) ? "QC Intern ID" : "QC Trainee ID");
        String textCounter = companyID.substring(2, 4); // substrings [A-Z]{2}
        int numberCounter = Integer.parseInt(companyID.substring(4)); // substrings [1-9][0-9]{3}

        ++numberCounter;
        if (numberCounter == 10000) {
            numberCounter = 1000;
            if (textCounter.matches(QcUtils.TEXT_COUNTER_NOT_LAST)) {
                char lastCharacter = textCounter.charAt(1);
                lastCharacter++;
                textCounter = String.valueOf(textCounter.charAt(0)).concat(String.valueOf(lastCharacter));
            } else if (textCounter.matches(QcUtils.TEXT_COUNTER_LAST)) {
                char firstCharacter = textCounter.charAt(0);
                firstCharacter++;
                textCounter = String.valueOf(firstCharacter).concat(String.valueOf('A'));
            }

            if (textCounter.matches(QcUtils.TEXT_COUNTER_TO_EXPIRE))
                CommonLogger.warning(QcUtils.class, departmentName.concat(" will expire soon... Please take a look on that!"));
        }

        String nextCompanyID = department.concat(textCounter).concat(String.valueOf(numberCounter));
        CommonLogger.info(QcUtils.class, "Next ".concat(departmentName).concat(" generated from [").concat(companyID).concat("] -> [").concat(nextCompanyID).concat("]"));
        return nextCompanyID;
    }

    public static String objectToJsonString(Object any) throws WebServiceException {
        try {
            return QcUtils.jsonConverter().writeValueAsString(any);
        } catch (IOException ex) {
            System.out.println("-->");
            CommonLogger.error(QcUtils.class, ex.getMessage());
            throw new WebServiceException(ApplicationCodes.ERROR_JACKSON_CONVERSION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
        }
    }

    public static Object jsonStringToObject(String anyJsonString, Class<?> type) throws WebServiceException {
        try {
            return QcUtils.jsonConverter().readValue(anyJsonString, type);
        } catch (IOException ex) {
            CommonLogger.error(QcUtils.class, ex.getMessage());
            throw new WebServiceException(ApplicationCodes.ERROR_JACKSON_CONVERSION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
        }
    }

    public static Map parseForFirestore(Object any) {
        return QcUtils.jsonConverter().convertValue(any, Map.class);
    }

    public static StatusIndicator defaultSuccessBody() {
        CommonLogger.info(QcUtils.class, "Wrapping up the response...");
        StatusIndicator statusIndicator = new StatusIndicator();
        statusIndicator.completed();
        return statusIndicator;
    }

}
