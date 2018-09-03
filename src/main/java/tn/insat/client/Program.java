package tn.insat.client;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import tn.insat.entities.Author;
import tn.insat.entities.Book;
import tn.insat.entities.Publisher;



@SuppressWarnings({"rawtypes","unchecked"})
public class Program 
{
	public static void main( String[] args )
    {
		//Getting Session Factory
		Configuration configuration =  new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                 configuration.getProperties()).build();            

    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	
    	//Getting Session
		Session session = sessionFactory.openSession();
		
		//Beginning Transaction
		Transaction tx = session.beginTransaction();

		//Creating two books with their author and publisher
		Author aut1 = new Author("Alain BADIOU", "FRA", new HashSet(0));
		Author aut2 = new Author("Charles BUKOWSKI", "USA", new HashSet(0));
		
		Publisher p1 = new Publisher("Circonstances, 5", new HashSet(0));
		Publisher p2 = new Publisher("Le Livre de Poche", new HashSet(0));
		
		Book b1 = new Book(p1, "L'Hypothese Communiste", 205, new HashSet(0));
		Book b2 = new Book(p2, "Contes de la folie ordinaire", 254, new HashSet(0));
	
		b1.getAuthors().add(aut1);
		b1.setPublisher(p1);
		b2.getAuthors().add(aut2);
		b2.setPublisher(p2);
		
		//Saving entities
		session.save(aut1);
		session.save(aut2);
		
		session.save(p1);
		session.save(p2);
		
		session.save(b1);
		session.save(b2);
		
		//Committing and closing session 
		tx.commit();
		session.close();
		
		// Fix the program termination by closing the sessionFactory
		sessionFactory.close();
		System.out.println("Fin de l'initialisation de la BDD ....");
    }
}
