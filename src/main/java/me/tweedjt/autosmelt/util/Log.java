package me.tweedjt.autosmelt.util;

import me.tweedjt.autosmelt.AutoSmelt;

public class Log {

    public enum Severity {
        INFO,
        WARN,
        URGENT,
        CRITICAL
    }

    // Send a message to the console
    public static void debugToConsole(String message) {
        if (AutoSmelt.getInstance().getSmeltData().getDebug()) {
            System.out.println(Misc.colorToString("&4[&fAutoSmelt Debug&4]&r " + message));
        }
    }
    // Send a message to the console
    public static void logToConsole(String message) {
        System.out.println(Misc.colorToString("&3[&7AutoSmelt Log&3]&r " + message));
    }
    // Send a message to the console without color
    public static void logToConsoleNoColor(String message) {
        System.out.println("[AutoSmelt Log] " + message);
    }

    /**
     * Logs an error
     * @param errorNum Error number
     * @param className Class name
     * @param functionName Function name
     * @param summary Summary to send
     * @param detail Detail to send
     * @param severity Severity to send
     * @param stackTraceElements Stack Trace to send
     */
    public static void error(
            int errorNum,
            String className,
            String functionName,
            String summary,
            String detail,
            Severity severity,
            StackTraceElement[]
                    stackTraceElements
    ) {

        String severityString = severity.toString();
        switch (severity) {
            case INFO:
                severityString = Misc.colorToString("&dInfo&r");
                break;
            case WARN:
                severityString = Misc.colorToString("&6Warning&r");
                break;
            case URGENT:
                severityString = Misc.colorToString("&eUrgent&r");
                break;
            case CRITICAL:
                severityString = Misc.colorToString("&cCritical&r");
                break;
        }


        StringBuilder stackTrace = new StringBuilder();
        ;
        if (stackTraceElements != null) {
            stackTrace.append(
                    Misc.colorToString(
                            "\n&c############################################################" +
                                    "\nStack Trace\n" +
                                    "&fPlease include this in any bug reports submitted!\n" +
                                    "&c############################################################&r\n"
                    )
            );
            for (StackTraceElement element : stackTraceElements) {
                stackTrace.append(element.toString());
                stackTrace.append("\n");
            }
            stackTrace.append(
                    Misc.colorToString(
                            "&r############################################################&f"
                    )
            );
        }

        // Output the error message
        String errorMessage = Misc.colorToString(
                "&c[AutoSmelt Error " + errorNum + "] &r" +
                "&f[" + severityString + "&f] " +
                className + "." + functionName + ": " +
                summary + "\n&7" + detail
        ) + stackTrace.toString();
        System.out.println(errorMessage);
    }
}
