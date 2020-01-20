package com.edut.springboot.test;

/**
 * 设计：将IntContainer类设计为单例对象(此类的实例在内存中只能有一份)
 * 
 * 
 * ---------------------
 * volatile(1.禁止指令重排序。2.保证线程可见性。3.不能保证原子性)
 * 
 * 
 * 
 * @author Administrator
 *
 */
class IntContainer {
	//has a array 
	static int [] array = new int[1024] ; //1024*4=4096=4k
	//构造方法私有化
	private  IntContainer() {System.out.println("==IntContainer==");} 
	//定义一个变量
	private volatile static IntContainer instance ; //volatile 禁止指令重排序 
	public static IntContainer getInstance() { // 双重非空检验
		if(instance==null) {
			synchronized (IntContainer.class) {
				if(instance==null) {
					instance = new IntContainer() ; 
				}
			}
		}
		return instance ; 
	}
	public void addFirst(int num) {
		array[0] = num ; 
	}
	
}

public class TestSingleton01 {
	public static void main(String[] args) {
		for(int i=0 ; i<10000 ; i++) {
			int a = i ; 
			new Thread() {
				public void run() {
					IntContainer.getInstance().addFirst(a); ;
					//1.开辟内存
					//2.为属性进行初始化
					//3.执行构造方法
					//4.将内存地址赋值给intContainer变量
					
					//JVM 指令重排序 ==> volatile 禁止指令重排序
				};
			}.start();
		}
	}
	
}
