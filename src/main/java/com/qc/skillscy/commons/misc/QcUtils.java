package com.qc.skillscy.commons.misc;

import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.loggers.CommonLogger;

public class QcUtils {

    private QcUtils() {}

    private static String TEXT_COUNTER_NOT_LAST = "[A-Z][A-Y]";
    private static String TEXT_COUNTER_LAST = "[A-Z]Z";
    private static String TEXT_COUNTER_TO_EXPIRE = "[X-Z][A-Z]";

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

}
