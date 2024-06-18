package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        // 1. Connection 객체 생성
        Connection con = getConnection();

        // 2. Statement 생성 // 쿼리문을 작성해서 데이터베이스에 요청하기위해 사용하는 객체
        Statement stmt = null;

        // 3. ResultSet 생성 (SELECT 할 때만)
        ResultSet rset = null;

        try {
            // 4. 연결객체의 createStatement()를 이용한 Statement 객체 생성
            stmt = con.createStatement();

            String EMP_ID = "200";
            String query = "SELECT EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + EMP_ID + "'";

            // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet에 반환받음
            rset = stmt.executeQuery(query);

            // 6. 쿼리문의 결과를 컬럼 이름을 이용해서 사용
            while (rset.next()){
                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 7. 사용한 자원 반납
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
