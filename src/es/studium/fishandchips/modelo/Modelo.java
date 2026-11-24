package es.studium.fishandchips.modelo;

public class Modelo {
    private PezJugador pezJugador;

    private static final int POS_X_INICIAL = 247;
    private static final int POS_Y_INICIAL = 235;
    private static final int TAMANO_INICIAL = 5;

    public Modelo(){
        pezJugador = new PezJugador(POS_X_INICIAL, POS_Y_INICIAL, TAMANO_INICIAL, "/pezjugador01.png");
    }

    public PezJugador getPezJugador(){
        return this.pezJugador;
    }
}
