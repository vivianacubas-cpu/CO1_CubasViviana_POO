package dao;

import model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public boolean insertar(Producto p) {
        String sql = "INSERT INTO productos(nombre, categoria, precio, stock) VALUES(?,?,?,?)";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
            ps.close(); cn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre=?, categoria=?, precio=?, stock=? WHERE id=?";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
            ps.close(); cn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id=?";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close(); cn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }

    public Producto buscar(int id) {
        Producto p = null;
        String sql = "SELECT * FROM productos WHERE id=?";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
            }
            rs.close(); ps.close(); cn.close();
        } catch (Exception e) {
            System.out.println("Error buscar: " + e.getMessage());
        }
        return p;
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                lista.add(p);
            }
            rs.close(); ps.close(); cn.close();
        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }
        return lista;
    }
}
