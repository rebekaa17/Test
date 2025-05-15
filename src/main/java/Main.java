package org.example;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) {
        ConnectionManager cm = new ConnectionManager(
                "jdbc:postgresql://localhost:5432/internship_db",
                "postgres",
                "fjalekalimi"
        );

        try {
            cm.connect();


            Map<String, String> koloneTip = new LinkedHashMap<>();
            koloneTip.put("id", "SERIAL PRIMARY KEY");
            koloneTip.put("emri", "VARCHAR(100)");
            koloneTip.put("piket", "INT");

            cm.createTable("studenti", koloneTip);


            Student s1 = new Student(null, "Erion", 15);
            cm.insertStudent(s1);


            Student s = cm.getStudentById(1);
            if (s != null) System.out.println("Student: " + s);


            Student sUpdate = new Student(null, "Erion Berisha", 18);
            cm.updateStudent(1, sUpdate);


            cm.deleteStudent(1);

            cm.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
