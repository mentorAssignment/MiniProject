package model;

public class Review {
	private int reviewId;
	private int rating;
	private String comment;
	public Review(int reviewId,int rating,String comment)
	{
		this.reviewId=reviewId;
		this.rating=rating;;
		this.comment=comment;
	}
	public int getReviewId()
	{
		return reviewId;
	}
	public int getRating() {
		return rating;
	}
	public String getComment() {
		return comment;
	}
}