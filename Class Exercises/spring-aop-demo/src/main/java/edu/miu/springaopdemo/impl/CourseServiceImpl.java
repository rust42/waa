package edu.miu.springaopdemo.impl;

import edu.miu.springaopdemo.CourseService;
import edu.miu.springaopdemo.helper.HelloWorld;
import edu.miu.springaopdemo.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private Helper helper;

    @Override
    @HelloWorld
    public String findAll() {
//        helper.print();
        return "All courses";
    }

    @Override
    @HelloWorld
    public String findById(int id) {
//        helper.print();
        return "One id";
    }

}
