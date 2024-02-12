package com.company;

import java.util.Scanner;

/**
 * CarRepairShop implementa una aplicación que permite dar de alta a clientes, mecánicos, vehículos y reparaciones nuevas en un taller de mecánicos
 */
public class Main {

    // Scanner
    Scanner input = new Scanner(System.in);

    // Matrices principales de la aplicación
    public String[][] clients = new String[100][2];
    public String[][] mecanics = new String[100][3];
    public String[][] vehicles = new String[100][3];
    public String[][] reparacions = new String[100][4];

    // Método principal para inicializar la aplicación
    public static void main(String[] args) {

        Main main = new Main();
        main.init();
    }

    public void init(){
        int menuItem = 0; // Inicializa la opción de la aplicación
        do {
            System.out.println("TALLER DE REPARACIÓ DE VEHICLES");
            System.out.println("[1] Donar d’alta nou client");
            System.out.println("[2] Donar d’alta nou mecànic");
            System.out.println("[3] Introduir nou vehicle");
            System.out.println("[4] Crear fitxa de nova reparació");
            System.out.println("[5] Modificar reparació");
            System.out.println("[6] Sortir");
            System.out.println("Sel·lecciona una opció: ");

            if (input.hasNextInt()){
                menuItem = input.nextInt();
                // Menú principal de la aplicación
                switch (menuItem){
                    case 1:
                        System.out.println("Has triat donar d’alta nou client....");
                        altaClient();
                        break;
                    case 2:
                        System.out.println("Has triat donar d’alta nou mecànic....");
                        altaMecanic();
                        break;
                    case 3:
                        System.out.println("Has triat introduir nou vehicle....");
                        altaVehicle();
                        break;
                    case 4:
                        System.out.println("Has triat crear fitxa de nova reparació....");
                        novaReparacio();
                        break;
                    case 5:
                        System.out.println("Has triat crear fitxa de nova reparació....");
                        modificarReparacio();
                        break;
                    case 6:
                        System.out.println("Sortint....");
                        break;
                    default:
                        System.out.println("Opció no vàlida");
                }
            } else {
                System.out.println("Opció no vàlida");
            }
            input.nextLine();
            System.out.println("");

        }while (menuItem!=6); // Límite de número de opciones

    }

    private void altaClient() {
        System.out.println("Introdueix el DNI del client:");
        String dni = input.next();

        // Valida si el format del dni és correcte i si el DNI del nou client ja existeix
        if (validaFormatDNI(dni) && !clientExisteix(dni)) {
            System.out.println("Introdueix el nom del client:");
            String nom = input.next();

            if (!nom.isEmpty()) {
                // Busca posició nova per el nou client introduit
                int pos = 0;
                while (pos < clients.length && clients[pos][0] != null) {
                    pos++;
                }

                // Guardar el nuevo cliente
                clients[pos][0] = dni;
                clients[pos][1] = nom;

                System.out.println("Client donat d'alta amb èxit.");
            } else {
                System.out.println("Nom del client buit. Introdueix el nom!.");
            }
        } else {
            System.out.println("DNI invàlid o client ja existent.");
        }
    }

    private boolean validaFormatDNI(String dni) {
            // Implementa la lògica per a validar el format del DNI correcte
            boolean matched = dni.matches("\\d{8}[A-Za-z]");
            if (!matched) {
                System.out.println("Format de DNI invàlid. Ha de ser de 8 dígits seguit d'una lletra.");
            }
            return matched;
    }

    private boolean clientExisteix(String dni) {
            // Verifica si el client ja existeix en el array
            for (String[] client : clients) {
                if (client[0] != null && client[0].equals(dni)) {
                    return true;
                }
            }
            return false;
    }

    private void altaMecanic() {
        System.out.println("Introdueix el codi d'empleat del mecànic:");
        String codiMecanic = input.next();

        // Valida si el format del codi del mecànic és correcte i si el DNI del nou mecànic ja existeix
        if (validaFormatMecanic(codiMecanic) && !mecanicExisteix(codiMecanic)) {
            System.out.println("Introdueix el nom del mecànic:");
            String nom = input.next();

            if (!nom.trim().isEmpty()) {
                // Buscar una posición disponible para el nuevo mecánico
                int pos = 0;
                while (pos < mecanics.length && mecanics[pos][0] != null) {
                    pos++;
                }

                // Guardar el nuevo mecánico
                mecanics[pos][0] = codiMecanic;
                mecanics[pos][1] = nom;
                mecanics[pos][2] = "sense treball";

                System.out.println("Mecànic donat d'alta amb èxit.");
            } else {
                System.out.println("El nom del mecànic no pot ser buit.");
            }
        } else {
            System.out.println("Codi d'empleat invàlid o mecànic ja existent.");
        }
    }

    private boolean validaFormatMecanic(String codiMecanic) {
        // Implementa la lògica per a validar el format del codi del nou mecànic
        boolean matched = codiMecanic.matches("\\d{6}");
        if (!matched) {
            System.out.println("Format de codi d'empleat invàlid. Ha de ser de 6 dígits.");
        }
        return matched;
    }

    private boolean mecanicExisteix(String codiMecanic) {
        // Verifica si el nou mecànic ja existeix en el array
        for (String[] mec : mecanics) {
            if (mec[0] != null && mec[0].equals(codiMecanic)) {
                return true;
            }
        }
        return false;
    }

    private void altaVehicle() {
        System.out.println("Introdueix la matrícula del vehicle:");
        String matricula = input.next();

        // Valida si el format de la matricula del vehicle és correcte i si la matricula del nou vehicle ja existeix previament
        if (validaFormatMatricula(matricula) && !vehicleExisteix(matricula)) {
            System.out.println("Introdueix el nom del model del cotxe:");
            String model = input.next();

            System.out.println("Introdueix el DNI del propietari:");
            String dniPropietari = input.next();

            if (clientExisteix(dniPropietari)) {
                // Busca una posició disponible per el nou vehicle
                int pos = 0;
                while (pos < vehicles.length && vehicles[pos][0] != null) {
                    pos++;
                }

                // Guarda el nou vehicle (matricula, model i dni del propietari)
                vehicles[pos][0] = matricula;
                vehicles[pos][1] = model;
                vehicles[pos][2] = dniPropietari;

                System.out.println("Vehicle donat d'alta amb èxit.");
            } else {
                System.out.println("Propietari no trobat. Si us plau, dona d'alta el client si no existeix.");
            }
        } else {
            System.out.println("Matrícula invàlida o vehicle ja existent.");
        }
    }

    private boolean validaFormatMatricula(String matricula) {
        // Implementa la lògica per a validar el format de la matricula del vehicle del nou mecànic
        boolean matched = matricula.matches("\\d{4}[A-Za-z]{3}");
        if (!matched) {
            System.out.println("Format de matrícula invàlid. Ha de ser de 4 dígits seguit de 3 lletres.");
        }
        return matched;
    }

    private boolean vehicleExisteix(String matricula) {
        // Verifica si el nou vehicle ja existeix en el array
        for (String[] vehicle : vehicles) {
            if (vehicle[0] != null && vehicle[0].equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    private void novaReparacio() {
        System.out.println("Introdueix la matrícula del vehicle per a la nova reparació:");
        String matricula = input.next();

        // Valida si la matricula del vehicle existeix per fer la nova reparació
        if (vehicleExisteix(matricula)) {
            // Busca una posició disponible per el nou vehicle per la nova reparació
            int pos = 0;
            while (pos < reparacions.length && reparacions[pos][0] != null) {
                pos++;
            }

            // Obté primer mecànic disponible per fer la nova reparació
            String codiMecanic = primerMecanicDisponible();

            // Guardar la nueva reparación
            reparacions[pos][0] = matricula;
            reparacions[pos][1] = codiMecanic;
            reparacions[pos][2] = "en curs"; // La reparació s'inicialitza amb "en curs"
            actualizarEstadoMecanico(codiMecanic, "ocupat");

            System.out.println("Reparació donada d'alta amb èxit.");
        } else {
            System.out.println("Vehicle no trobat. Si us plau, introdueix un vehicle primer.");
        }
    }

    private String primerMecanicDisponible() {
        for (String[] mec : mecanics) {
            if (mec[0] != null && mec[2].equals("lliure")) {
                return mec[0];
            }
        }
        return null;
    }

    private void actualizarEstadoMecanico(String codiMecanic, String estado) {
        for (String[] mec : mecanics) {
            if (mec[0] != null && mec[0].equals(codiMecanic)) {
                mec[2] = estado;
                break;
            }
        }
    }

    private void modificarReparacio() {}

}
