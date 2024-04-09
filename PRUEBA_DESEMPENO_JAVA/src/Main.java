import controller.CoderController;
import controller.VacanteController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola mundo");
        // Prueba de conexióna la DB
        ConfigDB.openConnection();
        ConfigDB.closeConnection();

        String option = "", option2 = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. Administrar Coder.
                    2. Administrar Vacante.
                    3. Administrar Contratación.
                    4. Salir.
                    
                    Ingrese una opción:
                    """);

            switch (option){
                case "1":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Coder.
                                2. Listar los Coders.
                                3. Actualizar Coder.
                                4. Eliminar Coder.
                                5. Encontrar Coder por Clan.
                                6. Encontrar Coder por Tecnología.
                                7. Salir.
                                
                                Ingrese una opción:
                                """);

                        switch (option2){
                            case "1":
                                CoderController.create();
                                break;
                            case "2":
                                CoderController.getAll();
                                break;
                            case "3":
                                CoderController.update();
                                break;
                            case "4":
                                CoderController.delete();
                                break;
                            case "5":
                                CoderController.getByClan();
                                break;
                            case "6":
                                CoderController.getByTechnology();
                                break;
                        }

                    } while (!option2.equals("7"));
                    break;

                case "2":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Vacante.
                                2. Listar las Vacantes.
                                3. Actualizar Vacante.
                                4. Eliminar Vacante.
                                5. Salir.
                                
                                Ingrese una opción:
                                """);
                        switch (option2){
                            case "1":
                                VacanteController.create();

                                break;
                            case "2":
                                VacanteController.getAll();
                                break;
                            case "3":
                                VacanteController.update();
                                break;
                            case "4":
                                VacanteController.delete();
                                break;
                        }

                    } while (!option2.equals("5"));
                    break;

                case "3":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Contratación.
                                2. Listar las Contrataciones.
                                3. Actualizar Contratación.
                                4. Eliminar Contratación.
                                5. Salir.
                                
                                Ingrese una opción:
                                """);
                        switch (option2){
                            case "1":
                                //controller.metodo

                                break;
                            case "2":

                                break;
                            case "3":

                                break;
                            case "4":

                                break;
                        }

                    } while (!option2.equals("5"));
                    break;
            }

        } while(!option.equals("4"));
    }
}