package library_catalog_system;

public class Book {  //Book class

	private String title;
	private String author;
	
	public Book(String title,String author) { //Parameterized Constructor with 2 parameters title&author for creating book objects
		this.title=title;
		this.author=author;
	}
	//get() method for returning respective values i.e title,author of Book
	public String getTitle() {  
		return title;
	}
	public String getAuthor() {
		return author;
	}
	
	@Override
	public String toString() {  //Override toString() method which returns a string representation of the object.
		return "Title: " + title + ", Author: "+author;
	}
}
