package com.suyang;

import com.suyang.domain.Author;
import com.suyang.domain.Book;
import com.suyang.repository.AuthorRepository;
import com.suyang.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner init(AuthorRepository authorRepository,
                                  BookRepository bookRepository) {
        return args -> {
            Author author1 = new Author(1, "Joanne", 30);
            Author author2 = new Author(2, "Herman", 30);
            Author author3 = new Author(3, "Anne", 30);
            authorRepository.save(author1);
            authorRepository.save(author2);
            authorRepository.save(author3);

            Book book1 = new Book(1, "Harry Potter and the Philosopher's Stone", author1);
            Book book2 = new Book(2, "Moby Dick", author2);
            Book book3 = new Book(3, "Interview with the vampire", author3);

            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
        };
    }
}
