package com.ohgiraffers.section03.sqlinjection;

import java.sql.*;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        // 사원 ID와 이름을 입력받고 두 개가 일치하는 employee가 있는지 확인
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("사원 ID: ");
        String empId = sc.nextLine();

        System.out.print("사원 이름: ");
        String empName = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ? AND EMP_NAME = ?";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empId);
            pstmt.setString(2,empName);

            rset = pstmt.executeQuery();

            if (rset.next()){
                System.out.println(rset.getString("emp_name") + "님 환영합니다.");
            } else {
                System.out.println("회원 정보가 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        // 사원 ID: 65467532
        // 사원 이름: ' or 1=1 and emp_id = '204
        // 회원 정보가 없습니다.
    }
}
