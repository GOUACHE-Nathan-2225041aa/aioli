package fr.univamu.iut.plats;

import jakarta.ws.rs.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.univamu.iut.SqlRequests;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/plats")
public class Get {
    @GET
    public String get() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Plats ");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                float prix = resultSet.getFloat("prix");
                String description = resultSet.getString("description");
                row.put("id", id);
                row.put("nom", nom);
                row.put("prix", prix);
                row.put("description", description);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString().replace("[", "{").replace("]", "}");
    }

    @GET
    @Produces("text/plain")
    @Path("/{id}")
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