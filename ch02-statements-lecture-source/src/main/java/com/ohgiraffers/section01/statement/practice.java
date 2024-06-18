package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.StudentDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class practice {
    public static void main(String[] args) {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        // list 생성
        List<StudentDTO> studentList = null;

        StudentDTO row = null;
        try {
            stmt = con.createStatement();
            String query = "SELECT * FROM class";

            studentList = new ArrayList<>();

            rset = stmt.executeQuery(query);
            while (rset.next()){
                row = new StudentDTO();
                row.setStudent_no(rset.getInt("student_no"));
                row.setGender(rset.getString("gender"));
                row.setStudent_name(rset.getString("student_name"));
                row.setGithub_id(rset.getString("github_id"));
                row.setEmail(rset.getString("email"));
                row.setMbti(rset.getString("mbti"));
                row.setSubject_no(rset.getInt("subject_no"));

                studentList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);

            for (StudentDTO student : studentList){
                System.out.println(student);
            }
        }
    }
}
