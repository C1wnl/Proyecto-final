package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Documento;
import modelo.util.Conexion;

public class DocumentoDAO {

    // Crear nuevo documento en BD
    public int crear(Documento doc) {
        int resultado = 0;
        String sql = "INSERT INTO documento (nombre, tipo_id, version, fecha_subida, ruta) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, doc.getNombre());
            ps.setInt(2, doc.getTipoId());
            ps.setString(3, doc.getVersion());
            ps.setDate(4, doc.getFechaSubida());
            ps.setString(5, doc.getRuta());

            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // Listar todos los documentos
    public List<Documento> listarTodos() {
        List<Documento> lista = new ArrayList<>();
        String sql = "SELECT * FROM documento";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Documento d = new Documento();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setTipoId(rs.getInt("tipo_id"));
                d.setVersion(rs.getString("version"));
                d.setFechaSubida(rs.getDate("fecha_subida"));
                d.setRuta(rs.getString("ruta"));
                lista.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Obtener un documento por su ID
    public Documento obtenerPorId(int id) {
        Documento d = null;
        String sql = "SELECT * FROM documento WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = new Documento();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setTipoId(rs.getInt("tipo_id"));
                d.setVersion(rs.getString("version"));
                d.setFechaSubida(rs.getDate("fecha_subida"));
                d.setRuta(rs.getString("ruta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    // Eliminar un documento por su ID
    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM documento WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Actualizar datos de un documento (nombre, tipo, versión)
    public int actualizar(Documento doc) {
        int resultado = 0;
        String sql = "UPDATE documento SET nombre=?, tipo_id=?, version=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getNombre());
            ps.setInt(2, doc.getTipoId());
            ps.setString(3, doc.getVersion());
            ps.setInt(4, doc.getId());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // Versionar documento (crear nueva versión asociada)
    public int versionar(Documento doc) {
        int resultado = 0;
        String sql = "INSERT INTO documento (nombre, tipo_id, version, fecha_subida, ruta) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getNombre());
            ps.setInt(2, doc.getTipoId());
            ps.setString(3, doc.getVersion()); // nueva versión
            ps.setDate(4, doc.getFechaSubida());
            ps.setString(5, doc.getRuta());    // nueva ruta del archivo
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
