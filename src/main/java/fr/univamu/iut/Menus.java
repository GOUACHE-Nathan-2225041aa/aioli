package fr.univamu.iut;

import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Path("/menus")
public class Menus {

    /** CREATE
     * Crée un nouveau menu avec les plats spécifiés pour un utilisateur donné.
     *
     * @param nom_user Le nom de l'utilisateur.
     * @param plats    Les identifiants des plats à inclure dans le menu (séparés par des virgules).
     * @return true si le menu a été créé avec succès, false sinon.
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean create(@FormParam("nom_user") String nom_user, @FormParam("plats") String plats) {

        // Supprimer les crochets de la chaîne plats
        plats = plats.replace("[", "").replace("]", "");

        // Diviser les identifiants des plats en une liste
        String[] identifiants = plats.split(",");

        // Liste pour stocker les réponses des requêtes pour chaque plat
        ArrayList<JSONObject> reponses = new ArrayList<JSONObject>();

        // Parcourir les identifiants et récupérer les détails de chaque plat
        for (String id : identifiants) {
            Client client = ClientBuilder.newClient();
            String response = client.target("http://localhost:8080/Plats_et_users-1.0-SNAPSHOT/api/plats/" + id)
                    .request(MediaType.TEXT_PLAIN_TYPE).get(String.class);
            reponses.add(new JSONObject(response));
        }

        // Calculer le prix total des plats sélectionnés
        double prixFinal = 0;

        // Permet de stock les noms des plats associés aux IDs
        StringBuilder listePlats = new StringBuilder();

        // Ajouter le prix de chaque plat au prixFinal, ainsi que leur nom dans listePlats
        for (JSONObject answ : reponses) {
            prixFinal += answ.getDouble("prix");
            listePlats.append(answ.getString("nom")).append(", ");
        }
        // Suprime la virgule de fin
        if (listePlats.length() > 0) {
            listePlats.deleteCharAt(listePlats.length() - 1);
            listePlats.deleteCharAt(listePlats.length() - 1);
        }

        // Obtenir la date actuelle au format SQL
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sqlDate = sdf.format(date);

        // Insérer le nouveau menu dans la base de données
        try {
            SqlRequests.executeQuery("INSERT INTO Menus (nom_user, date_creation, plats, prix) VALUES ('" + nom_user + "', '" + sqlDate + "', '{" + listePlats + "}', " + prixFinal + ")");
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }


    /** READ (ALL)
     * Récupère tous les menus disponibles.
     *
     * @return Une chaîne JSON représentant tous les menus.
     * @throws SQLException          Si une erreur survient lors de l'exécution de la requête SQL.
     * @throws ClassNotFoundException Si la classe de la base de données n'est pas trouvée.
     */
    @GET
    @Produces("text/plain")
    public String get() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT * FROM Menus ");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                int id = resultSet.getInt("id");
                String nom_user = resultSet.getString("nom_user");
                String date_creation = resultSet.getString("date_creation");
                String plats = resultSet.getString("plats");
                float prix = resultSet.getFloat("prix");
                row.put("id", id);
                row.put("nom_user", nom_user);
                row.put("date_creation", date_creation);
                row.put("plats", plats);
                row.put("prix", prix);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString().replace("[", "{").replace("]", "}");
    }

    /** READ (ID)
     * Récupère un menu spécifique en fonction de son identifiant.
     *
     * @param id L'identifiant du menu à récupérer.
     * @return Une chaîne JSON représentant le menu trouvé.
     * @throws SQLException          Si une erreur survient lors de l'exécution de la requête SQL.
     * @throws ClassNotFoundException Si la classe de la base de données n'est pas trouvée.
     */
    @GET
    @Produces("text/plain")
    @Path("/{id}")
    public String getNameById(@PathParam("id") String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT nom_user, plats, date_creation, prix FROM Menus WHERE id='" + id + "'");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                String nom_user = resultSet.getString("nom_user");
                String plats = resultSet.getString("plats");
                String date_creation = resultSet.getString("date_creation");
                String prix = resultSet.getString("prix");
                row.put("id", id);
                row.put("nom_user", nom_user);
                row.put("date_creation", date_creation);
                row.put("plats", plats);
                row.put("prix", prix);
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString().replace("[", "").replace("]", "");
    }


    /** UPDATE (ID)
     * Met à jour les informations d'un menu existant.
     *
     * @param nom_user     Le nouveau nom de l'utilisateur.
     * @param plats        Les identifiants des plats à inclure dans le menu (séparés par des virgules).
     * @param id           L'identifiant du menu à mettre à jour.
     * @return true si le menu a été mis à jour avec succès, false sinon.
     */
    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public boolean updateMenu(@FormParam("nom_user") String nom_user, @FormParam("plats") String plats, @FormParam("id") int id) {
        // Supprimer les crochets de la chaîne plats
        plats = plats.replace("[", "").replace("]", "");

        // Diviser les identifiants des plats en une liste
        String[] identifiants = plats.split(",");

        // Liste pour stocker les réponses des requêtes pour chaque plat
        ArrayList<JSONObject> reponses = new ArrayList<>();

        // Parcourir les identifiants et récupérer les détails de chaque plat
        for (String platId : identifiants) {
            Client client = ClientBuilder.newClient();
            String response = client.target("http://localhost:8080/Plats_et_users-1.0-SNAPSHOT/api/plats/" + platId)
                    .request(MediaType.TEXT_PLAIN_TYPE).get(String.class);
            reponses.add(new JSONObject(response));
        }

        // Calculer le prix total des plats sélectionnés
        double prixFinal = 0;

        // Permet de stock les noms des plats associés aux IDs
        StringBuilder listePlats = new StringBuilder();

        // Ajouter le prix de chaque plat au prixFinal, ainsi que leur nom dans listePlats
        for (JSONObject answ : reponses) {
            prixFinal += answ.getDouble("prix");
            listePlats.append(answ.getString("nom")).append(", ");
        }

        // Supprimer la virgule finale si la liste des plats n'est pas vide
        if (listePlats.length() > 0) {
            listePlats.deleteCharAt(listePlats.length() - 1);
            listePlats.deleteCharAt(listePlats.length() - 1);
        }

        // Obtenir la date actuelle au format SQL
        java.util.Date currentDate = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(currentDate);

        // Mettre à jour le menu dans la base de données
        try {
            SqlRequests.executeQuery("UPDATE Menus SET nom_user='" + nom_user + "', plats='{" + listePlats + "}', prix=" + prixFinal + ", date_creation='" + date + "' WHERE id=" + id);
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }


    /** DELETE (ID)
     * Supprime un menu existant en fonction de son identifiant.
     *
     * @param id L'identifiant du menu à supprimer.
     * @return true si le menu a été supprimé avec succès, false sinon.
     */
    @DELETE
    @Path("/{id}")
    public boolean deletePlatWithId(@PathParam("id") String id) {
        try {
            SqlRequests.executeQuery("DELETE FROM Menus WHERE id='" + id + "'");
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}