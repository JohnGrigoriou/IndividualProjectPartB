package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    private int id;
    private String title;
    private String stream;
    private String type;
    private String startDate;
    private String endDate;
    private List<Student> students = new ArrayList();
    private List<Trainer> trainers = new ArrayList();
    private List<Assignment> assignments = new ArrayList();
   
    public Course() {
    }

    public Course(int id, String title, String stream, String type, String startDate, String endDate) {
        this.id = id;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course(String title, String stream, String type, String startDate, String endDate) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
    
    public void addStudent(Student student){
        if(students == null){
            students = new ArrayList();
        }
        students.add(student);
    }
    
    public void addTrainer(Trainer trainer){
        if(trainers == null){
            trainers = new ArrayList();
        }
        trainers.add(trainer);
    }
    
    public void addAssignment(Assignment assignment){
        if(assignments == null){
            assignments = new ArrayList();
        }
        assignments.add(assignment);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.stream);
        hash = 29 * hash + Objects.hashCode(this.type);
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
        final Course other = (Course) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.stream, other.stream)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%1$-4s%2$-17s%3$-15s%4$-10s%5$-15s%6$-10s", id, title, stream, type, startDate, endDate);
    }
    
}
