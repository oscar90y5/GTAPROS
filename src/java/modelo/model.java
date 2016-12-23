package modelo;

/**
 *
 * @author miki
 */
public class model {
    // return: 1-> Jefe de Proyecto. 2-> Desarrollador. 3-> Administrador. 0-> Usuario invalido
    public static int validateUser(String user, String pass){
        //Rellenar con la consulta a la base de datos.
        if(pass.equals("J")){
            return 1;
        }
        if(pass.equals("D")){
            return 2;
        }
        if(pass.equals("A")){
            return 3;
        }
        return 0;

    }
}