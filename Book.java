/* This class represents a book, which has a title, author,
year of publication and different literary aspects*/
public class Book {
	/**
	 * The title of this book.
	 */
	final String title;
	
	/**
	 * The name of the author of this book.
	 */
	final String author;
	
	/**
	 * The year this book was published.
	 */
	final int yearOfPublication;
	
	/**
	 * The comic value of this book.
	 */
	int comicValue;
	
	/**
	 * The dramatic value of this book.
	 */
	int dramaticValue;
	
	/**
	 * The educational value of this book.
	 */
	int educationalValue;
	
	/**
	 * The position of the book if returned or not
	 */
	boolean bookIsReturned = true;
	
	/**
	 * The id of the current borrower of this book.
	 */
	int currentBorrowerId = -1;
	
	/**
	 * The id of the book id it belongs to a library
	 */
	int bookId = -1;
	
	/**
	 * @param bookTitle             The title of this book.
	 * @param bookAuthor            The name of the author of this book.
	 * @param bookYearOfPublication The year this book was published.
	 * @param bookComicValue        The comic value of this book.
	 * @param bookDramaticValue     The dramatic value of this book.
	 * @param bookEducationalValue  The educational value of this book.
	 */
	Book(String bookTitle, String bookAuthor, int bookYearOfPublication,
	     int bookComicValue, int bookDramaticValue, int bookEducationalValue) {
		title = bookTitle;
		author = bookAuthor;
		yearOfPublication = bookYearOfPublication;
		comicValue = bookComicValue;
		dramaticValue = bookDramaticValue;
		educationalValue = bookEducationalValue;
	}
	
	/**
	 * Returns a string representation of the book
	 */
	String stringRepresentation() {
		int totalValue = comicValue + educationalValue +
				dramaticValue;
		return '[' + title + ',' + author + ','
				+ yearOfPublication + ',' + totalValue + ']';
		
	}
	
	/**
	 * Returns the literary value of this book
	 */
	int getLiteraryValue() {
		return comicValue + dramaticValue + educationalValue;
	}
	
	/**
	 * Sets the given id as the id of the current borrower of this book
	 */
	void setBorrowerId(int borrowerId) {
		currentBorrowerId = borrowerId;
	}
	
	/**
	 * The id of the current borrower of this book
	 */
	
	int getCurrentBorrowerId() {
		return currentBorrowerId;
	}
	
	/**
	 * Marks this book as returned
	 */
	void returnBook() {
		bookIsReturned = true;
		setBorrowerId(-1);
	}
	
	/**
	 * Giving an Id for the book
	 */
	void setBookId(int id) {
		bookId = id;
	}
	
	/**
	 * Return the book ID
	 */
	int getBookId() {
		return bookId;
	}
	
	/**
	 * sets the book as borrowed
	 */
	void setBookBorrowed() {
		bookIsReturned = false;
	}
}


