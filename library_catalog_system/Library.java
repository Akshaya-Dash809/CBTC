package library_catalog_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library { //Library Class(Main class)
	
	private List<Book> books;
	
	public Library() {
		books = new ArrayList<>();
	}
	
	public void addBook(String title,String author) { //addBook() method for creating new book 
		books.add(new Book(title,author));
		System.out.println("Book added successfully. ");
	}

	public void searchByTitle(String title) {  //searchByTitle() method for searching book by it's  title
		boolean found=false;
		for(Book book : books) {
			if(book.getTitle().equalsIgnoreCase(title)) {
				System.out.println(book);
				found=true;
			}
		}
		
		if(!found) {
			System.out.println("No book found with the title: "+title);
		}	
	}
	
	public void searchByAuthor(String author) { //searchByAuthor() method for searching book by it's Author name
		boolean found=false;
		for(Book book : books) {
			if(book.getAuthor().equalsIgnoreCase(author)) {
				System.out.println(book);
				found = true;
			}
		}
		if(!found) {
			System.out.println("No books found by the author: "+author);
		}
		
	}
	public void listBooks() {  
		if(books.isEmpty()) {
			System.out.println("No books available in the catalog !!!");
		}
		else {
			System.out.println("Books found in the catalog : ");
			for(Book book : books) {
				System.out.println(book);
			}
		}
	}
	public static void main(String[] args) {
		Library library_cat= new Library();
		Scanner scanner= new Scanner(System.in);
		String title,author;
		int choice;
		//All catalog operation can be done byBy using do-while and switch 
		do { 
			System.out.println("\nLibrary Catalog System:");
			System.out.println("1. Add Book");
			System.out.println("2. Search By Title");
			System.out.println("3. Search By Author");
			System.out.println("4. List All Books");
			System.out.println("5. Exit");
			System.out.println("Enter Your Choice: ");
			choice =scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1:
				System.out.println("Enter book title: ");
				title=scanner.nextLine();
				System.out.println("Enter book author: ");
				author=scanner.nextLine();
				library_cat.addBook(title, author);
				break;
			case 2:
				System.out.println("Enter title to search: ");
				title=scanner.nextLine();
				library_cat.searchByTitle(title);
				break;
			case 3:
				System.out.println("Enter author to search: ");
				author=scanner.nextLine();
				library_cat.searchByAuthor(author);
				break;
			case 4:
				library_cat.listBooks();
				break;
			case 5:
				System.out.println("Exiting from the system.");
				break;
				default:
					System.out.println("Invalid choice.Please try again.");
			}
		}
		while(choice != 5) ;
			scanner.close();
		}
	
}
