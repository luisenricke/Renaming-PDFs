import sun.awt.OSInfo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ExecuteShellComand {

    public static void main(String[] args) {
        ManagerPDF files = new ManagerPDF();
        HandlerFiles env = new HandlerFiles();
        OSInfo.OSType OS = OSInfo.getOSType();

        //C:\Users\xLcKe\Downloads\a.pdf

        try {
            /*
            Process openPDF = env.openFile("C:\\Program Files (x86)\\Foxit Software\\Foxit Reader\\FoxitReader.exe C:\\Users\\xLcKe\\Downloads\\a.pdf");
            openPDF.waitFor(5, TimeUnit.SECONDS);
            openPDF = env.openFile("C:\\Program Files (x86)\\Foxit Software\\Foxit Reader\\FoxitReader.exe C:\\Users\\xLcKe\\Downloads\\b.pdf");
            openPDF.waitFor(5, TimeUnit.SECONDS);
            */

            Process pdfViewer = env.openProgram(ManagerPDF.getPDFViewer(OS));
            System.out.println("Inserte la ruta de la carpeta");
            String path = env.checkDirectory(new Scanner(System.in).nextLine());

            while (pdfViewer.isAlive()) {
                System.out.println("Inserte el nombre del archivo, para salir es con \'exit\'");
                String nameFile = new Scanner(System.in).nextLine();
                if (nameFile.equals("exit")) break;
                File file = env.checkFile(path, nameFile);
                files.addFile(file);
                Process openPDF = env.openFile(files.getCommand(file.getName()));

                /*
                System.out.println("Inserte el nuevo nombre del archivo, para cancelar es con \'cancel\'");
                String newNameFile = new Scanner(System.in).nextLine();
                File newFile = new File(path+newNameFile);
                env.openFile(files.getCommand(file.getName()));

                if (newNameFile.equals("exit")) break;
                if (nameFile.equals(newNameFile)) System.out.println("Tiene el mismo nombre");
                if (!newNameFile.equals("cancel") && file.isFile()){
                    openPDF.destroy();
                    //System.out.println(files.renameFile(path+nameFile,path+file)+ " ");
                    //newFile.createNewFile();
                    //System.out.println(files.getFile(file.getName()));

                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        } */finally {
            System.out.println("Success!");
            System.exit(0);
        }

    }
}