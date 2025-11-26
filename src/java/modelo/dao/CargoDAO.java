package modelo.dao;

import modelo.entidades.Cargo;
import modelo.util.Conexion;
import java.sql.*;
import java.util.*;

public class CargoDAO {

    public List<Cargo> listar() {
        List<Cargo> lista = new ArrayList<>();
        String sql = "SELECT * FROM cargo";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cargo c = new Cargo();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}

