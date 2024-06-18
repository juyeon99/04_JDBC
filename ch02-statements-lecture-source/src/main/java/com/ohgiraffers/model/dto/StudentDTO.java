package com.ohgiraffers.model.dto;

public class StudentDTO {
    private int student_no;
    private String gender;
    private String student_name;
    private String github_id;
    private String email;
    private String mbti;
    private int subject_no;

    public StudentDTO() {
    }

    public StudentDTO(int student_no, String gender, String student_name, String github_id, String email, String mbti, int subject_no) {
        this.student_no = student_no;
        this.gender = gender;
        this.student_name = student_name;
        this.github_id = github_id;
        this.email = email;
        this.mbti = mbti;
        this.subject_no = subject_no;
    }

    public int getStudent_no() {
        return student_no;
    }

    public void setStudent_no(int student_no) {
        this.student_no = student_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getGithub_id() {
        return github_id;
    }

    public void setGithub_id(String github_id) {
        this.github_id = github_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public int getSubject_no() {
        return subject_no;
    }

    public void setSubject_no(int subject_no) {
        this.subject_no = subject_no;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "student_no=" + student_no +
                ", gender='" + gender + '\'' +
                ", student_name='" + student_name + '\'' +
                ", github_id='" + github_id + '\'' +
                ", email='" + email + '\'' +
                ", mbti='" + mbti + '\'' +
                ", subject_no=" + subject_no +
                '}';
    }
}
