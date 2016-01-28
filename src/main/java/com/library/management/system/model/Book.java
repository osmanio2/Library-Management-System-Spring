package com.library.management.system.model;
import javax.persistence.*;

@Entity
@Table(name = "library_book_details")
public class Book {
	 @Id
	   @Column(name = "BOOK_ISBN")
	   private String isbn;

		@Column(name = "BOOK_TITLE")
		 private String title;
		 
		 @Column(name = "BOOK_EDITION")
		 private String edition;
		 
		 @Column(name = "CATEGORY")
		 private String category;
		 
		 @Column(name = "AUTHOR")
		 private String author;
		 		

		@Column(name = "NUMBER_OF_ITEMS")
		private int numOfItems;
				 		
	     @Column(name = "BOOK_STATUS")
	     private String status;
	     
	     @Column(name = "NumberOfBorrowed")
	     private int numOfBorrowed;

	 
	 /**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @param edition the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the numOfItems
	 */
	public int getNumOfItems() {
		return numOfItems;
	}

	/**
	 * @param numOfItems the numOfItems to set
	 */
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the numOfBorrowed
	 */
	public int getNumOfBorrowed() {
		return numOfBorrowed;
	}

	/**
	 * @param numOfBorrowed the numOfBorrowed to set
	 */
	public void setNumOfBorrowed(int numOfBorrowed) {
		this.numOfBorrowed = numOfBorrowed;
	}

}
