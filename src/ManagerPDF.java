import sun.awt.OSInfo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ManagerPDF  {
    private static String PDF_VIEWER_LINUX = "gnome-open ";
    private static String PDF_VIEWER_MAC = "open ";
    private static String PDF_VIEWER_WINDOWS = "C:\\Program Files (x86)\\Foxit Software\\Foxit Reader\\FoxitReader.exe ";

    private final static String ERROR_PDF = "The file is not PDF";
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

    public void addFile(String path, String name) throws IOException {
        File file = new File(path + name);
        if (file.getName().contains(FILE_EXTENSION_PDF))
            this.files.put(name, new FilePDF(path, name, makeCommand(path + name)));
        else
            throw new IOException(ERROR_PDF);
    }

    public FilePDF getFilePDF(String name) {
        return files.get(name);
    }

    public String getCommand(String name) {
        return files.get(name).getCommand();
    }

    public String makeCommand(String file) throws NullPointerException {
        if (HandlerFiles.getOS() == OSInfo.OSType.WINDOWS)
            return PDF_VIEWER_WINDOWS + file;
        else if (HandlerFiles.getOS() == OSInfo.OSType.MACOSX)
            return PDF_VIEWER_MAC + file;
        else if (HandlerFiles.getOS() == OSInfo.OSType.LINUX)
            return PDF_VIEWER_LINUX + file;
        else
            throw new NullPointerException(ERROR_COMMAND);
    }

    public String getPDFViewer() throws NullPointerException {
        if (HandlerFiles.getOS() == OSInfo.OSType.WINDOWS)
            return PDF_VIEWER_WINDOWS;
        else if (HandlerFiles.getOS() == OSInfo.OSType.MACOSX)
            return PDF_VIEWER_MAC;
        else if (HandlerFiles.getOS() == OSInfo.OSType.LINUX)
            return PDF_VIEWER_LINUX;
        else
            throw new NullPointerException(ERROR_COMMAND);
    }
}
