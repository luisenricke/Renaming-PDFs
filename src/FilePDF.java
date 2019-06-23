import java.io.File;

public class FilePDF{
    private File file;
    private String command;

    public FilePDF() {
    }

    public FilePDF(File file, String command) {
        this.file = file;
        this.command = command;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}