package fr.univamu.iut.users;

import fr.univamu.iut.SqlRequests;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/utilisateurs")
public class Get {
    @GET
    @Produces
    public String get() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Utilisateurs ");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                row.put("id", id);
                row.put("nom", nom);
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
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Utilisateurs WHERE id='" + id + "'");
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