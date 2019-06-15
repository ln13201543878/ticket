package four;
//四个线程执行两个任务
class Apple {
	public int i = 0;
}

class Buy implements Runnable {
	private Apple a;

	public Buy(Apple a) {
		this.a = a;
	}

	@Override
	public void run() {
		while(true) {
		synchronized (a) {
			while(a.i>=200) {
				try {
					a.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread() + "买了第" + (++a.i) + "个苹果");
			a.notifyAll();
		}

	}
		}

}

class Sale implements Runnable {
	private Apple a;

	public Sale(Apple a) {
		this.a = a;
	}

	@Override
	public void run() {
		while(true) {
		synchronized (a) {
			while(a.i<=0) {
				try {
					a.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread() + "卖了第" + (a.i--) + "个苹果");
			a.notifyAll();
		}
	}
	}
}

public class AppTest2 {
	public static void main(String[] args) {
		Apple a = new Apple();
		Buy b = new Buy(a);
		Sale s = new Sale(a);
		Thread th = new Thread(b, "张三");
		Thread th1 = new Thread(b, "李四");
		Thread th2 = new Thread(s, "王武");
		Thread th3 = new Thread(s, "马六");
		th.start();
		th1.start();
		th2.start();
		th3.start();
	}

}
