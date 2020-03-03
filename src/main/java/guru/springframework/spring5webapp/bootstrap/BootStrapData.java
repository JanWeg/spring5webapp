package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }


    private Publisher createAndSavePublisher(String name, String city, String zip) {
        Publisher publisher = new Publisher(name, city, zip);
        publisherRepository.save(publisher);

        return publisher;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher oreileyPublisher = createAndSavePublisher("O'Really", "New Awk", "12345");
        Publisher sgfPublisher = createAndSavePublisher("SGF Publix", "St. Moritz", "CH-9876");

        Author aretha = new Author("Rita", "Random");
        Book newBook = new Book("How to do it", "12345678901");

        aretha.getBooks().add(newBook);
        newBook.getAuthors().add(aretha);
        newBook.setPublisher(oreileyPublisher);
        oreileyPublisher.getBooks().add(newBook);

        authorRepository.save(aretha);
        bookRepository.save(newBook);

        newBook = new Book("Lifeguard book on not buying Lifegard books", "65498721");
        newBook.getAuthors().add(aretha);
        newBook.setPublisher(oreileyPublisher);
        oreileyPublisher.getBooks().add(newBook);
        aretha.getBooks().add(newBook);

        bookRepository.save(newBook);


        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(sgfPublisher);
        sgfPublisher.getBooks().add(noEjb);

        authorRepository.save(rod);
        bookRepository.save(noEjb);


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "65434523");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(sgfPublisher);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        System.out.println("\n\n" +
                "Started in bootstrap");
        System.out.println("    Number of Books: " + bookRepository.count());
        System.out.println("    Number of Authors: " + authorRepository.count());
        System.out.println("    Number of Publihers: " + publisherRepository.count());

        System.out.printf("    Publisher : %s\n", sgfPublisher.toString());
        System.out.printf("    Publisher : %s\n", oreileyPublisher.toString());
    }

}
