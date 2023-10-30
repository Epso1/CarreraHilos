package CarreraHilos;


import java.util.ArrayList;
import java.util.List;


public class Carrera {
    private int distancia;
    private boolean terminada;
    private int contadorFinalizados;
    private List<Corredor> corredores;
    private Corredor ganador;
    
    public Carrera(int distancia){
        this.distancia = distancia;
        this.terminada = false;
        this.contadorFinalizados = 0;
        this.corredores = new ArrayList<>();
    }

    public int getDistancia() {
        return distancia;
    }

    public int getContadorFinalizados() {
        return contadorFinalizados;
    }    

    public List<Corredor> getCorredores() {
        return corredores;
    }
    

    public boolean isTerminada() {
        return terminada;
    }

    public void setGanador(Corredor ganador) {
        this.ganador = ganador;
    }

    public Corredor getGanador() {
        return ganador;
    }
    

    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }
    

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }

    public void addContadorFinalizados() {
        this.contadorFinalizados ++;
    }   
}
