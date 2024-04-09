package controller;

import Utils.Utils;
import entity.Coder;
import entity.Contratacion;
import entity.Vacante;
import model.ContratacionModel;

import javax.swing.*;

public class ContratacionController {
    public static void create() {

        String fechaAplicacion = JOptionPane.showInputDialog(null, "Ingresa la fecha de aplicación a la vacante: YYYY-MM-dd");
        String estado = JOptionPane.showInputDialog(null, "Ingresa el estado de la contratación");
        String salario = JOptionPane.showInputDialog(null, "Ingresa el salario");


        /* Utilizamos la clase utils, y en este caso vamos a instanciar una instancia de lo
         * que queremos llamar instanciar
         */
        Object[] opcionesVacantes = Utils.listToArray(VacanteController.instanceModel().findAll());

        Object[] opcionesCoders = Utils.listToArray(CoderController.instanceModel().findAll());

        Vacante vacantyeSeleccionado = (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione un paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesVacantes,
                opcionesVacantes[0]
        );

        Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                null,
                "Seleccione el médico asignado a la cita",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCoders,
                opcionesCoders[0]
        );


    }

    // Método para instanciar el modelo de cita

    public static ContratacionModel instanceModel(){
        return new ContratacionModel();
    }

}
