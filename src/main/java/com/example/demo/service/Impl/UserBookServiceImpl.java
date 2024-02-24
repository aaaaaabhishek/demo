package com.example.demo.service.Impl;

import com.example.demo.Payload.UserBookDto;
import com.example.demo.Repositary.UserBookRepositary;
import com.example.demo.entity.Book;
import com.example.demo.service.UserBookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookServiceImpl implements UserBookservice {
    @Autowired
    private UserBookRepositary userBookRepositary;
    @Override
    public void saveuser(UserBookDto userbookdto) {
        Book book=new Book();
        book.setId(userbookdto.getId());
        book.setCheck_In_Date(userbookdto.getCheck_In_Date());
        book.setCheck_Out_Date(userbookdto.getCheck_Out_Date());
        book.setNumber_of_Guests(userbookdto.getNumber_of_Guests());
        book.setRoom_Type(userbookdto.getRoom_Type());
        userBookRepositary.save(book);
    }
}
