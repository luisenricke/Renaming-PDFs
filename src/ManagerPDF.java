import sun.awt.OSInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManagerPDF {

    private static String PDF_VIEWER_LINUX = "gnome-open ";
    private static String PDF_VIEWER_MAC = "open ";
    private static String PDF_VIEWER_WINDOWS = "C:\\Program Files (x86)\\Foxit Software\\Foxit Reader\\FoxitReader.exe ";

    private final String ERROR_PDF = "The file is not PDF";
    private final static String ERROR_COMMAND = "Not command found";
    private final String FILE_EXTENSION_PDF = ".pdf";

    private HashMap<String, FilePDF> files;

    public ManagerPDF() {
        this.files = new HashMap<String, FilePDF>();
    }

    public HashMap<String, FilePDF> getFiles() {
        return files;
    }

    public void setFiles(HashMap<String, FilePDF> files) {
        this.files = files;
    }

    public void addFile(File file) throws IOException {
        if (file.getName().contains(FILE_EXTENSION_PDF))
            this.files.put(file.getName(),new FilePDF(file,makeCommand(OSInfo.getOSType(),file.getAbsolutePath())));
        else
            throw new IOException(ERROR_PDF);
    }

    public File getFile(String name) {
        return files.get(name).getFile();
    }

    public String getCommand(String name) {
        return files.get(name).getCommand();
    }

    public boolean renameFile(String file, String newFile){
        return files.get(new File(file).getName()).getFile().renameTo(new File(newFile));
    }

    public String makeCommand(OSInfo.OSType os, String file) throws NullPointerException {
        if (os == OSInfo.OSType.WINDOWS)
            return PDF_VIEWER_WINDOWS + file;
        else if (os == OSInfo.OSType.MACOSX)
            return PDF_VIEWER_MAC + file;
        else if (os == OSInfo.OSType.LINUX)
            return PDF_VIEWER_LINUX + file;
        else
            throw new NullPointerException(ERROR_COMMAND);
    }

    public static String getPDFViewer(OSInfo.OSType os) throws NullPointerException {
        if (os == OSInfo.OSType.WINDOWS)
            return PDF_VIEWER_WINDOWS;
        else if (os == OSInfo.OSType.MACOSX)
            return PDF_VIEWER_MAC;
        else if (os == OSInfo.OSType.LINUX)
            return PDF_VIEWER_LINUX;
        else
            throw new NullPointerException(ERROR_COMMAND);
    }
}
