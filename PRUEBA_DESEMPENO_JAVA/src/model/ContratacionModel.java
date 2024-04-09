package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Vacante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Contratacion
        Contratacion objContratacion = (Contratacion) obj;

        try {
            //3. Escribimos la sentencia SQL para crear una contratacion
            String sql = "INSERT INTO contratacion (vacante_id, coder_id, fecha_aplicacion, estado, salario) VALUES (?,?,?,?,?);";

            //4. Preparamos el statement, y le pasamos el sql como parámetro y le decimos que nos retorne las llaves generadas por la misma base de datos
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //5. Le damos valor a los ? ? ? ? ?
            objPrepare.setInt(1, objContratacion.getVacanteId());
            objPrepare.setInt(2, objContratacion.getCoderId());
            objPrepare.setDate(3, Date.valueOf(objContratacion.getFechaAplicacion()));
            objPrepare.setString(4, objContratacion.getEstado());
            objPrepare.setDouble(5, objContratacion.getSalario());

            //6. Ejecutamos el query
            objPrepare.execute();

            //7. Resultado del query
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // Mientras haya un resultado
            while (objResult.next()){
                /*
                 * Vamos a guardar en el objContratacion el id que se generó
                 * en este caso lo sacamos del objResult.getInt con la columna o el índice 1
                 */

                objContratacion.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Contratación creada correctamente");

        }catch (SQLException error){
            System.out.println("ERROR >>> " + error.getMessage());
        }

        //8. Cerramos la conexión
        ConfigDB.closeConnection();

        return objContratacion;
    }

    @Override
    public List<Object> findAll() {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos una lista de objetos
        List<Object> listaContrataciones = new ArrayList<>();

        try {
            //3. Escribimos la sentencia SQL para seleccionar las contrataciones
            String sql = "SELECT * FROM contratacion\n" +
                    "INNER JOIN vacante ON vacante.id = contratacion.vacante_id\n" +
                    "INNER JOIN coder ON coder.id = contratacion.coder_id;";

            //4. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Vamos a ejecutar el query con executeQuery() para que nos devuelva todos los registros que están en la base de datos
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //6. Crear una nueva instancia de Contratacion
                Contratacion objContratacion= new Contratacion();

                //7. Creamos una instancia del objeto Vacante
                Vacante objVacante = new Vacante();

                //8.  Creamos una instancia del objeto Coder
                Coder objCoder = new Coder();

                //9. Y lo vamos a llenar con los siguientes datos
                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setVacanteId(objResult.getInt("contratacion.vacante_id"));
                objContratacion.setCoderId(objResult.getInt("contratacion.coder_id"));
                objContratacion.setFechaAplicacion(objResult.getString("contratacion.fecha_aplicacion"));
                objContratacion.setEstado(objResult.getString("contratacion.estado"));
                objContratacion.setSalario(objResult.getDouble("contratacion.salario"));


                //10. En el ToString yo solo quiero tanto el titulo de la vacante y el nombre del coder
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objCoder.setNombre(objResult.getString("coder.nombre"));

                //11. Luego nos toca guardar tanto objVacante y objCoder
                objContratacion.setObjVacante(objVacante);
                objContratacion.setObjCoder(objCoder);

                //12. Luego guardamos en la lista a contratacion que ya tiene al coder y a la vacante
                listaContrataciones.add(objContratacion);
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //13. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return listaContrataciones;
    }

    @Override
    public boolean update(Object obj) {

        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Contratacion
        Contratacion objContratacion = (Contratacion) obj;

        //3. Declaramos la variable que nos va a indicar si una contratación fue actualizada o no
        boolean isUpdated = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar una Contratacion
            String sql = "UPDATE contratacion SET vacante_id = ?, coder_id = ?, fecha_aplicacion = ?, estado = ?, salario = ? WHERE id = ? ;";

            //5. Preparamos el statement que viene de la conexión
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor a los ? ? ? ? ? ?
            objPrepare.setInt(5, objContratacion.getVacanteId());
            objPrepare.setInt(6, objContratacion.getCoderId());
            objPrepare.setDate(1, Date.valueOf(objContratacion.getFechaAplicacion()));
            objPrepare.setString(3, objContratacion.getEstado());
            objPrepare.setDouble(4, objContratacion.getSalario());
            objPrepare.setInt(5, objContratacion.getId());


            //7. Ejecutamos verificando cuantas filas fueron afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Contratación actualizada correctamente");
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
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Castear el obj que nos llega como parámetro que es de tipo Object y hay que castearlo a Contratacion
        Contratacion objContratacion = (Contratacion) obj;

        //3. Declaramos la variable que nos va a indicar si una contratacion fue eliminada o no
        boolean isDeleted = false;

        try {
            //4. Escribimos la sentencia SQL para eliminar una contratacion
            String sql = "DELETE FROM contratacion WHERE id = ?;";

            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Le damos valor al ?
            objPrepare.setInt(1, objContratacion.getId());

            //7. Ejecutamos verificando cuantas filas fueron afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Contratación eliminada correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR >>> " + e.getMessage());
        }

        //8. Cerramos la conexión a la DB
        ConfigDB.closeConnection();

        return isDeleted;

    }
}
