import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ServicioAPI servicio = new ServicioAPI();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        System.out.println("\n*********************************");
        System.out.println("     Conversor de Divisas       ");
        System.out.println("***********************************\n");

        while (continuar) {
            try {
                // Elegir la moneda de origen
                System.out.println("Seleccione la moneda base:");
                System.out.println("1. ARS / Peso argentino");
                System.out.println("2. USD / Dólar estadounidense");
                System.out.println("3. BRL / Real brasileño");
                System.out.println("4. COP / Peso colombiano");
                System.out.println("5. CLP / Peso chileno");
                System.out.println("6. BOB / Boliviano boliviano");
                System.out.println("7. Salir");
                System.out.print("Ingrese su opción: ");
                int opcionOrigen = Integer.parseInt(scanner.nextLine());

                if (opcionOrigen == 7) {
                    System.out.println("\n¡Gracias por usar el conversor! \n");
                    continuar = false;
                    break;
                }

                String monedaOrigen = obtenerMoneda(opcionOrigen);
                if (monedaOrigen == null) {
                    System.out.println("\nOpción inválida. Intente nuevamente.\n");
                    continue;
                }

                // Elegir la moneda de destino
                System.out.println("\nIdique la moneda de destino:");
                System.out.println("1. ARS / Peso argentino");
                System.out.println("2. USD / Dólar estadounidense");
                System.out.println("3. BRL / Real brasileño");
                System.out.println("4. COP / Peso colombiano");
                System.out.println("5. CLP / Peso chileno");
                System.out.println("6. BOB / Boliviano boliviano");
                System.out.print("Ingrese su opción: ");
                int opcionDestino = Integer.parseInt(scanner.nextLine());

                String monedaDestino = obtenerMoneda(opcionDestino);
                if (monedaDestino == null) {
                    System.out.println("\nOpción inválida.\n");
                    continue;
                }

                // Pedir el monto a convertir
                System.out.print("\nIngrese el monto a convertir: ");
                double monto = Double.parseDouble(scanner.nextLine());

                if (monto <= 0) {
                    System.out.println("\nEl monto debe ser mayor que 0. Intente nuevamente.\n");
                    continue;
                }

                // Realizar la conversión
                Monedas tasas = servicio.obtenerTasasDeCambio();
                double tasaDeCambio = servicio.obtenerTasaEspecifica(tasas, monedaOrigen, monedaDestino);
                double resultado = servicio.convertirMoneda(monto, tasaDeCambio);

                // Mostrar el resultado
                System.out.printf("\nEl monto convertido de %s a %s es: %.2f%n\n", monedaOrigen, monedaDestino, resultado);

            } catch (NumberFormatException e) {
                System.out.println("\nError: Debe ingresar un número válido. Intente nuevamente.\n");
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println("\nOcurrió un error inesperado. Intente nuevamente.\n");
                e.printStackTrace();
            }
        }

        scanner.close();
    }


    private static String obtenerMoneda(int opcion) {
        return switch (opcion) {
            case 1 -> "ARS";
            case 2 -> "USD";
            case 3 -> "BRL";
            case 4 -> "COP";
            case 5 -> "CLP";
            case 6 -> "BOB";
            default -> null; // Retorna null si la opción no es válida
        };
    }
}
