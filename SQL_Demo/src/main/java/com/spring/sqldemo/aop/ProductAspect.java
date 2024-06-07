package com.spring.sqldemo.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spring.sqldemo.entity.Product;

@Aspect
@Component
public class ProductAspect {

	Logger logger = LoggerFactory.getLogger(ProductAspect.class);
	
	@Before(value = "execution(* com.spring.sqldemo.controller.SQLDemoController.*(..))")
	public void beforeAspect(JoinPoint joinPoint) {
		logger.info("Before - Request to "+joinPoint.getSignature()+" started at "+new Date());
	}
	
	@After(value = "execution(* com.spring.sqldemo.controller.SQLDemoController.*(..))")
	public void afterAspect(JoinPoint joinPoint) {
		logger.info("After - Request to "+joinPoint.getSignature()+" ended at "+new Date());
	}
	
	@Before(value = "execution(* com.spring.sqldemo.service.ProductService.*(..))")
	public void beforeAspectForService(JoinPoint joinPoint) {
		logger.info("Before - Request to ProductService "+joinPoint.getSignature()+" started at "+new Date());
	}
	
	@After(value = "execution(* com.spring.sqldemo.service.ProductService.*(..))")
	public void afterAspectForService(JoinPoint joinPoint) {
		logger.info("After - Request to ProductService "+joinPoint.getSignature()+" ended at "+new Date());
	}
	
	@AfterReturning(value = "execution(* com.spring.sqldemo.service.ProductService.getProductById*(..))", returning = "product")
	public void afterReturningAspectForService(JoinPoint joinPoint, Product product) {
		logger.info("AfterReturning - Request to ProductService.getProductById to get the product "+product);
	}
	
	
	@AfterThrowing(value = "execution(* com.spring.sqldemo.service.ProductService.getProductById(..))", throwing = "e")
	public void afterThrowingAspectForService(JoinPoint joinPoint, Exception e) {
		logger.info("AfterThrowing - Exception occurred while getting the product" +e.getMessage());
	}
	
//	@Around(value = "execution(* com.spring.sqldemo.service.ProductService.getProductById(..))")
//	public void aroundAspectForService(ProceedingJoinPoint joinPoint){
//		logger.info("Around - Request to ProductService.getProductById "+joinPoint.getSignature()+" started at "+new Date());
//		try {
//			joinPoint.proceed();
//		}catch(Throwable e) {
//			logger.error("Around - Product details not available for this id "+e.getMessage());
//		}
//		logger.info("Around - Request to ProductService.getProductById "+joinPoint.getSignature()+" ended at "+new Date());
//	}
//	
	
}
