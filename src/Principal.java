
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Convertidor convertidor = new Convertidor();

            while (true) {
                mostrarMenu();

                int opcion = obtenerOpcionValida(scanner);

                switch (opcion) {
                    case 1:
                        convertidor.convertir("USD", "ARS", scanner);
                        break;
                    case 2:
                        convertidor.convertir("ARS", "USD", scanner);
                        break;
                    case 3:
                        convertidor.convertir("USD", "BRL", scanner);
                        break;
                    case 4:
                        convertidor.convertir("BRL", "USD", scanner);
                        break;
                    case 5:
                        convertidor.convertir("USD", "COP", scanner);
                        break;
                    case 6:
                        convertidor.convertir("COP", "USD", scanner);
                        break;
                    case 7:
                        System.out.println("¡Vuelva pronto!...");
                        return;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al realizar la solicitud HTTP: " + e.getMessage());
        } finally {
            scanner.close(); // Cierra el scanner al finalizar el programa
        }
    }

    private static void mostrarMenu() {

        System.out.println("\n/******/******/******/******/******/******/");
        System.out.println("*+*+ BIENVENIDO AL CONVERSOR DE MONEDAS *+*+");
        System.out.println("/******/******/******/******/******/******/");

        System.out.println("\n1. Dólar >>> Peso argentino");
        System.out.println("2. Peso argentino >>> Dólar");
        System.out.println("3. Dólar >>> Real brasileño");
        System.out.println("4. Real brasileño >>> Dólar");
        System.out.println("5. Dólar >>> Peso colombiano");
        System.out.println("6. Peso colombiano >>> Dólar");
        System.out.println("7. Salir");
    }

    private static int obtenerOpcionValida(Scanner scanner) {
        int opcion = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("\nPor favor seleccione una opción: ");
                opcion = scanner.nextInt();
                opcionValida = true; // Si no hay excepción, la opción digitada es válida
            } catch (InputMismatchException e) {
                System.err.println("\nError: Por favor, digite un número entero.");
                scanner.next(); // Limpiar el buffer de entrada
            }
        }

        return opcion;
    }
}
