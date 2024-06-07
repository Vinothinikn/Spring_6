package com.example.demo.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.service.MyDefaultInterface;
import com.example.demo.service.MyFunctionalInterface;
import com.example.demo.service.MyStaticInterface;

@Service
public class ServiceImplementationClass implements MyDefaultInterface, MyStaticInterface, MyFunctionalInterface{

	public void myDefaultMethod() {
		System.out.println("ServiceImplementationClass method implemented");
	}
	
	@Override
	public void abstractMethod() {
		System.out.println("Abstract method implemented");
	}
	
	public void remainingMethod() {
		ServiceImplementationClass serviceImpl = new ServiceImplementationClass();
		serviceImpl.abstractMethod();
		serviceImpl.myDefaultMethod();
		MyStaticInterface.myStaticMethod();
		
	}

}
