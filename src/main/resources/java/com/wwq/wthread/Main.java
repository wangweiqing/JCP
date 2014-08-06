package com.wwq.wthread;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*LazyInitRace lazyInitRace = new LazyInitRace();*/
		for (int i=0;i<=10;i++){
			String name = "Thread" + i;
			String new_name = "Thread_NEW_"+i;
			TestThread t1 = new TestThread(name);
			TestThread t2 = new TestThread(new_name);
			t1.run();
			t2.run();
		}
	}

}
