package fr.univamu.iut;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersTest {

    Users users = new Users();

    @BeforeAll
    void setUp() throws SQLException, ClassNotFoundException {
        SqlRequests.executeQuery("INSERT INTO Utilisateurs (id, nom) VALUES (1, 'User Test')");
    }

    @AfterAll
    void tearDown() throws SQLException, ClassNotFoundException {
        SqlRequests.executeQuery("DELETE FROM Utilisateurs WHERE id=1");
    }

    @Test
    void create() throws SQLException, ClassNotFoundException {
        users.create("Test");
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Utilisateurs WHERE nom='Test'");
        if(rs.next()){
            assertEquals("Test", rs.getString("nom"));
        }
        SqlRequests.executeQuery("DELETE FROM Utilisateurs WHERE nom='Test'");
    }

    @Test
    void get() throws SQLException, ClassNotFoundException {
        JSONArray jsonArray = new JSONArray(users.get());
        assertEquals("{\"id\":1,\"nom\":\"User Test\"}", jsonArray.get(1).toString());
    }

    @Test
    void getWithId() throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject(users.getWithId(1));
        assertEquals("Test2", jsonObject.getString("nom"));
    }



    @Test
    void put() throws SQLException, ClassNotFoundException {
        users.put("Test2", 1);
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Utilisateurs WHERE id=1");
        if(rs.next()){
            assertEquals("Test2", rs.getString("nom"));
        };
    }

    @Test
    void delete() throws SQLException, ClassNotFoundException {
        SqlRequests.executeQuery("INSERT INTO Utilisateurs (id, nom) VALUES (2, 'Plat Test')");
        users.delete(2);
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Utilisateurs WHERE id=2");
        if(rs.next()){
            assertEquals("", rs.getString("nom"));
        }
        SqlRequests.executeQuery("DELETE FROM Utilisateurs WHERE id=2");
    }
}