import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import utilidades.UtilidadesPartidos;
import utilidades.UtilidadesPronosticos;

public class main {

    public static void main(String[] args) {
        // Verifica que se hayan pasado los argumentos necesarios
        if (args.length != 2) {
            System.out.println("Uso: java Main [archivo de partidos] [archivo de pronósticos]");
            return;
        }

        // Obtiene las rutas de los archivos de partidos y pronósticos
        String partidosPath = args[0];
        String pronosticosPath = args[1];

        // Carga los partidos y los pronósticos
        List<String> partidosString;
        List<String> pronosticosString;
        try {
            partidosString = Files.readAllLines(new File(partidosPath).toPath());
            pronosticosString = Files.readAllLines(new File(pronosticosPath).toPath());
        } catch (IOException e) {
            System.out.println("Error al cargar archivos: " + e.getMessage());
            return;
        }

        // Instancia los objetos de las clases correspondientes
        List<ResultadoPartido> partidos = utilidades.parsearLineas(partidosString);
        List<Pronostico> pronosticos = utilidades.parsearLineas(pronosticosString);

        // Verifica que haya la misma cantidad de partidos y pronósticos
        if (partidos.size() != pronosticos.size()) {
            System.out.println("La cantidad de partidos y pronósticos no coincide");
            return;
        }

        // Calcula los puntos del usuario
        int puntos = 0;
        for (int i = 0; i < partidos.size(); i++) {
            ResultadoPartido resultado = partidos.get(i);
            Pronostico pronostico = pronosticos.get(i);

            int puntosPartido = pronostico.calcularPuntos(resultado);
            puntos += puntosPartido;

            // Muestra el resultado del partido y los puntos obtenidos por el usuario
            System.out.println(resultado + " - Puntos: " + puntosPartido);
        }

        // Muestra el total de puntos obtenidos por el usuario
        System.out.println("Puntos totales: " + puntos);
        
        public int calcularPuntos(ResultadoPartido resultado) {
            if (this.getEquipo1().equals(resultado.getEquipo1()) &&
                this.getEquipo2().equals(resultado.getEquipo2()) &&
                this.getGolesEquipo1() == resultado.getGolesEquipo1() &&
                this.getGolesEquipo2() == resultado.getGolesEquipo2()) {
                return 3;
            } else if (this.getGolesEquipo1() - this.getGolesEquipo2() ==
                       resultado.getGolesEquipo1() - resultado.getGolesEquipo2()) {
                return 1;
            } else {
                return 0;
            }
        }
        
        
        
    }
}