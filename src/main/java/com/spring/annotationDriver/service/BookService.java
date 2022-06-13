package com.spring.annotationDriver.service;


import com.spring.annotationDriver.dao.BookDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //@Qualifier("bookDao")
    //@Autowired(required=false)
    //@Resource(name="bookDao2")
//	@Inject
    @Qualifier("bookDao2")
    private BookDao bookDao;

    public void print() {
        System.out.println("bookDao:" + bookDao);
    }
}

