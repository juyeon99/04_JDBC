package com.ohgiraffers.section02.template;

import java.sql.Connection;
import java.sql.SQLException;

import static com.ohgiraffers.section02.template.JDBCTemplate.close;
import static com.ohgiraffers.section02.template.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        // DB와 연결하는 연결 객체
        Connection con = getConnection();

        // 연결객체 생성되었는지 확인. (객체의 주소값이 반환되는것 확인)
        System.out.println("con = " + con);

        // 커넥션 꺼주기
        close(con);
    }
}
