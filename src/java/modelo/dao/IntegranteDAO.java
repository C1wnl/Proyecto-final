package modelo.dao;

import modelo.entidades.Integrante;
import modelo.util.Conexion;
import java.sql.*;
import java.util.*;

public class IntegranteDAO {

    // Listar todos los integrantes
    public List<Integrante> listar() {
        List<Integrante> lista = new ArrayList<>();
        String sql = "SELECT i.*, c.nombre AS cargo_nombre, j.nombre AS jefe_nombre " +
                     "FROM integrante i " +
                     "LEFT JOIN cargo c ON i.cargo_id = c.id " +
                     "LEFT JOIN integrante j ON i.jefe_id = j.id";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integrante i = new Integrante();
                i.setId(rs.getInt("id"));
                i.setNombre(rs.getString("nombre"));
                i.setCorreo(rs.getString("correo"));
                i.setTelefono(rs.getString("telefono"));
                i.setCargoId(rs.getInt("cargo_id"));
                i.setCargoNombre(rs.getString("cargo_nombre"));
                i.setJefeId(rs.getInt("jefe_id"));
                i.setJefeNombre(rs.getString("jefe_nombre"));
                lista.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Obtener un integrante por ID
    public Integrante obtenerPorId(int id) {
        Integrante i = null;
        String sql = "SELECT i.*, c.nombre AS cargo_nombre, j.nombre AS jefe_nombre " +
                     "FROM integrante i " +
                     "LEFT JOIN cargo c ON i.cargo_id = c.id " +
                     "LEFT JOIN integrante j ON i.jefe_id = j.id " +
                     "WHERE i.id = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                i = new Integrante();
                i.setId(rs.getInt("id"));
                i.setNombre(rs.getString("nombre"));
                i.setCorreo(rs.getString("correo"));
                i.setTelefono(rs.getString("telefono"));
                i.setCargoId(rs.getInt("cargo_id"));
                i.setCargoNombre(rs.getString("cargo_nombre"));
                i.setJefeId(rs.getInt("jefe_id"));
                i.setJefeNombre(rs.getString("jefe_nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    // Insertar nuevo integrante
    public void insertar(Integrante i) {
        String sql = "INSERT INTO integrante (nombre, correo, telefono, cargo_id, jefe_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, i.getNombre());
            ps.setString(2, i.getCorreo());
            ps.setString(3, i.getTelefono());
            ps.setInt(4, i.getCargoId());
            if (i.getJefeId() > 0) {
                ps.setInt(5, i.getJefeId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Actualizar integrante
    public void actualizar(Integrante i) {
        String sql = "UPDATE integrante SET nombre=?, correo=?, telefono=?, cargo_id=?, jefe_id=? WHERE id=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, i.getNombre());
            ps.setString(2, i.getCorreo());
            ps.setString(3, i.getTelefono());
            ps.setInt(4, i.getCargoId());
            if (i.getJefeId() > 0) {
                ps.setInt(5, i.getJefeId());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            ps.setInt(6, i.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar integrante
    public void eliminar(int id) {
        String sql = "DELETE FROM integrante WHERE id=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

