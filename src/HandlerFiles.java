import sun.awt.OSInfo;

import java.io.*;

public class HandlerFiles {
    private static final String ERROR_FILE = "Invalid file";
    private static final String ERROR_DIRECTORY = "Invalid directory";
    private static final String ERROR_OS = "Invalid OS";
    private static OSInfo.OSType os = OSInfo.getOSType();

    public static OSInfo.OSType getOS() {
        return os;
    }

    public static void setOs(OSInfo.OSType system) {
        os = system;
    }

    public static Process openProgram(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }

    public static Process openFile(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }

    public static boolean isOpenFile(File file) {
        return !file.renameTo(file);
    }

    public static File checkFile(String path, String file) throws IOException {
        File returnFile = new File(path + file);
        if (returnFile.exists())
            return returnFile;
        else
            throw new IOException(ERROR_FILE);
    }

    public static String checkDirectory(String path) throws IOException {
        if (new File(path).isDirectory())
            return path;
        else
            throw new IOException(ERROR_DIRECTORY);
    }

    public static boolean renameFile(String path, String file, String newFile) throws IOException {
        File oldFile = checkFile(path, file);
        File changeFile = new File(path + newFile);
        return oldFile.renameTo(changeFile);
    }

    public static String parsePath(String path) throws IOException {
        if (os == OSInfo.OSType.MACOSX || os == OSInfo.OSType.LINUX)
            return path;
        else if (os == OSInfo.OSType.WINDOWS)
            return path.replace("\\", "\\\\");
        else
            throw new IOException(ERROR_OS);
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream osm = null;
        try {
            is = new FileInputStream(source);
            osm = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                osm.write(buffer, 0, length);
            }
        } finally {
            is.close();
            osm.close();
        }
    }

    public static Process clearWindows() throws IOException {
        if (os == OSInfo.OSType.MACOSX || os == OSInfo.OSType.LINUX)
            return Runtime.getRuntime().exec("clear");
        else if (os == OSInfo.OSType.WINDOWS)
            return Runtime.getRuntime().exec("cls");
        else
            throw new IOException(ERROR_OS);
    }
}
