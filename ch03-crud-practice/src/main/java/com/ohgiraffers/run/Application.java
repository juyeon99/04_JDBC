package com.ohgiraffers.run;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int result = 0;

        Properties prop = new Properties();

        List<EmployeeDTO> empList = null;

        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            while (flag){
                System.out.println("--------------------------------");
                System.out.println("\n1. 사원 목록 조회\n2. 사원 추가\n3. 사원 수정\n4. 사원 삭제\n5. 사원 단일 조회\n0. 종료");
                System.out.print("선택: ");
                int input = sc.nextInt();
                sc.nextLine();
                System.out.println("--------------------------------");

                switch (input){
                    case 1:
                        String query = prop.getProperty("selectAllEmployees");
                        pstmt = con.prepareStatement(query);
                        rset = pstmt.executeQuery();

                        EmployeeDTO row = null;
                        empList = new ArrayList<>();

                        while (rset.next()){
                            row = new EmployeeDTO();

                            row.setEmpId(rset.getString("EMP_ID"));
                            row.setEmpName(rset.getString("EMP_NAME"));
                            row.setEmpNo(rset.getString("EMP_NO"));
                            row.setEmail(rset.getString("EMAIL"));
                            row.setPhone(rset.getString("PHONE"));
                            row.setDeptCode(rset.getString("DEPT_CODE"));
                            row.setJobCode(rset.getString("JOB_CODE"));
                            row.setSalLevel(rset.getString("SAL_LEVEL"));
                            row.setSalary(rset.getDouble("SALARY"));
                            row.setBonus(rset.getDouble("BONUS"));
                            row.setManagerId(rset.getString("MANAGER_ID"));
                            row.setHireDate(rset.getDate("HIRE_DATE"));
                            row.setEntDate(rset.getDate("ENT_DATE"));
                            row.setEntYn(rset.getString("ENT_YN"));

                            empList.add(row);
                        }

                        for (EmployeeDTO emp : empList){
                            System.out.println(emp);
                        }
                        break;
                    case 2:
                        query = prop.getProperty("insertEmployee");
                        pstmt = con.prepareStatement(query);

                        System.out.print("추가할 사원 번호: ");
                        int empId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("사원 이름: ");
                        String empName = sc.nextLine();

                        System.out.print("주민등록번호: ");
                        String empNo = sc.nextLine();

                        System.out.print("직급 코드: ");
                        String jobCode = sc.nextLine();

                        System.out.print("급여 등급: ");
                        String salLevel = sc.nextLine();

                        pstmt.setInt(1, empId);
                        pstmt.setString(2, empName);
                        pstmt.setString(3, empNo);
                        pstmt.setString(4, jobCode);
                        pstmt.setString(5, salLevel);

                        result = pstmt.executeUpdate();

                        if(result > 0){
                            System.out.println("사원 추가 성공!");
                        } else {
                            System.out.println("사원 추가 실패!");
                        }

                        break;
                    case 3:
                        query = prop.getProperty("updateEmployee");
                        pstmt = con.prepareStatement(query);

                        System.out.print("수정할 사원 번호: ");
                        empId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("사원 이름: ");
                        empName = sc.nextLine();

                        System.out.print("주민등록번호: ");
                        empNo = sc.nextLine();

                        pstmt.setString(1, empName);
                        pstmt.setString(2, empNo);
                        pstmt.setInt(3, empId);

                        result = pstmt.executeUpdate();

                        if(result > 0){
                            System.out.println("사원 정보 변경 성공!");
                        } else {
                            System.out.println("사원 정보 변경 실패!");
                        }
                        break;
                    case 4:
                        query = prop.getProperty("deleteEmployee");
                        pstmt = con.prepareStatement(query);

                        System.out.print("삭제할 사원 번호: ");
                        empId = sc.nextInt();
                        sc.nextLine();

                        pstmt.setInt(1, empId);

                        result = pstmt.executeUpdate();

                        if(result > 0){
                            System.out.println("사원 삭제 성공!");
                        } else {
                            System.out.println("사원 삭제 실패!");
                        }
                        break;
                    case 5:
                        query = prop.getProperty("selectOneEmployee");
                        pstmt = con.prepareStatement(query);

                        System.out.print("조회할 사원 번호: ");
                        empId = sc.nextInt();
                        sc.nextLine();

                        pstmt.setInt(1, empId);

                        rset = pstmt.executeQuery();

                        if (rset.next()){
                            row = new EmployeeDTO();

                            row.setEmpId(rset.getString("EMP_ID"));
                            row.setEmpName(rset.getString("EMP_NAME"));
                            row.setEmpNo(rset.getString("EMP_NO"));
                            row.setEmail(rset.getString("EMAIL"));
                            row.setPhone(rset.getString("PHONE"));
                            row.setDeptCode(rset.getString("DEPT_CODE"));
                            row.setJobCode(rset.getString("JOB_CODE"));
                            row.setSalLevel(rset.getString("SAL_LEVEL"));
                            row.setSalary(rset.getDouble("SALARY"));
                            row.setBonus(rset.getDouble("BONUS"));
                            row.setManagerId(rset.getString("MANAGER_ID"));
                            row.setHireDate(rset.getDate("HIRE_DATE"));
                            row.setEntDate(rset.getDate("ENT_DATE"));
                            row.setEntYn(rset.getString("ENT_YN"));

                            System.out.println(row);
                        }
                        break;
                    case 0:
                        flag = false;
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
    }
}
