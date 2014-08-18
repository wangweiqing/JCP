package com.wwq.wthread;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	public long timeTasks(int nThreads, final Runnable task)
			throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException ignored) {
					}
				}
			};
			t.start();
		}

		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;
	}

	public static void main(String[] args) {
		TestHarness testHarness = new TestHarness();
		SimpleTask simpleTask = new SimpleTask();
		try {
			long waste = testHarness.timeTasks(10, simpleTask);
			System.out.println("Waste time : " + waste);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
