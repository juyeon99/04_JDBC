package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        /*
        * Placeholder : ?
        *
        * java 쿼리문은 파싱을 통해 컴파일 돼서 DB에 쿼리를 수행해 결과 값을 가져온다.
        * Statement -> SQL문 실행시 매번 쿼리를 새롭게 인식해서 컴파일 진행
        * PreparedStatement -> 조건값을 ?로 두고 다른 쿼리를 미리 컴파일 해둔뒤
        *                      쿼리는 변경하지 않고, 바인딩되는 변수만 바꿔서 SQL문이 실행된다.
        * */

        // 커넥션 객체
        Connection con = getConnection();

        // PreparedStatement 레퍼런스 변수 생성
        PreparedStatement pstmt = null;

        // ResultSet 레퍼런스 변수 생성
        ResultSet rset = null;

        String empId = "200";
        String empSalary = "8000000";
        try {
//            pstmt = con.prepareStatement("SELECT EMP_ID,EMP_NAME FROM EMPLOYEE " +
//                    "WHERE EMP_ID = ?");

            pstmt = con.prepareStatement("SELECT EMP_ID,EMP_NAME FROM EMPLOYEE " +
                    "WHERE EMP_ID = ? AND SALARY = ?");

            // 인덱스가 붙어있지만 1부터 시작
            pstmt.setString(1,empId);
            pstmt.setString(2,empSalary);

            rset = pstmt.executeQuery();

            if (rset.next()){
                System.out.println(rset.getString("EMP_ID") + ", " +
                        rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
