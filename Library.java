public class Library {
	/**
	 * The maximal number of books this library can hold.
	 */
	final int bookCapacity;
	/**
	 * The maximal number of books this library allows a single patron to borrow at the same time.
	 */
	final int borrowedBooks;
	/**
	 * The maximal number of registered patrons this library can handle.
	 */
	final int patronCapacity;
	/**
	 * The shelves were the book are stored
	 */
	Book[] bookShelves;
	/**
	 * Where the patron are registered
	 */
	Patron[] patronsNumbers;
	
	int[] patronBooks;
	
	/**
	 * @param maxBookCapacity   The maximal number of books this library can hold.
	 * @param maxBorrowedBooks  The maximal number of books this library allows a single
	 *                          patron to borrow at the same time.
	 * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
	 */
	Library(int maxBookCapacity, int maxBorrowedBooks,
	        int maxPatronCapacity) {
		bookCapacity = maxBookCapacity;
		borrowedBooks = maxBorrowedBooks;
		patronCapacity = maxPatronCapacity;
		bookShelves = new Book[bookCapacity];
		patronsNumbers = new Patron[maxPatronCapacity];
		patronBooks = new int[maxPatronCapacity];
	}
	
	/**
	 * Adds the given book to this library, if there is place available, and it
	 * isn't already in the library.
	 */
	int addBookToLibrary(Book book) {
		
		if (book.getBookId() != -1) {
			return book.getBookId();
		}
		
		for (int i = 0; i < bookShelves.length; i++) {
			if (bookShelves[i] == book) {
				return i;
			}
			
			if (bookShelves[i] == null) {
				bookShelves[i] = book;
				book.setBookId(i);
				
				return book.getBookId();
			}
		}
		return -1;
	}
	
	/**
	 * @return true if the given number is an id of some book in the library,
	 * false otherwise.
	 */
	boolean isBookIdValid(int bookId) {
		
		return (0 <= bookId && bookId < bookShelves.length && bookShelves[bookId] != null);
//		}
		
	}
	
	/**
	 * @return the non-negative id number of the given book if he is owned by
	 * this library, -1 otherwise.
	 */
	int getBookId(Book book) {
		if (0 <= book.getBookId() && book.getBookId() < bookCapacity) {
			return book.getBookId();
		}
		return -1;
	}
	
	boolean isBookAvailable(int bookId) {
		if (bookId >= 0 && bookId < bookShelves.length) {
			return bookShelves[bookId] != null && bookShelves[bookId].getCurrentBorrowerId() == -1;
		}
		return false;
	}
	
	/**
	 * Registers the given Patron to this library, if there is a spot available.
	 *
	 * @return a non-negative id number for the patron if there was a spot and
	 * the patron was successfully registered or if the patron was already registered.
	 * a negative number otherwise.
	 */
	int registerPatronToLibrary(Patron patron) {
		if (patron.getPatronId() != -1) {
			return patron.getPatronId();
		}
		for (int i = 0; i < patronsNumbers.length; i++) {
			if (patronsNumbers[i] == patron) {
				return i;
			}
			if (patronsNumbers[i] == null) {
				patron.setPatronId(i);
				patronsNumbers[i] = patron;
				return patron.getPatronId();
			}
		}
		return -1;
	}
	
	/**
	 * @return true if the given number is an id of a patron in the library,
	 * false otherwise.
	 */
	boolean isPatronIdValid(int patronId) {
		if (patronId >= 0 && patronId < patronsNumbers.length) {
			return patronsNumbers[patronId] != null;
		}
		return false;
	}
	
	/**
	 * @return the non-negative id number of the given patron if he is registered
	 * to this library, -1 otherwise.
	 */
	int getPatronId(Patron patron) {
		if (0 <= patron.getPatronId() && patron.getPatronId() < patronCapacity) {
			return patron.getPatronId();
		}
		return -1;
	}
	
	/**
	 * Marks the book with the given id number as borrowed by the patron with
	 * the given patron id, if this book is available, the given patron isn't
	 * \already borrowing the maximal number of books allowed, and if the patron
	 * will enjoy this book.
	 */
	boolean borrowBook(int bookId, int patronId) {
		if (!isBookAvailable(bookId) || !isPatronIdValid(patronId)) {
			return false;
		}
		if (patronsNumbers[patronId].getPatronBorrowedBooks() < patronCapacity
				&& patronsNumbers[patronId].willEnjoyBook(bookShelves[bookId])) {
			bookShelves[bookId].setBookBorrowed();
			bookShelves[bookId].setBorrowerId(patronId);
			patronBooks[patronId]++;
			return true;
		}
		return false;
	}
	
	/**
	 * Return the given book.
	 */
	void returnBook(int bookId) {
		--patronBooks[bookShelves[bookId].getCurrentBorrowerId()];
		bookShelves[bookId].returnBook();
	}
	
	/**
	 * Suggest the patron with the given id the book he will enjoy the most,
	 * out of all available books he will enjoy, if any such exist.
	 *
	 * @return The available book the patron with the given ID will enjoy the most.
	 * Null if no book is available.
	 */
	Book suggestBookToPatron(int patronId) {
		int maxBookScore = 0;
		Book bookToReturn = null;
		if (!isPatronIdValid(patronId)) {
			return null;
		}
		for (int j = 0; j < bookShelves.length; j++) {
			if (patronsNumbers[patronId].getBookScore(bookShelves[j]) > maxBookScore &&
					isBookIdValid(j) && patronsNumbers[patronId].willEnjoyBook(bookShelves[j])) {
				maxBookScore = bookShelves[j].getBookId();
				bookToReturn = bookShelves[j];
			}
		}
		return bookToReturn;
	}
}
	

