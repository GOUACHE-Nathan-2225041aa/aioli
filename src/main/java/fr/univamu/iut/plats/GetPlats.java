package fr.univamu.iut.plats;

import jakarta.ws.rs.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.univamu.iut.SqlRequests;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/plat")
public class GetPlats {
    @GET
    @Produces("text/plain")
    @Path("/getIdByName/{name}")
    public String getIdByName(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT id FROM Plats WHERE nom='" + name + "'");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                int id = resultSet.getInt("id");
                row.put("id", id);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
    @GET
    @Produces("text/plain")
    @Path("/getNameById/{id}")
    public String getNameById(@PathParam("id") String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT nom FROM Plats WHERE id='" + id + "'");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                String nom = resultSet.getString("nom");
                row.put("Nom", nom);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}