package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión a la DB
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo vacante, es decir, convertimos el objeto que llegó
        Vacante objVacante = (Vacante) obj;

        try {
            //3. Insertamos el objeto en la BD (escribimos el SQL)
            String sql = "INSERT INTO vacante (empresa_id, titulo, descripcion, duracion, estado, tecnologia, clan) VALUES (?,?,?,?,?,?,?);";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valor a los ? ? ? ? ? ? ?
            objPrepare.setInt(1, objVacante.getEmpresaId());
            objPrepare.setString(2, objVacante.getTitulo());
            objPrepare.setString(3, objVacante.getDescripcion());
            objPrepare.setString(4, objVacante.getDuracion());
            objPrepare.setString(5, objVacante.getEstado());
            objPrepare.setString(6, objVacante.getTecnologia());
            objPrepare.setString(7, objVacante.getClan());

            //6. Ejecutamos el SQL
            objPrepare.execute();

            //7. Obtenemos los resultados generados por las llaves
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //8. Iterar mientras haya un siguiente registro (por eso se coloca el next())
            while (objResult.next()){
                objVacante.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Vacante creada correctamente");

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        return objVacante;
    }

    @Override
    public List<Object> findAll() {

        //1. Lista donde se van a almacenar todos los registros de las vacantes
        List<Object> listaVacantes = new ArrayList<>();

        //2. Abrimos la conexión a la DB
        Connection objConnection = ConfigDB.openConnection();

        try {
            //3. Escribimos la sentencia SQL
            /* Pero necesitamos traer a la vacante UNIDA con la información de la empresa,
             * entonces eso se hace con un INNER JOIN
             */
            String sql = "SELECT * FROM vacante\n" +
                    "INNER JOIN empresa ON empresa.id = vacante.empresa_id;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Obtenemos los resultados de la consulta
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                // Creamos un objeto de vacante
                Vacante objVacante = new Vacante();

                // Creamos un objeto de empresa (que es la relación)
                Empresa objEmpresa = new Empresa();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setEmpresaId(objResult.getInt("vacante.empresa_id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setClan(objResult.getString("vacante.clan"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));

                /*
                * Guardar la empresa dentro de vacante
                * Entonces utilizamos sus respectivos métodos get y set que fueron creados
                * en la entidad vacante
                * */

                objVacante.setObjEmpresa(objEmpresa);

                listaVacantes.add(objVacante);
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        // Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return listaVacantes;
    }

    @Override
    public boolean update(Object obj) {

        //1. Abrimos la conexión, como no es una clase con métodos estáticos, no se coloca new
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo vacante, es decir, convertimos el objeto que llegó por parámetro a tipo vacante
        Vacante objVacante = (Vacante) obj;

        //3.  Variable que obtiene el estado de la consulta en tiempo real, si se está actualizado o no el registro
        boolean isUpdated = false;

        try {

            //4. Escribimos el sql para actualizar un registro
            String sql = "UPDATE vacante SET empresa_id = ?, titulo = ?, descripcion = ?, duracion = ?, estado = ?, tecnologia = ?, clan = ? WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor a los ? ? ? ? ? ? ?
            objPrepare.setInt(1, objVacante.getEmpresaId());
            objPrepare.setString(2, objVacante.getTitulo());
            objPrepare.setString(3, objVacante.getDescripcion());
            objPrepare.setString(4, objVacante.getDuracion());
            objPrepare.setString(5, objVacante.getEstado());
            objPrepare.setString(6, objVacante.getTecnologia());
            objPrepare.setString(7, objVacante.getClan());
            objPrepare.setInt(8, objVacante.getId());

            // 7. Ejecutamos el query (la consulta)
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro de la vacante actualizado correctamente");
            }


        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        //1. Abrimos la conexión, como no es una clase con métodos estáticos, no se coloca new
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo vacante, es decir, convertimos el objeto que llegó por parámetro a tipo vacante
        Vacante objVacante = (Vacante) obj;

        //3.  Variable que obtiene el estado de la consulta en tiempo real, si se está eliminado o no el registro
        boolean isDeleted = false;

        try{
            //4. Escribimos el sql para eliminar un registro
            String sql = "DELETE FROM vacante WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor al ?
            objPrepare.setInt(1, objVacante.getId());

            // 7. Ejecutamos el query (la consulta) para saber cuantas filas afectó
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Registro de la vacante eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isDeleted;
    }
}
