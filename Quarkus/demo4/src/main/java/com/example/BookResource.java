package com.example;

import Entities.Book;
import Repositories.BookRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.faulttolerance.exceptions.BulkheadException;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional
public class BookResource {

    @Inject
    private BookRepository repo;

    @GET
    @Bulkhead(1)
    @Fallback(fallbackMethod = "FallbackAvaliable" , applyOn = BulkheadException.class)
    public List<Book> getBooks() {
        return repo.listAll();
    }

    @POST
    public Book createBook(Book book) {
        repo.persist(book);
        return book;
    }

    @GET
    @Path("{id}")
    public Book getBookById(@PathParam("id") Long id) {
        var book = repo.findById(id);
        if (book != null) {
            return book;
        }
        throw new NoSuchElementException("Book with id " + id + " not found");
    }

    @DELETE
    @Path("{id}")
    public void deleteBookById(@PathParam("id") Long id) {
        Book book = repo.findById(id);
    }

    @PUT
    @Path("{id}")
    public Book updateBook(@PathParam("id") Long id, Book book) {
        var oldBook = repo.findById(id);
        if (oldBook != null) {
            book.setId(oldBook.getId());
            book.setAuthor(oldBook.getAuthor());
            book.setTitle(oldBook.getTitle());
            book.setNumberOfPages(oldBook.getNumberOfPages());
            repo.persist(book);
            return book;
        }
        throw new NoSuchElementException("Book with id " + id + " not found");
    }

    public List<Book> FallbackAvaliable() {
        return List.of();
    }

}
