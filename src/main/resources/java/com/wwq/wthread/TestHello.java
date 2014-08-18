package com.wwq.wthread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestHello {

	private final List<Integer> listInteger = new CopyOnWriteArrayList<Integer>();
	private final List<Double> listDouble = new CopyOnWriteArrayList<Double>();
	
	public void addInteger(int i){
		listInteger.add(i);
	}
	
	public void addDouble(double i){
		listDouble.add(i);
	}
	
	public void removeInteger(int i){
		listInteger.remove(i);
	}
	
	public void removeDouble(double i){
		listDouble.remove(i);
	}
	
	public void printInteger(){
		for(Integer i:listInteger)
			System.out.println("Integer : " + i);
	}
	
	public void printDouble(){
		for(Double i:listDouble)
			System.out.println("Double : " + i);
	}
}
