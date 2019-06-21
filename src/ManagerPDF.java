import sun.awt.OSInfo;

import java.io.File;
import java.util.ArrayList;

public class ManagerPDF {

    private static String PDF_VIEWER_LINUX = "gnome-open ";
    private static String PDF_VIEWER_MAC = "open ";
    private static String PDF_VIEWER_WINDOWS = "C:\\Program Files (x86)\\Foxit Software\\Foxit Reader\\FoxitReader.exe ";

    private final String INVALID_FILE = "Invalid PDF";

    private ArrayList<File> files;

    public ManagerPDF() {
        this.files = new ArrayList<>();
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public void addFile(File file) {
        if (file != null)
            this.files.add(file);
        else
            System.out.println(INVALID_FILE);
    }

    public File getFile(String name) {
        return files.get(files.indexOf((Object) name));
    }

    public File getFile(int index) {
        return files.get(index);
    }

    public String makeCommand(OSInfo.OSType os, String file) {
        return (os == OSInfo.OSType.WINDOWS) ? PDF_VIEWER_WINDOWS + file :
                (os == OSInfo.OSType.MACOSX) ? PDF_VIEWER_MAC + file :
                        (os == OSInfo.OSType.LINUX) ? PDF_VIEWER_LINUX + file :
                                null;
    }
}
