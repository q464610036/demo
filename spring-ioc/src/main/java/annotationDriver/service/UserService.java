package annotationDriver.service;

import annotationDriver.annotation.Photo;
import annotationDriver.bean.Dog;
import annotationDriver.bean.Student;


public class UserService {
    @Photo
    public Student getStudent(String id){
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        return student;
    }
}
