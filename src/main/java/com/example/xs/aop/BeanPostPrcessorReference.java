package com.example.xs.aop;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
@Component  
public class BeanPostPrcessorReference implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getType().isInterface()) {
                	if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                	RpcReference reference = field.getAnnotation(RpcReference.class);
                    if (reference != null) {
                    	System.out.println("************************************");
                        Object value = this.rpc(field.getType());
                        if (value != null) {
                            field.set(bean, value);
                        }
                    }
                }
                
            } catch (Exception e) {
                throw new BeanInitializationException("Failed to init remote service reference at filed " + field.getName() + " in class " + bean.getClass().getName(), e);
            }
        }
        return bean;
        
	}
	
	public static Object rpc(final Class clazz){
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
    }

}
