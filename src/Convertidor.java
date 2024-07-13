import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Convertidor {
    private static final String API_KEY = "d7b985cb45e12ffd1fdc8515";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public void convertir(String monedaBase, String monedaObjetivo, Scanner scanner) throws IOException {
        double cantidad = 0;

        // Manejar excepción si se ingresa una letra en lugar de un número
        try {
            System.out.print("\nIngrese la cantidad en " + monedaBase + " a convertir: ");
            cantidad = scanner.nextDouble();
        } catch (Exception e) {
            System.err.println("Error: Debe ingresar una opción válida.");
            scanner.nextLine(); // Limpiar el buffer del scanner
            return; // Salir del método si hay error
        }

        String apiUrl = BASE_URL + API_KEY + "/latest/" + monedaBase;

        URL url = new URL(apiUrl);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        int codigoRespuesta = conexion.getResponseCode();

        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;

            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }
            lector.close();

            // Crear instancia de TasaDivisa con la respuesta JSON
            TasaDivisa respuestaAPI = new TasaDivisa(respuesta.toString());
            // Obtener el tipo de cambio
            double tipoCambio = respuestaAPI.obtenerTipoCambio(monedaObjetivo);

            double cantidadConvertida = cantidad * tipoCambio;

            // Imprimir el resultado
            System.out.println("\n*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
            System.out.printf("El valor %.2f %s corresponde al valor final de >>> %.2f %s\n", cantidad, monedaBase, cantidadConvertida, monedaObjetivo);
            System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");

        } else {
            System.err.println("\nError en la solicitud HTTP: " + codigoRespuesta);
        }

        conexion.disconnect();
    }
}
