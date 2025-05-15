import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/internship_db"; // URL i databazës
        String user = "postgres"; // Emri i përdoruesit në PostgreSQL
        String password = "fjalekalimi"; // Fjalëkalimi i përdoruesit

        try {

            Class.forName("org.postgresql.Driver");

            // Krijo lidhjen me databazën
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println("✅ Lidhja u bë me sukses!");

                Statement stmt = conn.createStatement();

                // a. Listo të gjitha tabelat në databazë
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet tables = meta.getTables(null, null, "%", new String[] {"TABLE"});
                System.out.println("\n📋 Tabelat në databazë:");
                while (tables.next()) {
                    System.out.println("Tabela: " + tables.getString("TABLE_NAME"));
                }

                // b. Lexo të gjithë rrjeshtat nga tabela kursi
                System.out.println("\n📚 Kurset:");
                ResultSet rs = stmt.executeQuery("SELECT * FROM kursi");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("emri"));
                }

                // c. Studentët me më shumë se 10 pikë
                System.out.println("\n🎓 Studentët me më shumë se 10 pikë:");
                rs = stmt.executeQuery("SELECT * FROM studenti WHERE piket > 10");
                while (rs.next()) {
                    System.out.println(rs.getString("emri") + " - " + rs.getInt("piket") + " pikë");
                }

                // d. Shto një student
                System.out.println("\n➕ Shtojmë një student...");
                stmt.executeUpdate("INSERT INTO studenti (emri, piket) VALUES ('Erion', 14)");

                // e. Modifiko pikët e një studenti
                System.out.println("\n✏️ Modifikojmë pikët për Erion...");
                stmt.executeUpdate("UPDATE studenti SET piket = 17 WHERE emri = 'Erion'");

                // f. Fshi një student
                System.out.println("\n❌ Fshijmë studentin Erion...");
                stmt.executeUpdate("DELETE FROM studenti WHERE emri = 'Erion'");

            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver PostgreSQL nuk u gjet!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Gabim në lidhje ose query:");
            e.printStackTrace();
        }
    }
}
