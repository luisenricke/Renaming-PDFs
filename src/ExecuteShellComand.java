import sun.awt.OSInfo;
import sun.misc.OSEnvironment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteShellComand {

    private static String LINUX_PDF_VIEWER = "gnome-open ";
    private static String MAC_PDF_VIEWER = "open ";
    private static String WINDOWS_PDF_VIEWER = "C:\\Program Files (x86)\\Foxit Software\\Foxit Reader\\FoxitReader.exe ";

    private static String INVALID_FILE = "Invalid file";
    private static String HOME = "C:\\Users\\xLcKe";

    public static void main(String[] args) {


        try {

            String path = System.console().readLine().replace("\\", "\\\\");
            String fileName = System.console().readLine();
            String filePath = path + fileName;
            System.out.println(filePath);
            File file = new File(filePath);
            Process process = Runtime.getRuntime().exec("echo");
            if (file.exists()) {
                if (OSInfo.getOSType() == OSInfo.OSType.MACOSX)
                    process = Runtime.getRuntime().exec(MAC_PDF_VIEWER + filePath);
                if (OSInfo.getOSType() == OSInfo.OSType.LINUX)
                    process = Runtime.getRuntime().exec(LINUX_PDF_VIEWER + filePath);
                if (OSInfo.getOSType() == OSInfo.OSType.WINDOWS)
                    process = Runtime.getRuntime().exec(WINDOWS_PDF_VIEWER + filePath);

                process.waitFor(1L, TimeUnit.SECONDS);

                while (!file.renameTo(file)) {

                }

                System.out.println("Success!");
                System.exit(0);
            } else {
                System.out.println(INVALID_FILE);
                process.destroy();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isOpenPDF(String name) {
        return false;
    }

}