package fr.univamu.iut;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlatsTest {

    Plats plat = new Plats();

    @BeforeAll
    void setUp() throws SQLException, ClassNotFoundException {
        SqlRequests.executeQuery("INSERT INTO Plats (id, nom, prix, description) VALUES (1, 'Plat Test', 1, 'Plat de test')");
    }

    @AfterAll
    void tearDown() throws SQLException, ClassNotFoundException {
        SqlRequests.executeQuery("DELETE FROM Plats WHERE id=1");
    }

    @Test
    void create() throws SQLException, ClassNotFoundException {
        plat.create("Test", 10, "Plat de test");
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Plats WHERE nom='Test'");
        if(rs.next()){
            assertEquals("Plat de test", rs.getString("description"));
        }
        SqlRequests.executeQuery("DELETE FROM Plats WHERE nom='Test'");
    }

    @Test
    void get() throws SQLException, ClassNotFoundException {
        JSONArray jsonArray = new JSONArray(plat.get());
        assertEquals("{\"prix\":1,\"description\":\"Plat de test\",\"id\":1,\"nom\":\"Plat Test\"}", jsonArray.get(0).toString());
    }

    @Test
    void getWithId() throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject(plat.getWithId(1));
        assertEquals("Test2", jsonObject.getString("nom"));
    }



    @Test
    void put() throws SQLException, ClassNotFoundException {
        plat.put("Test2", 2, "Test 2", 1);
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Plats WHERE id=1");
        if(rs.next()){
            assertEquals("Test2", rs.getString("nom"));
        };
    }

    @Test
    void delete() throws SQLException, ClassNotFoundException {
        SqlRequests.executeQuery("INSERT INTO Plats (id, nom, prix, description) VALUES (2, 'Plat Test', 1, 'Plat de test')");
        plat.delete(2);
        ResultSet rs = SqlRequests.executeQuery("SELECT * FROM Plats WHERE id=2");
        if(rs.next()){
            assertEquals("", rs.getString("nom"));
        }
        SqlRequests.executeQuery("DELETE FROM Plats WHERE id=2");
    }
}