/*
 *  ==============================================================================
 *
 *  Copyright 2020 Persh Corporation
 *  All rights reserved.
 *
 *  This program may not be duplicated, disclosed or provided to any third parties
 *  without the prior written consent of Persh Corporation.
 *
 *  Disassembly or de-compilation of the software and/or reverse engineering of
 *  the object code are prohibited.
 *
 *  ==============================================================================
 */

package loggers;

import codes.ApplicationCodes;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class CommonLogger {

    private CommonLogger() {
    }

    private static Logger getLoggerObject(Class<?> value) {
        return Logger.getLogger(value.getName());
    }

    public static void info(Class<?> classType, String message) {
        getLoggerObject(classType).info(message);
    }

    public static void warning(Class<?> classType, String message) {
        getLoggerObject(classType).warning(message);
    }

    public static void error(Class<?> classType, String message) {
        getLoggerObject(classType).severe(message);
    }

    public static void error(Class<?> classType, ApplicationCodes appResponse) {
        final String messageFormat = "{0} - {1}";
        getLoggerObject(classType).severe(MessageFormat.format(messageFormat, appResponse.getAppCode(), appResponse.getAppCodeDescription()));
    }

}
