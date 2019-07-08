import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RenamePdfFiles {
    public static void main(String[] args) throws IOException, InterruptedException {
        ManagerPDF files = new ManagerPDF();
        String path, nameFile, newNameFile;
        File file;
        Process pdfViewer = null, openPDF = null;

        try {
            pdfViewer = HandlerFiles.openProgram(files.getPDFViewer());
            System.out.println("Enter the folder path");
            path = HandlerFiles.checkDirectory(new Scanner(System.in).nextLine());
            while (pdfViewer.isAlive()) {
                System.out.println("Enter the name of the file, to exit is with \'exit\'");
                nameFile = new Scanner(System.in).nextLine();
                if (nameFile.equals("exit")) break;
                file = HandlerFiles.checkFile(path, nameFile);
                files.addFile(path, nameFile);
                openPDF = HandlerFiles.openFile(files.getCommand(nameFile));
                openPDF.waitFor(2, TimeUnit.SECONDS);
                if (file.isFile()) {
                    System.out.println("Enter the new file name, to cancel is with \'cancel\'");
                    newNameFile = new Scanner(System.in).nextLine();
                    files.getFilePDF(nameFile).setNewName(newNameFile);
                    if (newNameFile.equals("exit") || newNameFile.equals("cancel")) break;
                    if (nameFile.equals(newNameFile)) System.out.println("It has the same name");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (openPDF != null)
                openPDF.destroyForcibly();
            if (pdfViewer != null)
                pdfViewer.destroyForcibly();

            Process waitTime = HandlerFiles.openProgram("date");
            waitTime.waitFor(2, TimeUnit.SECONDS);

            for (FilePDF pdf : files.getFiles().values()) {
                if (HandlerFiles.renameFile(pdf.getPathFile(), pdf.getName(), pdf.getNewName()))
                    System.out.println("File changed: " + pdf.getPathFile() + pdf.getName() + " > " + pdf.getPathFile() + pdf.getNewName());
            }
            System.exit(0);
        }
    }
}