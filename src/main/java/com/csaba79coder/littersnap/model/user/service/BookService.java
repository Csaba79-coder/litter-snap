package com.csaba79coder.littersnap.model.user.service;

import com.csaba79coder.littersnap.model.user.entity.Book;
import com.csaba79coder.littersnap.model.user.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
