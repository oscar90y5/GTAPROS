package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase con metodos que acceden a la base de datos. Se disponen las consultas
 * utilizadas al comienzo de la clase, y después los metodos que ejecutaran
 * dichas consultas
 *
 * @author MiguelMartinezArias
 */
public class Consulta {

    private static final String INSERT_IMAGE = "INSERT INTO media (idMedia, ruta, titulo, subtitulo, fechaSubida, tipoMedia, idContenido, emailAutor,esImgPerfil) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?,0)";
    private static final String INICIAR_SESION = "SELECT email, password FROM usuario WHERE email= ? AND password= ?";

    /**
     * Comprueba que el usuario y password dados esten en la base datos, es
     * decir, que esten registrados.
     *
     * @param usuario el email del usuario
     * @param password la clave del usuario
     * @return true si el usuario se encuentra, false en caso contrario
     */
    public static boolean iniciarSesion(String usuario, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(INICIAR_SESION);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet sesion = ps.executeQuery();
            return sesion.first();
        } catch (SQLException e) {
            return false;
        } finally {
            pool.freeConnection(connection);
        }
    }

}
