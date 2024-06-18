package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        /*
        * PreparedStatement
        *
        * Statement를 상속받은 인터페이스
        * 더 효율적으로 작업 진행
        * 완성된 SQL문과 미완성된 SQL문을 모두 사용 가능
        *
        * Placeholder : ?
        * */

        // 커넥션 생성
        Connection con = getConnection();

        // PreparedStatement 레퍼런스 변수 생성
        PreparedStatement pstmt = null;

        // ResultSet 레퍼런스 변수 생성
        ResultSet rset = null;

        try {
            // statement와 다르게 PreparedStatement 객체를 생성할 때 쿼리문을 넣어준다.
            pstmt = con.prepareStatement("SELECT EMP_ID,EMP_NAME FROM EMPLOYEE");

            rset = pstmt.executeQuery();

            while (rset.next()){
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
