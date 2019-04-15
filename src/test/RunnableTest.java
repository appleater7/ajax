package test;

class Test implements Runnable {
	public void run() {
		System.out.println("실행");
	}
}

public class RunnableTest {
	
	public static void main(String[] args) {
		Runnable r = new Test();
		Thread t = new Thread(r);
		t.start();
	}
}
