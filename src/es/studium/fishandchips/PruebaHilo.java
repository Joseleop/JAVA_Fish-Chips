package es.studium.fishandchips;

public class PruebaHilo {
        static Thread hilo = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Tic" + Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Error al dormir el hilo");
                }
            }
        });

    public static void main(String[] args) {
        hilo.start();
        System.out.println("main sigue");
    }
}


