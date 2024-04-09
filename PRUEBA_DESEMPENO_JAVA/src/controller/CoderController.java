package controller;

import Utils.Utils;
import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.util.List;

public class CoderController {

    //Método para llamar a la instancia del modelo de coder
    public static CoderModel instanceModel(){
        return new CoderModel();
    }

    // Método para crear un coder
    public static void create(){

        // Pedimos los datos al usuario
        String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre: ");
        String apellidos = JOptionPane.showInputDialog("Por favor ingrese los apellidos: ");
        String documento = JOptionPane.showInputDialog("Por favor ingrese el documento de identidad: ");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Por favor ingrese la cohorte: "));
        String cv = JOptionPane.showInputDialog("Por favor ingrese su currìculum: ");

         /*
         * Llamamos al método instanceModel() que creamos y le pasamos
         * al insert un nuevo coder que le pasamos los parámetros del
         * constructor que creamos en la entidad
         */

        instanceModel().insert(new Coder(nombre,apellidos,documento,cohorte,cv));
    }

    //Método para listar u obtener todos los coders
    public static void getAll(){
        /*
         * Guardar en una lista el método getAll pero le pasamos
         * el método instanceModel().findAll()
         * La lista que le vamos a pasar a getAll() la vamos a tomar
         * de instanciar el modelo, es decir, de instanceModel()
         * y obtener el método instanceModel()
         */

        String lista = getAll(instanceModel().findAll());

        //Le pasamos la lista que recorrió getAll que aplica sobreescritura sobre métodos estáticos
        JOptionPane.showMessageDialog(null, lista);
    }

    /*
    * Método que es sobreescritura de getAll, pero este devuelve un String y va
    * a pedir como parámetro una lista de tipo Object
     */
    public static String getAll(List<Object> list){
        String listaString = "Lista de registros de Coders \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objCoder a Coder ese temporal que se está recorriendo en cada iteracion
            Coder objCoder = (Coder) temp;
            listaString += objCoder.toString() + "\n";
        }

        return listaString;

    }

    //Método para actualizar un coder
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

        Object[] opcionesCoder = Utils.listToArray(instanceModel().findAll());

        Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                null,
                "Seleccione el coder que desea actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCoder,
                opcionesCoder[0]
        );

        //Le pedimos los nuevos datos al usuario
        coderSeleccionado.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre", coderSeleccionado.getNombre()));
        coderSeleccionado.setApellidos(JOptionPane.showInputDialog(null, "Ingrese los nuevos apellidos", coderSeleccionado.getApellidos()));
        coderSeleccionado.setDocumento(JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad", coderSeleccionado.getDocumento()));
        coderSeleccionado.setCohorte(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva cohorte", coderSeleccionado.getCohorte())));
        coderSeleccionado.setCv(JOptionPane.showInputDialog(null, "Ingrese el nuevo currículum", coderSeleccionado.getCv()));

        //Acá lo actualizamos con el modelo instanciado
        instanceModel().update(coderSeleccionado);

    }

    //Método para eliminar un coder
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

        Object[] opcionesCoder = Utils.listToArray(instanceModel().findAll());

        Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                null,
                "Seleccione el coder que desea eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCoder,
                opcionesCoder[0]
        );

        instanceModel().delete(coderSeleccionado);

    }

    // MÉTODOS ADICIONALES PARA CODER

    // Método para encontrar un coder por clan
    public static void getByClan(){
        String clan = JOptionPane.showInputDialog("Escriba el clan ");

        CoderModel objCoderModel = new CoderModel();

        String listaString = "COINCIDENCIAS \n";

        for (Coder iterador: objCoderModel.findByClan(clan)){
            listaString += iterador.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listaString);
    }

    // Método para encontrar a un coder por tecnologia

    public static void getByTechnology(){
        String tecnologia = JOptionPane.showInputDialog("Escriba la tecnologia ");

        CoderModel objCoderModel = new CoderModel();

        String listaString = "COINCIDENCIAS \n";

        for (Coder iterador: objCoderModel.findByTechnology(tecnologia)){
            listaString += iterador.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listaString);
    }

}
