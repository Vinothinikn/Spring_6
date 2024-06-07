package com.example.demo.service;

public interface MyDefaultInterface {

	default void myDefaultMethod() {
		System.out.println("Default method called");
	}
}
