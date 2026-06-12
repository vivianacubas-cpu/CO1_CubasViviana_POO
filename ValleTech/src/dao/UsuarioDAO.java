package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean validarUsuario(String usuario, String password) {

        boolean acceso = false;
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=?";

        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password); // ✔ CORREGIDO: usa el parámetro real

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acceso = true;
            }

            rs.close();
            ps.close();
            cn.close();

        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
        }

        return acceso;
    }
}
