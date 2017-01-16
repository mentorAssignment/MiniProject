package model;

public class Books {
	private int bookid;
	private String title;
	private String author;
	private int genre;
	private int status;
	private String genreString;
	private String creationDate;
	private int createdBy;
	private String modificationDate;
	private int modifiedBy;
	private String description;
	
	public Books(){
		
	}
	public Books(int id,String title, String author, String description, String genre) {
		this.title = title;
		this.description = description;
		this.author = author;
		this.bookid = id;
		this.genreString = genre;
	}
	public Books(int bookid, String title, String author, int genre, int status, String creationDate, int i,
			String modificationDate, int modifiedBy, String description) {
		super();
		this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.status = status;
		this.creationDate = creationDate;
		this.createdBy = i;
		this.modificationDate = modificationDate;
		this.modifiedBy = modifiedBy;
		this.description = description;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenreString() {
		
		return genreString;
	}
	public void setGenreString(String genre) {
		this.genreString = genre;
	}
}
