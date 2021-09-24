package entity;

import java.util.Objects;

public class Grade {
    
    private Student student;
    private Assignment assignment;
    private int oralMark;
    private int totalMark;

    public Grade(Student student, Assignment assignment, int oralMark, int totalMark) {
        this.student = student;
        this.assignment = assignment;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public int getOralMark() {
        return oralMark;
    }

    public void setOralMark(int oralMark) {
        this.oralMark = oralMark;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.student);
        hash = 89 * hash + Objects.hashCode(this.assignment);
        hash = 89 * hash + this.oralMark;
        hash = 89 * hash + this.totalMark;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grade other = (Grade) obj;
        if (this.oralMark != other.oralMark) {
            return false;
        }
        if (this.totalMark != other.totalMark) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return student.getFirstName()+" "+student.getLastName()+" grades for assignment"
                + " "+assignment.getTitle()+" are: Oralmark = "+oralMark+" Totalmark = "+totalMark;
    }
   
}
