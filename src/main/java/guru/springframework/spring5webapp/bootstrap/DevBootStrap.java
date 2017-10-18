package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    // DI by constructor
    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public void initData(){
        //Eric
        Author eric = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "1234");
        Publisher publisher = new Publisher("Harper Collins", "27 Mount Hope");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        book1.setPublisher(publisher);

        // Save to repos
        publisherRepository.save(publisher); // PK field
        authorRepository.save(eric); // PK field
        bookRepository.save(book1); // FK Field, so PK fields must be added to databse first



        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book book2 = new Book("J2EE Development without EJB", "23444" );
        Publisher publisher1 = new Publisher("Wrox", "3560 Cham");
        rod.getBooks().add(book2);
        book2.getAuthors().add(rod);
        book2.setPublisher(publisher1);

        // Save to repos
        publisherRepository.save(publisher1); // PK field
        authorRepository.save(rod); // PK field
        bookRepository.save(book2); // FK Field, so PK fields must be added to databse first

    }

}
