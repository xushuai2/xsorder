package com.example.xs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xs.aop.RpcReference;
import com.example.xs.bean.Product;
import com.example.xs.good.IProductService;
import com.example.xs.good.ITestXuRpcService;

@RestController  
public class OrderController2 {
	
	@RpcReference
	IProductService productService;
	
	@RpcReference
	ITestXuRpcService iTestXuRpcService;
	
	 @RequestMapping("/rpc/hello")  
	 public String hell(String name) { 
		String result = productService.hello("xushuai");
		System.out.println(result);
		return result;  
	 }  
	 
	 @ResponseBody
	 @RequestMapping("/rpc/get/good")
	 public Product getGood(Long id){
        Product product = productService.queryById(100);
        System.out.println(product);
		return product;
	 }
	 
	 @ResponseBody
	 @RequestMapping("/rpc/update")
	 public Product update(){
        Product product = productService.queryById(100);
        System.out.println("修改前："+product);
        product = iTestXuRpcService.update(product);
        System.out.println("修改后："+product);
		return product;
	 }
	 
}
