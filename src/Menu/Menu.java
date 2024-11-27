
package Menu;

import java.util.Scanner;
import excepciones.GarajeException;
import garajes.Garaje;
import garajes.RedDeGarajes;
import vehiculos.*;
import java.util.*;

public class Menu {
    private Scanner scanner;
    private RedDeGarajes redDeGarajes;

    public Menu() {
        scanner = new Scanner(System.in);
        redDeGarajes = new RedDeGarajes();
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("====== Menú Principal ======");
            System.out.println("1. Crear Garaje");
            System.out.println("2. Eliminar Garaje");
            System.out.println("3. Listar Garajes");
            System.out.println("4. Alquilar Vehículo en un Garaje");
            System.out.println("5. Retirar Vehículo de un Garaje");
            System.out.println("6. Calcular Ingresos Totales");
            System.out.println("7. Calcular Ocupación Total por Tipo de Vehículo");
            System.out.println("8. Listar Vehículos en un Garaje");
            System.out.println("9. Salir");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearGaraje();
                    break;
                case 2:
                    eliminarGaraje();
                    break;
                case 3:
                    listarGarajes();
                    break;
                case 4:
                    alquilarVehiculo();
                    break;
                case 5:
                    retirarVehiculo();
                    break;
                case 6:
                    calcularIngresosTotales();
                    break;
                case 7:
                    calcularOcupacionTotalPorTipoVehiculo();
                    break;
                case 8:
                    listarVehiculosEnGaraje();
                    break;
                case 9:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
            }
        }
    }

    private void crearGaraje() {
        System.out.print("Introduce el ID del Garaje: ");
        String id = scanner.nextLine();

        System.out.print("Introduce la ubicación del Garaje: ");
        String ubicacion = scanner.nextLine();

        System.out.print("Introduce el número de espacios del Garaje: ");
        int numEspacios = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        redDeGarajes.crearGaraje(id, ubicacion, numEspacios);
    }

    private void eliminarGaraje() {
        System.out.print("Introduce el ID del Garaje a eliminar: ");
        String id = scanner.nextLine();

        redDeGarajes.eliminarGaraje(id);
    }

    private void listarGarajes() {
        redDeGarajes.listarGarajes();
    }

    private void alquilarVehiculo() {
        System.out.print("Introduce el ID del Garaje donde deseas alquilar el vehículo: ");
        String idGaraje = scanner.nextLine();

        System.out.print("Introduce la placa del vehículo a alquilar: ");
        String placa = scanner.nextLine();

        Vehiculo vehiculo = obtenerVehiculoPorPlaca(placa);  // Aquí necesitas definir un método que devuelva un vehículo por su placa

        try {
            redDeGarajes.alquilarVehiculo(idGaraje, vehiculo);
        } catch (GarajeException e) {
            System.out.println("Error al alquilar el vehículo: " + e.getMessage());
        }
    }

    private void retirarVehiculo() {
        System.out.print("Introduce el ID del Garaje donde deseas retirar el vehículo: ");
        String idGaraje = scanner.nextLine();

        System.out.print("Introduce la matrícula del vehículo a retirar: ");
        String matricula = scanner.nextLine();

        try {
            redDeGarajes.retirarVehiculo(idGaraje, matricula);
        } catch (GarajeException e) {
            System.out.println("Error al retirar el vehículo: " + e.getMessage());
        }
    }

    private void calcularIngresosTotales() {
        double ingresosTotales = redDeGarajes.calcularIngresosTotales();
        System.out.println("Ingresos totales de la red de garajes: " + ingresosTotales);
    }

    private void calcularOcupacionTotalPorTipoVehiculo() {
        System.out.print("Introduce el tipo de vehículo (Moto, Camioneta, Auto): ");
        String tipo = scanner.nextLine();

        Class<? extends Vehiculo> tipoVehiculo = null;

        switch (tipo.toLowerCase()) {
            case "moto":
                tipoVehiculo = Moto.class;
                break;
            case "camioneta":
                tipoVehiculo = Camioneta.class;
                break;
            case "auto":
                tipoVehiculo = Auto.class;
                break;
            default:
                System.out.println("Tipo de vehículo no válido.");
                return;
        }

        int ocupacionTotal = redDeGarajes.calcularOcupacionTotalPorTipoVehiculo(tipoVehiculo);
        System.out.println("Ocupación total de " + tipo + "s en la red de garajes: " + ocupacionTotal);
    }

    private void listarVehiculosEnGaraje() {
        System.out.print("Introduce el ID del Garaje para listar los vehículos: ");
        String idGaraje = scanner.nextLine();

        redDeGarajes.listarVehiculosEnGaraje(idGaraje);
    }

    private Vehiculo obtenerVehiculoPorPlaca(String placa) {
        // Esta función debería buscar un vehículo en la red de garajes por su placa
        // Aquí necesitas realizar una búsqueda en los garajes
        for (Garaje garaje : redDeGarajes.getGarajes()) {
            for (Vehiculo vehiculo : garaje.getVehiculos()) {
                if (vehiculo.getPlaca().equals(placa)) {
                    return vehiculo;
                }
            }
        }
        System.out.println("Vehículo no encontrado.");
        return null;
    }
     public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}