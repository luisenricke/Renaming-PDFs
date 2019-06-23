import sun.awt.OSInfo;

import java.io.*;

public class HandlerFiles {

    private final String ERROR_FILE = "Invalid file";
    private final String ERROR_DIRECTORY = "Invalid directory";
    private final String ERROR_OS = "Invalid OS";
    private OSInfo.OSType os;

    public HandlerFiles() {
        this.os = OSInfo.getOSType();
    }

    public OSInfo.OSType getOS() {
        return os;
    }

    public void setOs(OSInfo.OSType os) {
        this.os = os;
    }

    public Process openProgram(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }

    public Process openFile(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }

    public static boolean isOpenFile(File file) {
        return !file.renameTo(file);
    }

    public File checkFile(String path, String file) throws IOException {
        File returnFile = new File(path + file);
        if (returnFile.exists())
            return returnFile;
        else
            throw new IOException(ERROR_FILE);
    }

    public String parsePath(String path) throws IOException {
        if (this.os == OSInfo.OSType.MACOSX || this.os == OSInfo.OSType.LINUX)
            return path;
        else if (this.os == OSInfo.OSType.WINDOWS)
            return path.replace("\\", "\\\\");
        else
            throw new IOException(ERROR_OS);
    }

    public String checkDirectory(String path) throws IOException {
        if (new File(path).isDirectory())
            return path;
        else
            throw new IOException(ERROR_DIRECTORY);
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public Process clearWindows() throws IOException {
        if (this.os == OSInfo.OSType.MACOSX || this.os == OSInfo.OSType.LINUX)
            return Runtime.getRuntime().exec("clear");
        else if (this.os == OSInfo.OSType.WINDOWS)
            return Runtime.getRuntime().exec("cls");
        else
            throw new IOException(ERROR_OS);

    }
}
