package com.wwq.wthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestThread implements Runnable {

/*	private LazyInitRace lazyInitRace;*/
	
/*	public TestThread(LazyInitRace lazyInitRace){
		this.lazyInitRace = lazyInitRace;
	}*/
	private String name;
	
	public TestThread(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
/*		ExpensiveObject obj = lazyInitRace.getInstance();
		System.out.println(this.toString()+" : " + obj.toString());*/
		try {
			for (int i = 0; i < 10; i++) {
				URL url = new URL("http://localhost:8080/concurrency/test2");
				URLConnection conn = url.openConnection();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String line = null;
				System.out.println("The thread : " + this.name);
				while ((line = reader.readLine()) != null) {

					System.out.println(line);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
