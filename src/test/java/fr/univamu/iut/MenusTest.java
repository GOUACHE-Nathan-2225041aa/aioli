package fr.univamu.iut;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MenusTest {

    Menus menu = new Menus();

    @BeforeAll
    void setUp() throws SQLException, ClassNotFoundException {
        // menu.create("UserTest", "41,42");
        // J'arrive pas à créer avec menu.create() alors que menu.get() fonctionne ?
        // Je peux pas vraiment tester grand chose sans menu.create()...
        SqlRequests.executeQuery("INSERT INTO Menus (id, nom_user, plats) VALUES (1, 'UserTest', '41,42,43,44,45')");
    }

    @AfterAll
    void tearDown() throws SQLException, ClassNotFoundException {
        SqlRequests.executeQuery("DELETE FROM Menus WHERE id=1");
    }

    @Test
    void create() throws SQLException, ClassNotFoundException {
        menu.create("UserTest", "41,42");
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Menus WHERE nom_user='UserTest'");
        if(rs.next()){
            assertEquals("prix", rs.getString("71"));
        }
        SqlRequests.executeQuery("DELETE FROM Plats WHERE nom='UserTest'");
    }

    @Test
    void get() throws SQLException, ClassNotFoundException {
        // Le create ne fonctionnant pas,
        // je crée à la main un menu sans date ni prix, que je get() avec succès ici :)
        JSONArray jsonArray = new JSONArray(menu.get());
        assertEquals("{\"prix\":0,\"nom_user\":\"UserTest\",\"plats\":\"41,42,43,44,45\",\"id\":1,\"date_creation\":\"0000-00-00\"}", jsonArray.get(0).toString());
    }

    @Test
    void getUser_nameById() throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject(menu.getWithId("1"));
        assertEquals("UserTest", jsonObject.getString("nom_user"));
    }

    @Test
    void updateMenu() throws SQLException, ClassNotFoundException {
        // Il ne trouve pas updateMenu non plus visiblement...
        menu.updateMenu(1, "UserTestModifié", "43,44");
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Menus WHERE id=1");
        if(rs.next()){
            assertEquals("Test2", rs.getString("nom_user"));
        };
    }

    @Test
    void deletePlatWithId() throws SQLException, ClassNotFoundException {
        menu.deletePlatWithId("1");
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Plats WHERE id=1");
        if(rs.next()){
            assertEquals("", rs.getString("id"));
        }
    }
}
