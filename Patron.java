/* This class represents a patron, with first and last name, literary
aspects and enjoyment threshold */
public class Patron {
    /**
     * The patron first Name
     */
    final String firstName;
    /**
     * The patron Last name
     */
    final String lastName;
    /**
     * The weight the patron assigns to the educational aspects of books.
     */
    int comicTendence;
    /**
     * The weight the patron assigns to the dramatic aspects of books.
     */
    int dramaticTendence;
    /**
     * The weight the patron assigns to the educational aspects of books.
     */
    int educationalTendence;
    /**
     * The patrons enjoyment threshold
     */
    int enjoymentThreshold;

    /** The patron id when registered to a library*/
    int patronId = -1;
    
    /** The number of the borrowed books*/
    int patronBorrowedBooks;


    /**
     * @param patronFirstName          The first name of the patron.
     * @param patronLastName           The last name of the patron.
     * @param comicTendency            The weight the patron assigns to the comic
     *                                 aspects of books.
     * @param dramaticTendency         The weight the patron assigns to the dramatic
     *                                 aspects of books.
     * @param educationalTendency      The weight the patron assigns to the educational
     *                                 aspects of books.
     * @param patronEnjoymentThreshold The minimal literary value a book must have
     *                                 for this patron to enjoy it.
     */
    Patron(String patronFirstName, String patronLastName,
           int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold) {
        firstName = patronFirstName;
        lastName = patronLastName;
        comicTendence = comicTendency;
        dramaticTendence = dramaticTendency;
        educationalTendence = educationalTendency;
        enjoymentThreshold = patronEnjoymentThreshold;
    }

    /**
     * @return a string representation of the patron,
     * which is a sequence of its first and last name, separated by
     * a single white space.
     */
    String stringRepresentation() {
        return firstName + " " + lastName;
    }

    /**
     * @return the literary value this patron assigns to the given book.
     */
    int getBookScore(Book book) {
        int comic = book.comicValue * comicTendence;
        int dramatic = book.dramaticValue * dramaticTendence;
        int educational = book.educationalValue * educationalTendence;
        return comic + dramatic + educational;
    }

    /**
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book) {
        return enjoymentThreshold <= getBookScore(book);
    }

    /** setting the id of the patron when registered to a library*/
    void setPatronId(int id) {
        patronId = id;
    }

    /** @return the id of the patron in the library*/
    int getPatronId() {
        return patronId;
    }

    void setPatronBorrowedBooks(int numberOfBooks) {
        patronBorrowedBooks = numberOfBooks;
    }
    /** @return the number of the borrowed books*/
    int getPatronBorrowedBooks() {
        return patronBorrowedBooks;
    }
}
