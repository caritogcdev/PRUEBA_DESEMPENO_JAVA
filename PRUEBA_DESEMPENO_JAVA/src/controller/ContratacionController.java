package controller;

import Utils.Utils;
import entity.Coder;
import entity.Contratacion;
import entity.Vacante;
import model.ContratacionModel;

import javax.swing.*;
import java.util.List;

public class ContratacionController {

    // Método para instanciar el modelo de contratación
    public static ContratacionModel instanceModel(){
        return new ContratacionModel();
    }

    //Método para crear una contratación
    public static void create() {

        String fechaAplicacion = JOptionPane.showInputDialog(null, "Ingresa la fecha de aplicación a la vacante: YYYY-MM-dd");
        String estado = JOptionPane.showInputDialog(null, "Ingresa el estado de la contratación: ACTIVO o INACTIVO");
        double salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el salario que ganará el coder"));

        /* Utilizamos la clase utils, y en este caso vamos a instanciar una instancia de lo
         * que queremos llamar instanciar
         */
        Object[] opcionesVacantes = Utils.listToArray(VacanteController.instanceModel().findAll());

        Object[] opcionesCoders = Utils.listToArray(CoderController.instanceModel().findAll());

        Vacante vacanteSeleccionada = (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione una vacante",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, //No va a tener ningún ícono
                opcionesVacantes, // Las opciones son las de opcionesVacantes del objeto de arriba
                opcionesVacantes[0] // Y la opción por defecto va a ser la posición cero
        );

        Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                null,
                "Seleccione el coder asignado a la vacante",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, //No va a tener ningún ícono
                opcionesCoders, // Las opciones son las de opcionesCoders del objeto de arriba
                opcionesCoders[0] // Y la opción por defecto va a ser la posición cero
        );

        instanceModel().insert(new Contratacion(fechaAplicacion, estado, salario, vacanteSeleccionada.getId(), coderSeleccionado.getId(), vacanteSeleccionada, coderSeleccionado));
    }

    //Método para obtener todas las contrataciones
    public static void getAll(){

        /*
         * Guardar en una lista el método getAll, pero le pasamos
         * el método instanceModel().findAll()
         * La lista que le vamos a pasar a getAll() la vamos a tomar
         * de instanciar el modelo, es decir, de instanceModel()
         * y obtener el método instanceModel()
         */

        String listaString = getAll(instanceModel().findAll());

        // Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, listaString);

    }
    //Método que es sobreescritvura de getAll para obtener la lista de los registros de contratacion
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de Contratación \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objContratacion a Contratacion ese temporal que se está recorriendo en cada iteracion
            Contratacion objContratacion= (Contratacion) temp;
            listaString += objContratacion.toString() + "\n";
        }
        return listaString;
    }

    //Método para actualizar
    public static void update(){
        /*
         * Listamos todos los atributos que queremos actualizar, pero en un
         * select, para que mejore la experiencia de usuario
         * */

        /*
         * Utilizamos la clase Utils y el método que creamos en esa clase
         * llamado listToArray() y le mandamos la lista que queremos
         * convertir. Pero esa lista la tenemos que sacar del modelo y
         * del findAll()
         *  */

        Object[] opcionesContratacion = Utils.listToArray(instanceModel().findAll());

        Contratacion contratacionSeleccionada = (Contratacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la contratación que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, //No va a tener ningún ícono
                opcionesContratacion, // Las opciones son las de opcionesContratacion del objeto de arriba
                opcionesContratacion[0] // Y la opción por defecto va a ser la posición cero
        );

        contratacionSeleccionada.setFechaAplicacion(JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de aplicación a la vacante: YYYY-MM-dd", contratacionSeleccionada.getFechaAplicacion()));
        contratacionSeleccionada.setEstado(JOptionPane.showInputDialog(null, "Ingresa el nuevo estado de la contratación: ACTIVO o INACTIVO", contratacionSeleccionada.getEstado()));
        contratacionSeleccionada.setSalario(Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el nuevo salario que ganará el coder", contratacionSeleccionada.getSalario())));

        /*
        *Utilizamos la clase utils, y en este caso vamos a instanciar una
        * instancia de lo que queremos llamar
        */

        Object[] opcionesVacantes = Utils.listToArray(VacanteController.instanceModel().findAll());

        Object[] opcionesCoders = Utils.listToArray(CoderController.instanceModel().findAll());

        Vacante vacanteSeleccionada = (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione una vacante",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, //No va a tener ningún ícono
                opcionesVacantes, // Las opciones son las de opcionesVacantes del objeto de arriba
                opcionesVacantes[0] // Y la opción por defecto va a ser la posición cero
        );

        contratacionSeleccionada.setVacanteId(vacanteSeleccionada.getId());
        contratacionSeleccionada.setObjVacante(vacanteSeleccionada);

        Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                null,
                "Seleccione el coder asignado a la vacante",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, //No va a tener ningún ícono
                opcionesCoders, // Las opciones son las de opcionesCoders del objeto de arriba
                opcionesCoders[0] // Y la opción por defecto va a ser la posición cero
        );

        contratacionSeleccionada.setCoderId(coderSeleccionado.getId());
        contratacionSeleccionada.setObjCoder(coderSeleccionado);

        /* Una vez que el usuario escoja la contratacion que quiere actualizar, llamamos al método
        instanceModel() y al método update() y le enviamos todas las contrataciones seleccionadas
        * */
        instanceModel().update(contratacionSeleccionada);
    }

    //Método para eliminar
    public static void delete(){
        /*
         * Listamos todos los atributos que queremos eliminar, pero en un
         * select, para que mejore la experiencia de usuario
         * */

        /*
         * Utilizamos la clase Utils y el método que creamos en esa clase
         * llamado listToArray() y le mandamos la lista que queremos
         * convertir. Pero esa lista la tenemos que sacar del modelo y
         * del findAll()
         *  */

        Object[] opcionesContratacion = Utils.listToArray(instanceModel().findAll());

        Contratacion contratacionSeleccionada = (Contratacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la contratación a eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, //No va a tener ningún ícono
                opcionesContratacion, // Las opciones son las de opcionesContratacion del objeto de arriba
                opcionesContratacion[0] // Y la opción por defecto va a ser la posición cero
        );

        /* Una vez que el usuario escoja la contratacion que quiere eliminar, llamamos al método
        instanceModel() y al método delete() y le enviamos todas las contrataciones seleccionadas
        * */

        instanceModel().delete(contratacionSeleccionada);

    }
}
