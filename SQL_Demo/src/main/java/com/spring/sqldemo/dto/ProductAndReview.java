package com.spring.sqldemo.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.sqldemo.entity.Review;

@Component
public class ProductAndReview {

	    private String name;
	    private double price;
	    private String category;
	    private List<String> tags;
	    private List<Review> reviews;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public List<String> getTags() {
			return tags;
		}
		public void setTags(List<String> tags) {
			this.tags = tags;
		}
		public List<Review> getReviews() {
			return reviews;
		}
		public void setReviews(List<Review> reviews) {
			this.reviews = reviews;
		}
	    
	    
}
