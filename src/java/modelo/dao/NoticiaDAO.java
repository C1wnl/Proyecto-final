package modelo.dao;

import modelo.entidades.Noticia;
import modelo.util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDAO {

    public List<Noticia> listar() {
        List<Noticia> lista = new ArrayList<>();
        String sql = "SELECT n.*, c.nombre AS categoria_nombre " +
                     "FROM noticia n " +
                     "JOIN categoria c ON n.categoria_id = c.id " +
                     "ORDER BY n.fecha DESC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Noticia n = new Noticia(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("contenido"),
                    rs.getString("fecha"),
                    rs.getString("imagen"),
                    rs.getInt("categoria_id"),
                    rs.getString("autor")
                );
                n.setCategoriaNombre(rs.getString("categoria_nombre"));
                lista.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Noticia> filtrar(Integer categoriaId, String fecha) {
        List<Noticia> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT n.*, c.nombre AS categoria_nombre " +
            "FROM noticia n " +
            "JOIN categoria c ON n.categoria_id = c.id " +
            "WHERE 1=1"
        );

        if (categoriaId != null) {
            sql.append(" AND n.categoria_id = ?");
        }
        if (fecha != null) {
            sql.append(" AND DATE_FORMAT(n.fecha, '%Y-%m') = ?");
        }

        sql.append(" ORDER BY n.fecha DESC");

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int index = 1;
            if (categoriaId != null) {
                ps.setInt(index++, categoriaId);
            }
            if (fecha != null) {
                ps.setString(index++, fecha);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Noticia n = new Noticia(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("contenido"),
                    rs.getString("fecha"),
                    rs.getString("imagen"),
                    rs.getInt("categoria_id"),
                    rs.getString("autor")
                );
                n.setCategoriaNombre(rs.getString("categoria_nombre"));
                lista.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM noticia WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertar(Noticia noticia) {
        String sql = "INSERT INTO noticia (titulo, contenido, categoria_id, autor, imagen, fecha) VALUES (?, ?, ?, ?, ?, NOW())";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getContenido());
            ps.setInt(3, noticia.getCategoriaId());
            ps.setString(4, noticia.getAutor());
            ps.setString(5, noticia.getImagen());

            int filas = ps.executeUpdate();
            System.out.println("Noticia insertada. Filas afectadas: " + filas);

        } catch (Exception e) {
            System.err.println("Error al insertar noticia: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Noticia obtenerPorId(int id) {
        String sql = "SELECT n.*, c.nombre AS categoria_nombre " +
                     "FROM noticia n " +
                     "JOIN categoria c ON n.categoria_id = c.id " +
                     "WHERE n.id = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Noticia n = new Noticia(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("contenido"),
                        rs.getString("fecha"),
                        rs.getString("imagen"),
                        rs.getInt("categoria_id"),
                        rs.getString("autor")
                    );
                    n.setCategoriaNombre(rs.getString("categoria_nombre"));
                    return n;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizar(Noticia n) {
        String sql = "UPDATE noticia SET titulo=?, contenido=?, categoria_id=?, autor=?, imagen=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, n.getTitulo());
            ps.setString(2, n.getContenido());
            ps.setInt(3, n.getCategoriaId());
            ps.setString(4, n.getAutor());
            ps.setString(5, n.getImagen());
            ps.setInt(6, n.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}