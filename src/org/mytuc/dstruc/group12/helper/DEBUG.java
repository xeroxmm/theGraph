package org.mytuc.dstruc.group12.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DEBUG {
    private DEBUG() {
    }

    private static boolean useFileOutput = false;
    private static PrintWriter useFile;
    private static boolean useOutput = true;

    public static void log(String logMessage) {
        if (useOutput)
            System.out.println(logMessage);
        if(useFileOutput)
            useFile.println(logMessage);
    }

    public static void setOutput(boolean status) {
        useOutput = status;
    }
    public static void saveOutputToFile(String filePath){
        useFileOutput = true;
        try {
            useFile = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void logHeading(String headString) {
        log("\n# ".concat(getHeadingStripe( headString)).concat(" #"));
        log("|      " + headString+"      |");
        log("# ".concat(getHeadingStripe( headString)).concat(" #"));
    }
    private static String getHeadingStripe(String headingString){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < headingString.length() + 10; i++)
            s.append('-');
        return s.toString();
    }

    public static void logInline(String logMessage) {
        if (useOutput)
            System.out.print(logMessage);
        if(useFileOutput)
            useFile.print(logMessage);
    }

    public static void store() {
        if(!useFileOutput)
            return;
        System.out.println("Saving to File... ");
        useFile.close();
        System.out.println("DONE");
    }
}