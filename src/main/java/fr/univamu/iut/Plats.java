package fr.univamu.iut;

import jakarta.ws.rs.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Cette classe gère les opérations CRUD (Create, Read, Update, Delete) pour les plats.
 * Les opérations sont exposées en tant que services web RESTful.
 */
@Path("/plats")
public class Plats {

    /**
     * Crée un nouveau plat avec les informations fournies.
     *
     * @param nom         Le nom du plat.
     * @param prix        Le prix du plat.
     * @param description La description du plat.
     * @return true si le plat a été créé avec succès, false sinon.
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean create(@FormParam("nom") String nom, @FormParam("prix") float prix, @FormParam("description") String description) {
        try {
            SqlRequests.executeQuery("INSERT INTO Plats (nom, prix, description) VALUES ('" + nom + "', " + prix + ",'" + description + "')");
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Récupère tous les plats disponibles.
     *
     * @return Une chaîne JSON représentant tous les plats.
     * @throws SQLException          Si une erreur survient lors de l'exécution de la requête SQL.
     * @throws ClassNotFoundException Si la classe de la base de données n'est pas trouvée.
     */
    @GET
    @Produces("text/plain")
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

        return result.toString();
    }

    /**
     * Récupère un plat spécifique en fonction de son identifiant.
     *
     * @param id L'identifiant du plat à récupérer.
     * @return Une chaîne JSON représentant le plat trouvé.
     * @throws SQLException          Si une erreur survient lors de l'exécution de la requête SQL.
     * @throws ClassNotFoundException Si la classe de la base de données n'est pas trouvée.
     */
    @GET
    @Produces("text/plain")
    @Path("/{id}")
    public String getWithId(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Plats WHERE id='" + id + "'");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
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

        return result.toString().replace("[", "").replace("]", "");
    }

    /**
     * Met à jour les informations d'un plat existant.
     *
     * @param nom         Le nouveau nom du plat.
     * @param prix        Le nouveau prix du plat.
     * @param description La nouvelle description du plat.
     * @param id          L'identifiant du plat à mettre à jour.
     * @return true si le plat a été mis à jour avec succès, false sinon.
     */
    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public boolean put(@FormParam("nom") String nom, @FormParam("prix") float prix, @FormParam("description") String description, @FormParam("id") int id) {
        try {
            SqlRequests.executeQuery("UPDATE Plats SET nom='" + nom + "', prix='" + prix + "',description='" + description + "' WHERE id='" + id + "'");
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Supprime un plat existant en fonction de son identifiant.
     *
     * @param id L'identifiant du plat à supprimer.
     * @return true si le plat a été supprimé avec succès, false sinon.
     */
    @DELETE
    @Path("/{id}")
    public boolean delete(@PathParam("id") int id) {
        try {
            SqlRequests.executeQuery("DELETE FROM Plats WHERE id='" + id + "'");
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
