package com.csaba79coder.littersnap.model.user.persistence;

import com.csaba79coder.littersnap.model.user.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorOrTitleOrderByIdDesc(String author, String title);
    @Query("SELECT b FROM Book b WHERE b.author LIKE %?1% OR b.title LIKE %?1% ORDER BY b.id DESC")
    List<Book> findByAuthorOrTitleOrderByIdDescQuery(String author, String title);
}
