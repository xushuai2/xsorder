package com.example.xs.controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xs.aop.RpcReference;
import com.example.xs.bean.Product;
import com.example.xs.good.IProductService;

//@RestController  
public class OrderController {
	
//	@RpcReference
//	IProductService productService;
	
/*	 @RequestMapping("/hello")  
	 public String hell(String name) { 
		IProductService productService = (IProductService)rpc(IProductService.class);
		String result = productService.hello("xushuai");
		System.out.println(result);
		return result;  
	 }  
	 
	 @ResponseBody
	 @RequestMapping("/get/good")
	 public Product getGood(Long id){
		IProductService productService = (IProductService)rpc(IProductService.class);
        Product product = productService.queryById(100);
        System.out.println(product);
		return product;
	 }
	 */
	/* public static Object rpc(final Class clazz){
	    	//动态代理  代理类在程序运行前不存在、运行时由程序动态生成的代理方式称为动态代理。
	        return Proxy.newProxyInstance(clazz.getClassLoader(),
	                new Class[]{clazz}, new InvocationHandler() {
	                    @Override
	                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	                    	System.out.println("进入invoke");
	                        Socket socket = new Socket("127.0.0.1",8888);

	                        //我们想远程调用哪个类的哪个方法,并传递给这个方法什么参数
	                        //注意我们只知道Product API,并不知道Product API在Product的实现
	                        String apiClassName = clazz.getName();
	                        String methodName = method.getName();
	                        Class[] parameterTypes = method.getParameterTypes();

	                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
	                        objectOutputStream.writeUTF(apiClassName);
	                        objectOutputStream.writeUTF(methodName);
	                        objectOutputStream.writeObject(parameterTypes);
	                        objectOutputStream.writeObject(args);
	                        objectOutputStream.flush();

	                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
	                        Object o = objectInputStream.readObject();
	                        objectInputStream.close();
	                        objectOutputStream.close();

	                        socket.close();
	                        return o;
	                    }
	                });
	    }*/
	 
	 
}
