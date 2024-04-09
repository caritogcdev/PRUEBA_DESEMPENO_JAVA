package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        return null;
    }

    @Override
    public List<Object> findAll() {
        //1. Crear lista para ir guardando lo que nos devuelva la base de datos
        List<Object> listaEmpresa = new ArrayList<>();

        //2. Abrimos la conexión a la BD
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en SQL
            String sql = "SELECT * FROM empresa;";

            // 4. Preparamos la sentencia SQL
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutamos la sentencia SQL (Ejecutar el ResultSet que es donde va a venir el resultado de esa consulta)
            ResultSet objResultSet = objPrepare.executeQuery(); // executeQuery() me devuelve todos los registros de la DB

            // 6. Iterar mientras haya un registro
            while (objResultSet.next()){

                // 6.1 Crear una Empresa (creamos un objeto empresa)
                Empresa objEmpresa= new Empresa();

                //6.2 Llenar el objeto con la información de la base de datos del objeto que está iterando
                // se tiene que llamar igual a como se llaman en la DB
                objEmpresa.setId(objResultSet.getInt("id"));
                objEmpresa.setNombre(objResultSet.getString("nombre"));
                objEmpresa.setSector(objResultSet.getString("sector"));
                objEmpresa.setUbicacion(objResultSet.getString("ubicacion"));
                objEmpresa.setContacto(objResultSet.getString("contacto"));

                //6.3 Agregamos la empresa a la lista. Cuando el objeto está lleno, lo agregamos a la lista
                listaEmpresa.add(objEmpresa);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.  Cerrar la conexión
        ConfigDB.closeConnection();

        return listaEmpresa;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
