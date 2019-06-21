import sun.awt.OSInfo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HandlerFiles {

    private final String INVALID_OPEN = "INVALID TO OPEN";
    private OSInfo.OSType os;

    public HandlerFiles() {
        this.os = OSInfo.getOSType();
    }

    public OSInfo.OSType getOs() {
        return os;
    }

    public void setOs(OSInfo.OSType os) {
        this.os = os;
    }

    public Process openFile(String command) throws IOException {
        if (command == null)
            return null;
        return Runtime.getRuntime().exec(command);
    }

    protected File requestFile() {
        String pathFile = "";
        File returnFile = new File("");
        if (OSInfo.getOSType() == OSInfo.OSType.MACOSX || OSInfo.getOSType() == OSInfo.OSType.LINUX)
            pathFile = new Scanner(System.in).nextLine();
        if (OSInfo.getOSType() == OSInfo.OSType.WINDOWS)
            pathFile = new Scanner(System.in).nextLine().replace("\\", "\\\\");
        if (pathFile.contains(".pdf"))
            returnFile = new File(pathFile);
        return (returnFile.exists()) ? returnFile : null;
    }

    public boolean isOpenFile(File file) {
        return !file.renameTo(file);
    }
}
