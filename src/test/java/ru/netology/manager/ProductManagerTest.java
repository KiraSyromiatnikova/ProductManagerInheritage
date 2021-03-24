package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;


public class ProductManagerTest {

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    private Book first = new Book(1, "first", 100, "author1");
    private Book second = new Book(2, "second", 200, "author1");
    private Book third = new Book(3, "first", 300, "author3");
    private Smartphone fourth = new Smartphone(1, "first", 100, "producer1");
    private Smartphone fifth = new Smartphone(2, "second", 200, "producer2");
    private Smartphone sixth = new Smartphone(3, "third", 300, "producer2");

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    public void shouldSearchSame() {

        manager.searchBy("first");

        Product[] expected = new Product[]{first, third, fourth};
        Product[] actual = manager.searchBy("first");
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSearchByAuthor() {

        manager.searchBy("author1");

        Product[] expected = new Product[]{first, second};
        Product[] actual = manager.searchBy("author1");
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSearchByProducer() {

        manager.searchBy("Producer1");

        Product[] expected = new Product[]{fourth};
        Product[] actual = manager.searchBy("Producer1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotExistBook() {

        manager.searchBy("author4");

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("author4");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNotExistSmartphone() {

        manager.searchBy("producer4");

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("producer4");
        assertArrayEquals(expected, actual);
    }
}