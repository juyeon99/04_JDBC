package com.ohgiraffers.section03.sqlinjection;

import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        // 사원 ID와 이름을 입력받고 두 개가 일치하는 employee가 있는지 확인
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("사원 ID: ");
        String empId = sc.nextLine();

        System.out.print("사원 이름: ");
        String empName = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId
                + "' AND EMP_NAME = '" + empName + "'";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()){
                System.out.println(rset.getString("emp_name") + "님 환영합니다.");
            } else {
                System.out.println("회원 정보가 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }

        // 사원 ID: 65467532
        // 사원 이름: ' or 1=1 and emp_id = '204
        // 유재식님 환영합니다.
    }
}
