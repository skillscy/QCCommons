package com.qc.skillscy.commons.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.Timestamp;
import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.codes.HTTPCodes;
import com.qc.skillscy.commons.dto.StatusIndicator;
import com.qc.skillscy.commons.exceptions.WebExceptionType;
import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.loggers.CommonLogger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QcUtils {

    private QcUtils() {
    }

    private static ObjectMapper objectMapper = null;
    private static String TEXT_COUNTER_NOT_LAST = "[A-Z][A-Y]";
    private static String TEXT_COUNTER_LAST = "[A-Z]Z";
    private static String TEXT_COUNTER_TO_EXPIRE = "[X-Z][A-Z]";

    private static SimpleDateFormat simpleDateFormat;

    private static SimpleDateFormat getSimpleDateFormat() {
        if (QcUtils.simpleDateFormat == null) {
            CommonLogger.info(QcUtils.class, "creating SimpleDateFormat object...");
            QcUtils.simpleDateFormat = new SimpleDateFormat(QcCommonConstants.DATE_TIME_FORMAT);
        }
        CommonLogger.info(QcUtils.class, "returning SimpleDateFormat object...");
        return QcUtils.simpleDateFormat;
    }

    public static ObjectMapper jsonConverter() {
        if (QcUtils.objectMapper == null) {
            CommonLogger.info(QcUtils.class, "Creating Jackson Json Converter object...");
            QcUtils.objectMapper = new ObjectMapper();
        }
        return QcUtils.objectMapper;
    }

    public static String generateNextArticleID(String articleID) throws WebServiceException {
        Validator.notNull(articleID);
        Validator.checkQcArticleID(articleID);

        String textCounter = articleID.substring(0, 2); // substrings [A-Z]{2}
        int numberCounter = Integer.parseInt(articleID.substring(2)); // substrings [0-9]{4}

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
                CommonLogger.warning(QcUtils.class, "Article IDs will expire soon... Please take a look on that!");
        }

        String numberValue = String.valueOf(numberCounter);
        while (numberValue.length() < 4)
            numberValue = "0".concat(numberValue);

        String nextArticleID = textCounter.concat(numberValue);
        CommonLogger.info(QcUtils.class, "Next Article ID generated from [".concat(articleID).concat("] -> [").concat(nextArticleID).concat("]"));
        return nextArticleID;
    }

    public static String generateNextClientID(String clientID) throws WebServiceException {
        Validator.notNull(clientID);
        Validator.checkQcClientID(clientID);

        String department = clientID.substring(0, 1); // substrings C
        int numberCounter = Integer.parseInt(clientID.substring(1)); // substrings [0-9]{4}

        ++numberCounter;
        if (numberCounter >= 9000) {
            CommonLogger.warning(QcUtils.class, "Client IDs will expire soon... Please take a look on that!");
        }

        String numberValue = String.valueOf(numberCounter);
        while (numberValue.length() < 4)
            numberValue = "0".concat(numberValue);

        String nextClientID = department.concat(numberValue);
        CommonLogger.info(QcUtils.class, "Next Client ID generated from [".concat(clientID).concat("] -> [").concat(nextClientID).concat("]"));
        return nextClientID;
    }

    public static String generateNextCommentsID(String commentsID) throws WebServiceException {
        Validator.notNull(commentsID);
        Validator.checkQcCommentsID(commentsID);

        int numberCounter = Integer.parseInt(commentsID); // substrings [0-9]{8}

        ++numberCounter;
        if (numberCounter >= 90000000) {
            CommonLogger.warning(QcUtils.class, "Comments IDs will expire soon... Please take a look on that!");
        }

        String nextCommentsID = String.valueOf(numberCounter);
        while (nextCommentsID.length() < 8)
            nextCommentsID = "0".concat(nextCommentsID);

        CommonLogger.info(QcUtils.class, "Next Comments ID generated from [".concat(commentsID).concat("] -> [").concat(nextCommentsID).concat("]"));
        return nextCommentsID;
    }

    public static String generateNextCompanyID(String companyID) throws WebServiceException {
        return QcUtils.generateNextCompanyID(companyID, false);
    }

    public static String generateNextCompanyID(String companyID, boolean isFilePrefixed) throws WebServiceException {
        Validator.notNull(companyID);

        String filePrefix = isFilePrefixed ? "File " : "";
        String filePrefixShort = isFilePrefixed ? "F" : "";

        if (isFilePrefixed)
            Validator.checkQcFilesID(filePrefixShort.concat(companyID));
        else
            Validator.checkQcID(companyID);

        String department = companyID.substring(0, 2); // substrings Q[1-3]
        String departmentName = filePrefix.concat("Q1".equals(department) ? "QC Employee ID" : ("Q2".equals(department) ? "QC Intern ID" : "QC Trainee ID"));

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
        CommonLogger.info(QcUtils.class, "Next ".concat(departmentName).concat(" generated from [").concat(filePrefixShort).concat(companyID).concat("] -> [").concat(filePrefixShort).concat(nextCompanyID).concat("]"));
        return nextCompanyID;
    }

    public static String generateNextFilesID(String filesID) throws WebServiceException {
        Validator.notNull(filesID);
        Validator.checkQcFilesID(filesID);
        String companyID = filesID.substring(1); // cutting off prefix 'F'
        return "F".concat(QcUtils.generateNextCompanyID(companyID, true));
    }

    public static String objectToJsonString(Object any) throws WebServiceException {
        try {
            return QcUtils.jsonConverter().writeValueAsString(any);
        } catch (IOException ex) {
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

    public static StatusIndicator defaultSuccessBody() {
        CommonLogger.info(QcUtils.class, "Wrapping up the response...");
        StatusIndicator statusIndicator = new StatusIndicator();
        statusIndicator.completed();
        return statusIndicator;
    }

    public static String convertTimeStampToString(Timestamp timestamp) {
        CommonLogger.info(QcUtils.class, "converting Timestamp [".concat(timestamp.toString()).concat("] to string..."));
        String formattedOutput = QcUtils.getSimpleDateFormat().format(timestamp.toDate());
        CommonLogger.info(QcUtils.class, "Successfully converted [".concat(timestamp.toString()).concat("] to [").concat(formattedOutput).concat("]"));
        return formattedOutput;
    }

}
