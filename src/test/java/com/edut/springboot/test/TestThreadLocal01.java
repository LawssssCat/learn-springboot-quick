package com.edut.springboot.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ThreadLocal
 * 1)Java中的一个API对象，此对象提供了一种线程绑定记住，可以将某个对象绑定到当前线程
 * 2)常用方法
 * 	a)get() 从当前线程获取绑定的对象
 * 	b)set() 将当前对象绑定到当前线程
 * 	c)......................
 */
class DateUtils{
//	方案1==========  频繁创建爱你SimpleDateFormat，可能频繁触发GC	
//	
//	public synchronized static String formart(Date date) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd") ;
//		return sdf.format(date) ; 
//	}
	
//	方案2==========  多线程共享SimpleDateFormat，但是要加锁，避免线程不安全问题，导致大量阻塞，CPU在多线程来回切换，甚至出现OOM
//	
//	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd") ;//线程不安全
//	public synchronized static String formart(Date date) {
//		return sdf.format(date) ; 
//	}
	
//	方案3==========  既要考虑线程，又要考虑安全，减少对象创建次数-一个线程一个对象
	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			System.out.println(1);
			return new SimpleDateFormat();
		};
	};
	
	public static String formart(Date date ) {
		return threadLocal.get().format(date) ; 
	}
}


public class TestThreadLocal01 {
	
	public static void main(String[] args) {
		for(int i=0 ; i<5 ;i++) {
			new Thread() {
				public void run() {
					DateUtils.formart(new Date()) ; 
					DateUtils.formart(new Date()) ; 
					DateUtils.formart(new Date()) ; 
					DateUtils.formart(new Date()) ; 
					DateUtils.formart(new Date()) ; 
					DateUtils.formart(new Date()) ; 
				};
			}.start();
		}
	}
}
