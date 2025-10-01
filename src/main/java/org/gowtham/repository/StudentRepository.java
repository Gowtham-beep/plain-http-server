package org.gowtham.repository;

import org.gowtham.entity.Student;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentRepository {
    private final Map<Integer, Student> db = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public Student save(Student student){
        int id =idCounter.getAndIncrement();
        student.setId(id);
        db.put(id,student);
        return student;
    }
    public Student findById(int id){
        return db.get(id);
    }
    public Student update(int id, Student student){
        if(!db.containsKey(id)){
            return null;
        }
        student.setId(id);
        db.put(id,student);
        return student;
    }
    public boolean remove(int id){
        return db.remove(id)!= null;
    }

}
