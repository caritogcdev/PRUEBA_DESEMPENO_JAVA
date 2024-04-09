package controller;

import entity.Empresa;
import model.EmpresaModel;

import javax.swing.*;
import java.util.List;

public class EmpresaController {
    //Método para llamar a la instancia del modelo de empresa
    public static EmpresaModel instanceModel(){
        return new EmpresaModel();
    }

    //Método para listar u obtener todas las empresas
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
        String listaString = "Lista de registros de Empresas \n";

        // foreach de tipo Object y va a recorrer un temporal sobre la lista que nos llega como parámetro
        for (Object temp : list) {
            // Convertir objEmpresa a Empresa ese temporal que se está recorriendo en cada iteracion
            Empresa objEmpresa = (Empresa) temp;
            listaString += objEmpresa.toString() + "\n";
        }

        return listaString;

    }
}
