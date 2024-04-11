package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1. Abrimos la conexión a la DB
        Connection objConnection = ConfigDB.openConnection();

        //2. Creamos un objeto de tipo coder, es decir, convertimos el objeto que llegó
        Coder objCoder = (Coder) obj;

        try {
            //3. Insertamos el objeto en la BD (escribimos el SQL)
            String sql = "INSERT INTO coder (nombre, apellidos, documento, cohorte, cv) VALUES (?,?,?,?,?);";

            //4. Preparamos la sentencia SQL (preparamos el statement)
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 5. Asignar valor a los ? ? ? ? ?
            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellidos());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());


            //6. Ejecutamos la sentencia SQL (ejecutamos el statement) el query
            objPrepare.execute();

            //7. Obtenemos el ID generado por la BD (Llaves generadas)
            ResultSet objResult = objPrepare.getGeneratedKeys();

            // 8. Iterar mientras haya un registro
            while (objResult.next()){
                //Podemos obtener el valor también con índices
                objCoder.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se creó el coder correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear al coder");
            e.getMessage();
        }

        //9. Cerramos la conexión a la BD
        ConfigDB.closeConnection();

        return objCoder;
    }

    @Override
    public List<Object> findAll() {

        //1. Crear lista para ir guardando lo que nos devuelva la base de datos
        List<Object> listaCoder = new ArrayList<>();

        //2. Abrimos la conexión a la BD
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 3. Escribimos el query en SQL
            String sql = "SELECT * FROM coder;";

            // 4. Preparamos la sentencia SQL
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 5. Ejecutamos la sentencia SQL (Ejecutar el ResultSet que es donde va a venir el resultado de esa consulta)
            ResultSet objResultSet = objPrepare.executeQuery(); // executeQuery() me devuelve todos los registros de la DB

            // 6. Iterar mientras haya un registro
            while (objResultSet.next()){

                // 6.1 Crear un Coder (creamos un objeto coder)
                Coder objCoder = new Coder();

                //6.2 Llenar el objeto con la información de la base de datos del objeto que está iterando
                // se tiene que llamar igual a como se llaman en la DB
                objCoder.setId(objResultSet.getInt("id"));
                objCoder.setNombre(objResultSet.getString("nombre"));
                objCoder.setApellidos(objResultSet.getString("apellidos"));
                objCoder.setDocumento(objResultSet.getString("documento"));
                objCoder.setCohorte(objResultSet.getInt("cohorte"));
                objCoder.setCv(objResultSet.getString("cv"));
                //objCoder.setCreated_at(objResultSet.getString("created_at"));

                //6.3 Agregamos el coder a la lista. Cuando el objeto está lleno, lo agregamos a la lista
                listaCoder.add(objCoder);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //7.  Cerrar la conexión
        ConfigDB.closeConnection();

        return listaCoder;
    }

    @Override
    public boolean update(Object obj) {
        // 1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 2. Convertirlo a un Coder
        Coder objCoder = (Coder) obj;

        // 3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try {
            // 4. Crear la sentencia SQL
            String sql = "UPDATE coder SET nombre = ?, apellidos = ?, documento = ?, cohorte = ?, cv = ? WHERE id = ?;";

            // 5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Asignar valor a los parámetros del Query
            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellidos());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setInt(6, objCoder.getId());

            //7. Ejecutar el query
            // Utilizamos executeUpdate() para que nos devuelva el número de filas afectadas
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Se actualizó el coder correctamente");
            }

        } catch ( Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            // 8. Cerramos la conexión
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        // 1. Convertir el objeto a la entidad
        Coder objCoder = (Coder) obj;

        // 2. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        // 3. Crear la variable de estado
        boolean isDeleted = false;

        try {
            // 4. Crear la sentencia SQL
            String sql = "DELETE FROM coder WHERE id = ? ;";

            // 5. Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // 6. Dar valor al ?
            objPrepare.setInt(1, objCoder.getId());

            // 7. Ejecutamos el Query (executeUpdate) que devuelve el número de registros (filas) afectadas
            int totalAffectedRows = objPrepare.executeUpdate();

            //Si las filas afectadas fueron mayor a cero quiere decir que si eliminó algo
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Se eliminó el coder correctamente");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // 8. Cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    // MÉTODOS ADICIONALES PARA CODER ---> Buscar por clan y buscar por tecnología (eso viene de la tabla vacante)

    // Método para buscar coder por clan ---> PREGUNTAR ¿CÓMO TRAIGO EL CLAN SI VIENE DE OTRA TABLA (TABLA vacante)?

    public List<Coder> findByClan(String clan) {

        //Creamos la lista
        List<Coder> listCoder = new ArrayList<>();

        //1. Abrimos la conexión
        Connection objConnection  = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar por el clan
        Coder objCoderByClan = null;

        try {
            // 3. Sentencia sql para seleccionar al coder por el clan

            String sql = "SELECT * FROM coder WHERE clan = ?;";

            // 4. Preparamos el statement por buenas prácticas
            //Preparamos la consulta sql que se le realizará a la DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle el valor al parámetro del Query, es decir, al signo de interrogación que tenemos inicialmente en nuestro query
            objPrepare.setString(1, "%"+clan+"%");

            //6. Ejecutar el Query

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //Si viene algo entonces es que el nombre existe
                objCoderByClan = new Coder(); //Reasignamos el valor de la variable objCoderByClan para retornarlo en el apartado inferior

                objCoderByClan.setId(objResult.getInt("id"));
                objCoderByClan.setNombre(objResult.getString("nombre"));
                objCoderByClan.setApellidos(objResult.getString("apellidos"));
                objCoderByClan.setDocumento(objResult.getString("documento"));
                objCoderByClan.setCohorte(objResult.getInt("cohorte"));
                objCoderByClan.setCv(objResult.getString("cv"));

                listCoder.add(objCoderByClan);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + error.getMessage());
        }

        // 7. Cerramos la conexión
        ConfigDB.closeConnection();

        return listCoder;

    }

    // Método para buscar coder por tecnologia ---> PREGUNTAR ¿CÓMO TRAIGO LA TECNOLOGIA SI VIENE DE OTRA TABLA (TABLA vacante)?
    //where cv like y la tecnologia que quiero buscar en el cv
    public List<Coder> findByTechnology(String tecnologia){
        //Creamos la lista
        List<Coder> listCoder = new ArrayList<>();

        //1. Abrimos la conexión
        Connection objConnection  = ConfigDB.openConnection();

        //2. Crear el coder que vamos a retornar por el nombre aca no va
        Coder objCoderByTechnology = null;

        try {
            // 3. Sentencia sql para seleccionar al coder por el clan

            String sql = "SELECT * FROM coder WHERE cv = ?;";

            // 4. Preparamos el statement por buenas prácticas
            //Preparamos la consulta sql que se le realizará a la DB
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //5. Darle el valor al parámetro del Query, es decir, al signo de interrogación que tenemos inicialmente en nuestro query
            objPrepare.setString(1, "%"+tecnologia+"%");

            //6. Ejecutar el Query

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                //Si viene algo entonces es que el nombre existe
                objCoderByTechnology = new Coder(); //Reasignamos el valor de la variable objCoderByTechnology para retornarlo en el apartado inferior

                objCoderByTechnology.setId(objResult.getInt("id"));
                objCoderByTechnology.setNombre(objResult.getString("nombre"));
                objCoderByTechnology.setApellidos(objResult.getString("apellidos"));
                objCoderByTechnology.setDocumento(objResult.getString("documento"));
                objCoderByTechnology.setCohorte(objResult.getInt("cohorte"));
                objCoderByTechnology.setCv(objResult.getString("cv"));

                listCoder.add(objCoderByTechnology);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "ERROR >>>" + error.getMessage());
        }

        // 7. Cerramos la conexión
        ConfigDB.closeConnection();

        //Retornamos la lista
        return listCoder;

    }
}
