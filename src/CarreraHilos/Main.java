package CarreraHilos;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Carrera carrera = new Carrera(100);
        List<Corredor> corredores = new ArrayList<>();
        boolean corredoresCorrect = false, velBaseCorrect = false, posibTurboCorrect = false,
                posibEvasionCorrect = false, sumaValida = false;
        int numCorredores = 0;
        int velocidadBase = 0;
        int posibilidadTurbo = 0;
        int posibilidadEvasion = 0;
        while (!corredoresCorrect) {
            try {
                System.out.print("Numero de corredores: ");
                String numCorredoresStr = reader.readLine();
                try {
                    numCorredores = Integer.parseInt(numCorredoresStr);
                    corredoresCorrect = true;
                } catch (NumberFormatException excepcion) {
                    System.out.println("***Formato no válido, vuelve a intentarlo.***");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        for (int i = 1; i <= numCorredores; i++) {
            try {
                corredoresCorrect = false;
                velBaseCorrect = false;
                posibTurboCorrect = false;
                posibEvasionCorrect = false;
                sumaValida = false;

                System.out.println("Datos del Corredor " + i);
                System.out.print("Símbolo: ");
                char simbolo = reader.readLine().charAt(0);
                while (!sumaValida) {
                    while (!velBaseCorrect) {
                        System.out.print("Velocidad Base (1-5): ");
                        String velBaseStr = reader.readLine();
                        try {
                            velocidadBase = Integer.parseInt(velBaseStr);
                            if (velocidadBase > 0 && velocidadBase <= 5) {
                                velBaseCorrect = true;
                            } else {
                                System.out.println("***Introduce un valor entre 1 y 5.***");
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("***Formato no válido, vuelve a intentarlo.***");
                        }
                    }

                    while (!posibTurboCorrect) {
                        System.out.print("Posibilidad de turbo (1-5): ");
                        String posTurboStr = reader.readLine();
                        try {
                            posibilidadTurbo = Integer.parseInt(posTurboStr);
                            if (posibilidadTurbo > 0 && posibilidadTurbo <= 5) {
                                posibTurboCorrect = true;
                            } else {
                                System.out.println("***Introduce un valor entre 1 y 5.***");
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("***Formato no válido, vuelve a intentarlo.***");
                        }
                    }
                    while (!posibEvasionCorrect) {
                        System.out.print("Posibilidad de evasión (1-5): ");
                        String posEvasionStr = reader.readLine();
                        try {
                            posibilidadEvasion = Integer.parseInt(posEvasionStr);
                            if (posibilidadEvasion > 0 && posibilidadEvasion <= 5) {
                                posibEvasionCorrect = true;
                            } else {
                                System.out.println("***Introduce un valor entre 1 y 5.***");
                            }

                        } catch (NumberFormatException ex) {
                            System.out.println("***Formato no válido, vuelve a intentarlo.***");
                        }
                    }
                    if (velocidadBase + posibilidadTurbo + posibilidadEvasion <= 10) {
                        sumaValida = true;
                        corredores.add(new Corredor(simbolo, velocidadBase, posibilidadTurbo, posibilidadEvasion, carrera));
                    } else {
                        velBaseCorrect = false;
                        posibTurboCorrect = false;
                        posibEvasionCorrect = false;
                        System.out.println("La suma de velocidad + turbo + evasion no puede superar los 10 puntos.");
                    }
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        carrera.setCorredores(corredores);

        try {
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (Corredor c : carrera.getCorredores()) {
            new Thread(c).start();
        }

        while (!carrera.isTerminada()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < carrera.getDistancia() + 3; i++) {
                sb.append("-");
            }
            sb.append("\n");
            for (int i = 0; i < corredores.size(); i++) {
                sb.append("|");
                
                for (int j = 0; j < corredores.get(i).getPosicion(); j++) {
                    sb.append("·");
                    
                }
                sb.append(corredores.get(i).getSimbolo());

                for (int j = 0; j < carrera.getDistancia() - corredores.get(i).getPosicion(); j++) {
                    sb.append(" ");
                }
                sb.append("|\n");
            }

            for (int i = 0; i < carrera.getDistancia() + 3; i++) {
                sb.append("-");
            }
            System.out.println(sb);

        }
        System.out.println("FIN de la carrera.");
        System.out.println("El GANADOR ha sido " + carrera.getGanador().getSimbolo() + ".");
    }

}
