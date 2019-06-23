public class ThreadRunner implements Runnable  {

    private String name;
    Thread thread;
    FilePDF pdf;

    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    public ThreadRunner(String name, FilePDF pdf) {
        this.name = name;
        this.pdf = pdf;
    }

    public void start() {
        System.out.println("Starting " + name);
        if (thread == null) {
            thread = new Thread(this, name);
            thread.start();
        }
    }

    @Override
    synchronized public void run() {
        while (keepRunning()){
            if(pdf.isOpen()){

            }else{
                System.out.println("Se termino el ciclo de "+pdf.getFile().getName());
                break;
            }
            //System.out.println("Still open "+ pdf.getFile().getName());
        }
    }
}
