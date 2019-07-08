
public class FilePDF {
    private String command;
    private String newName;
    private String name;
    private String pathFile;

    public FilePDF(String pathFile, String name, String command) {
        this.pathFile = pathFile;
        this.name = name;
        this.command = command;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}