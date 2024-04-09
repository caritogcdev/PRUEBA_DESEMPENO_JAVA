package controller;

import Utils.Utils;
import entity.Empresa;
import entity.Vacante;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class VacanteController {
    //Método para llamar a la instancia del modelo de vacante
    public static VacanteModel instanceModel(){
        return new VacanteModel();
    }

    // Método para crear una vacante
    public static void create(){
        // Pedimos los datos al usuario
        String titulo = JOptionPane.showInputDialog("Ingrese el título de la vacante");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción dela vacante");
        String duracion = JOptionPane.showInputDialog("Ingrese la duración de la vacante");
        String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante: ACTIVO o INACTIVO");
        String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnología requerida para la vacante");
        String clan = JOptionPane.showInputDialog("Ingrese el clan para quien va dirigida la vacante");


        // Le vamos a pedir la empresa de la vacante

        // listar la empresa
        /*
         * Utilizamos el object de array guardando en las opciones de empresa
         * y utilizamos nuestra utilidad que creamos, que convierte de lista
         * a array
         *
         * Vamos a utilizar el modelo pero de empresa
         * */

        Object[] opcionesEmpresas = Utils.listToArray(EmpresaController.instanceModel().findAll());

        Empresa objEmpresa = (Empresa) JOptionPane.showInputDialog(
                null,
                "Seleccione una empresa: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesEmpresas, // Las opciones son las de opcionesEmpresas del objeto de arriba
                opcionesEmpresas[0] // Y la opción por defecto va a ser la posición cero
        );

        /*
        Esta instancia tiene que llevar un objeto, y le pasamos los parámetros del
        constructor de Vacante y todo el objeto de empresa
         */
        instanceModel().insert(new Vacante(titulo,descripcion,duracion,estado,tecnologia,clan, objEmpresa.getId(), objEmpresa));

    }

    //Método para listar u obtener todas las vacantes
    public static void getAll(){
        /*
         * Guardar en una lista el método getAll pero le pasamos
         * el método instanceModel().findAll()
         */

        String lista = getAll(instanceModel().findAll());

        // Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, lista);
    }

    /*
     * Método que es sobreescritura de getAll, pero este devuelve un String y va
     * a pedir como parámetro una lista de tipo Object
     */
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de las Vacantes \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objVacante a Vacante ese temporal que se está recorriendo en cada iteracion
            Vacante objVacante = (Vacante) temp;
            listaString += objVacante.toString() + "\n";
        }
        return listaString;
    }

    // Método para actualizar una vacante
    public static void update(){
        /*
         * Listamos todos los atributos que queremos actualizar, pero en un
         * select, para que mejore la experiencia de usuario
         * */

        // Listar todas las vacantes
        Object[] opcionesVacante = Utils.listToArray(instanceModel().findAll());

        Vacante objVacante= (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione la vacante que desee actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesVacante, // Las opciones son las de opcionesVacante del objeto de arriba
                opcionesVacante[0] // Y la opción por defecto va a ser la posición cero
        );

        // Pedir el nuevo dato por actualizar en la vacante
        String titulo = JOptionPane.showInputDialog(null,"Ingrese el nuevo título de la vacante", objVacante.getTitulo());
        String descripcion = JOptionPane.showInputDialog(null, "Ingrese la nueva descripción dela vacante", objVacante.getDescripcion());
        String duracion = JOptionPane.showInputDialog(null,"Ingrese la nueva duración de la vacante", objVacante.getDuracion());
        String estado = JOptionPane.showInputDialog(null,"Ingrese el nuevo estado de la vacante: ACTIVO o INACTIVO", objVacante.getEstado());
        String tecnologia = JOptionPane.showInputDialog(null,"Ingrese la nueva tecnología requerida para la vacante", objVacante.getTecnologia());
        String clan = JOptionPane.showInputDialog(null,"Ingrese el nuevo clan para quien va dirigida la vacante", objVacante.getClan());

        // Listamos las Empresas
        Object[] opcionesEmpresas = Utils.listToArray(EmpresaController.instanceModel().findAll());

        // Mostramos las Empresas en un select
        Empresa objEmpresa = (Empresa) JOptionPane.showInputDialog(
                null,
                "Seleccione una empresa: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesEmpresas, // Las opciones son las de opcionesEmpresas del objeto de arriba
                opcionesEmpresas[0] // Y la opción por defecto va a ser la posición cero
        );

        /*
        * Y lo mandamos a actualizar
        * esta instancia tiene que llevar un objeto, y le pasamos los parámetros
        * del constructor de Vacante y todo el objeto de la empresa
        * */

        instanceModel().update(new Vacante(titulo,descripcion,duracion,estado,tecnologia,clan, objEmpresa.getId(), objEmpresa));

    }

    //Método para eliminar una vacante
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
        Object[] opcionesVacante = Utils.listToArray(instanceModel().findAll());

        Vacante objVacante = (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione la vacante a eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE, // Ventana de pregunta
                null, // No va a tener ninguno
                opcionesVacante, // Las opciones son las de opcionesVacante del objeto de arriba
                opcionesVacante[0] // Y la opción por defecto va a ser la posición cero
        );

        // Llamamos al modelo .delete y le pasamos la vacante que queremos eliminar
        instanceModel().delete(objVacante);

    }

}
