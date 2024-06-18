package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.DepartmentDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {
    public static void main(String[] args) {
        /*
        * Department 테이블의 부서코드(아이디)를 입력받아 부서코드,부서명,지역코드 출력
        * */
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 부서코드: ");
        String deptId = sc.nextLine();

        String query = "SELECT * FROM DEPARTMENT WHERE DEPT_ID = ?";
        DepartmentDTO selectedDept = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, deptId);

            rset = pstmt.executeQuery();

            if (rset.next()){
                selectedDept = new DepartmentDTO();

                selectedDept.setDeptId(rset.getString("DEPT_ID"));
                selectedDept.setDeptTitle(rset.getString("DEPT_TITLE"));
                selectedDept.setLocationId(rset.getString("LOCATION_ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        System.out.println("selectedDept = " + selectedDept);
    }
}
