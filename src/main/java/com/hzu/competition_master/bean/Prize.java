package com.hzu.competition_master.bean;

import org.springframework.stereotype.Repository;

@Repository
public class Prize {
    public static int STATUS_WAIT = 1;
    public static int STATUS_AGREE = 2;
    public static int STATUS_DISAGREE = 3;
    private int id;
    private int studentId;
    private int competitionId;
    private int teacherId;
    private int status;
    private String certificatePath;
    private String result;
    private String participationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(String participationDate) {
        this.participationDate = participationDate;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", competitionId=" + competitionId +
                ", teacherId=" + teacherId +
                ", status=" + status +
                ", certificatePath='" + certificatePath + '\'' +
                ", result='" + result + '\'' +
                ", participationDate='" + participationDate + '\'' +
                '}';
    }
}
