package pronosticodeportivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class utilidades {
    public static List<ResultadoPartido> leerResultados(String rutaArchivo) {
        List<ResultadoPartido> resultados = new ArrayList<ResultadoPartido>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea = lector.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                ResultadoPartido resultado = new ResultadoPartido(Equipo.valueOf(datos[0]), Equipo.valueOf(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]));
                resultados.add(resultado);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de resultados: " + e.getMessage());
        }
        return resultados;
    }

    public static List<Pronostico> leerPronosticos(String rutaArchivo) {
        List<Pronostico> pronosticos = new ArrayList<Pronostico>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));
            String linea = lector.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                Pronostico pronostico = new Pronostico(Equipo.valueOf(datos[0]), Equipo.valueOf(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]));
                pronosticos.add(pronostico);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de pronósticos: " + e.getMessage());
        }
        return pronosticos;
    }

    public static int calcularPuntos(ResultadoPartido resultadoReal, Pronostico pronostico) {
        int puntos = 0;
        if (resultadoReal.getGoles().equals(pronostico.getCantidad())) {
            puntos += 1;
        }
        if (resultadoReal.getGoles().equals(pronostico.getCantidad())) {
            puntos += 1;
        }
        if (resultadoReal.getGolesLocal() == pronostico.getGolesLocal()) {
            puntos += 1;
        }
        if (resultadoReal.getGolesVisitante() == pronostico.getGolesVisitante()) {
            puntos += 1;
        }
        if (resultadoReal.getGolesLocal() == pronostico.getGolesLocal() && resultadoReal.getGolesVisitante() == pronostico.getGolesVisitante()) {
            puntos += 1;
        }
        return puntos;
    }

}

