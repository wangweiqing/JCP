package com.wwq.wthread;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*LazyInitRace lazyInitRace = new LazyInitRace();*/
/*		for (int i=0;i<=10;i++){
			String name = "Thread" + i;
			String new_name = "Thread_NEW_"+i;
			TestThread t1 = new TestThread(name);
			TestThread t2 = new TestThread(new_name);
			t1.run();
			t2.run();
		}*/
		TestHello t1 = new TestHello();
		int i = 0;
		for(;i<10;i++){
			t1.addInteger(i);
			t1.addDouble((double)i);
		}
		t1.printInteger();
		t1.printDouble();
	}

}
