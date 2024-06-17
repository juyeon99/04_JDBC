package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        // 연결객체
        Connection con = getConnection();

        // 쿼리문을 저장하고, 실행하는 기능을 하는 용도의 인터페이스
        Statement stmt = null;

        // *select* 결과집합을 받아올 용도의 인터페이스
        ResultSet rset = null;

        try {
            // Connection 이용해 statement 객체 생성
            stmt = con.createStatement();

            // 대소문자 상관 X, 쿼리문 마지막에 ; 넣으면 안됨
            rset = stmt.executeQuery("SELECT EMP_ID,EMP_NAME FROM EMPLOYEE");

            while (rset.next()){
                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            rset.close();
//            stmt.close();

            // connection 여는 순서: Connection -> Statement -> ResultSet
            // connection 닫는 순서: ResultSet -> Statement -> Connection
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
