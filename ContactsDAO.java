import java.sql.*;
import java.util.*;

public class ContactsDAO {
    private ArrayList<Person> list;
    private String userid = "root";
    private String password = "password";
    private String url = "jdbc:mysql://localhost/contacts?serverTimezone=Asia/Seoul";

    private Connection con;

    public ContactsDAO() {
        list = new ArrayList<>();
        getConnection();
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userid, password);
        } catch (Exception e) {
        }
        return con;
    }

    public ArrayList<Person> search(String n) {
        String name, phone, email;
        try {
            String sql = "SELECT * FROM person WHERE name like '%" + n + "%'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                name = rs.getString("name");
                phone = rs.getString("phone");
                email = rs.getString("email");
                Person c = new Person(name, phone, email);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insert(Person c) {
        try {
            String sql = "INSERT INTO person (name, phone, email) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void update(Person c) {
        try {
            String sql = "UPDATE person SET name=?, email=? where phone=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void delete(String n) {
        try {
            String sql = "DELETE FROM person WHERE name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, n);

            num = ps.executeUpdate();
        } catch (Exception e) {
        }
        return num;
    }
}
