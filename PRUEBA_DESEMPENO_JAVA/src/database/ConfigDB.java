package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase para establecer y cerrar la conexión con la DB
public class ConfigDB {

    //Este atributo tendrá el estado de la conexión
    public static Connection objConnection = null;

    //Método para conectar la DB
    public static Connection openConnection(){

        try {
            // importamos el driver que acabamos de llamar

            Class.forName("com.mysql.cj.jdbc.Driver");

            // CONEXIÓN A LA DB
            String url = "jdbc:mysql://bamyl8a7zut2pzeotwm1-mysql.services.clever-cloud.com/bamyl8a7zut2pzeotwm1";
            String user = "ulqqzbcag9a3dpg1";
            String password = "OucmkTZlUFhgh5Z4I2Ft";

            // Establecer la conexión
            // Lo casteamos porque queremos que lo que devuelva getConnection sea de tipo Connection y por eso lo ponemos entre paréntesis
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecté correctamente a la DB");

        } catch (ClassNotFoundException error){
            System.out.println("ERROR >>> Driver no Instalado " + error.getMessage());
        } catch (SQLException error) {
            System.out.println("ERROR >>> Error al conectar con la base de datos" + error.getMessage());
        }

        return objConnection;
    }

    //Método para finalizar (cerrar) una conexión para disminuir recursos de infraestructura y por tanto hace más seguro el pull de conexiones
    public static void closeConnection(){
        try {
            //Si hay una conexión activa entonces la cierro
            if (objConnection != null) objConnection.close();
            System.out.println("Se finalizó la conexión con éxito");
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
