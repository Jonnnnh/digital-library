package org.example.digital_library.service;

import org.example.digital_library.model.Author;
import org.example.digital_library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}
