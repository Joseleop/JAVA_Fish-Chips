package es.studium.fishandchips;

public class PruebaHilo02 {
    static volatile boolean activo = true;

    static Thread hilo = new Thread(()->{
        while (activo){
            System.out.println("tic");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });

    public static void main(String[] args) {
        hilo.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        activo = false;
    }
}
