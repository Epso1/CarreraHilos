package CarreraHilos;


import java.util.Random;

public class Corredor implements Runnable {

    private char simbolo;
    private int velocidadBase;
    private int turbo;
    private int posibilidadChoque;
    private Carrera carrera;

    private int posicion = 0;
    private int meta;

    public Corredor(char simbolo, int velocidadBase, int turbo, int posibilidadChoque, Carrera carrera) {
        this.simbolo = simbolo;
        this.velocidadBase = velocidadBase;
        this.turbo = turbo;
        this.posibilidadChoque = posibilidadChoque;
        this.carrera = carrera;
        this.meta = carrera.getDistancia();
    }

    public int getPosicion() {
        return posicion;
    }

    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (posicion < meta) {
            int avanzar = calcularAvance();
            int randomChoque = new Random().nextInt(5)+1;

            if (randomChoque >= posibilidadChoque) {
                avanzar = 0;
            }
            posicion += avanzar;
            
            if (posicion >= meta) {
                posicion = meta;
            }
            if(posicion == meta){
                carrera.addContadorFinalizados();
                System.out.println(simbolo + " ha terminado la carrera.");
                if(carrera.getContadorFinalizados() == 1){
                    carrera.setGanador(this);
                } else if(carrera.getContadorFinalizados() == carrera.getCorredores().size()){
                    carrera.setTerminada(true);
                }
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

    private int calcularAvance() {
        Random random = new Random();
        int valorAleatorio = random.nextInt(5) + 1;
        return valorAleatorio <= turbo ? velocidadBase * 2 : velocidadBase;
    }
}
