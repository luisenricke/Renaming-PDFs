import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExecuteShellComand {

    public static void main(String[] args) {
        ManagerPDF files = new ManagerPDF();
        HandlerFiles env = new HandlerFiles();

        /*
        C:\Users\xLcKe\Downloads\a.pdf
        C:\Users\xLcKe\Downloads\b.pdf
        C:\Users\xLcKe\Downloads\c.pdf
        */

        //files.addFile(env.requestFile());
        files.addFile(new File("C:\\Users\\xLcKe\\Downloads\\a.pdf"));
        try {

            System.out.println(files.getFile(0));
            Process process = env.openFile(files.makeCommand(env.getOs(), files.getFile(0).toString()));
            process.waitFor(1L, TimeUnit.SECONDS);

            while (env.isOpenFile(files.getFile(0))){

            }

            process.destroy();
            System.out.println("Success!");
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}