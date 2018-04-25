package com.prince.design;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Prince Raj
 */
public class OnlineBookReaderSystem {

    private static final int MAX_COUNT_PER_USER = 5;

    private Map<Category, List<Book>> categoryBooksMap = new HashMap<>();

    private List<User> users;

    private Map<User, List<Book>> userBooksMap = new HashMap<>();

    public void registerUser(User user) {
        users.add(user);
    }

    public void issueBook(User user, Book book) {
        List<Book> books = userBooksMap.get(user);

        if (books == null) {
            books = new ArrayList<>();
            userBooksMap.put(user, books);
        }

        if (books.size() < MAX_COUNT_PER_USER) {
            books.add(book);
            book.setIssuedDate(new Date());
            book.setUser(user);
        } else {
            // log error that books can;t be issued as max limit is reached
        }
    }

    public void releaseBook(User user, Book book) {
        List<Book> books = userBooksMap.get(user);
        if (books == null) {
            // log error that there is no book issued to user
        } else {
            books = userBooksMap.get(user);
            if (books.contains(book)) {
                books.remove(book);
            } else {
                // log error that mentioned book is not present in
            }
        }
    }

    public List<Book> getIssuedBooksForUser(User user) {
        List<Book> books = userBooksMap.get(user);
        if (books == null) {
            // log error that there is no book issued to user
        }

        return books;
    }

    public List<Book> suggestBooksForUser(User user) {
        List<Book> books = userBooksMap.get(user);
        List<Book> suggestedBooks = new ArrayList<>();
        if (books == null) {
            // log error that there is no book issued to user
        } else {
            for (Book book : books) {
                Category category = book.getCategory();
                suggestedBooks.addAll(categoryBooksMap.get(category));
            }

            suggestedBooks.removeAll(books);
        }

        return suggestedBooks;
    }

    public boolean searchBook(Book book) {
        boolean found = false;
        for (List<Book> books : categoryBooksMap.values()) {
            if (books.contains(book)) {
                found = true;
            }
        }

        return found;
    }

    public boolean isBookAvailable(Book book) {
        boolean available = false;
        for (List<Book> books : categoryBooksMap.values()) {
            if (books.contains(book) && book.getUser() == null) {
                available = true;
            }
        }

        return available;
    }

    public boolean renewMembership(User user) {
        return true;
    }
}

class Book {

    private String id;

    private String name;

    private String author;

    private BigDecimal price;

    private Category category;

    private User user;

    private Date issuedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book)o;
        return Objects.equals(id, book.id)
                && Objects.equals(name, book.name)
                && Objects.equals(author, book.author)
                && Objects.equals(price, book.price)
                && category == book.category
                && Objects.equals(user, book.user)
                && Objects.equals(issuedDate, book.issuedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, author, price, category, user, issuedDate);
    }
}

class User {

    private String name;

    private int age;

    private List<Category> interests;

    private List<String> booksIssued;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Category> getInterests() {
        return interests;
    }

    public void setInterests(List<Category> interests) {
        this.interests = interests;
    }

    public List<String> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<String> booksIssued) {
        this.booksIssued = booksIssued;
    }
}

enum Category {
    LITERATURE, PHILOSOPHY, HISTORY
}