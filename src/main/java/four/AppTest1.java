package four;
//四个线程执行两个任务
class Num {
	public int j = 0;

}

class Add implements Runnable {
	private Num n;

	public Add(Num n) {
		this.n = n;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (n) {
				while (n.j >= 200) {
					try {
						n.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread() + "又买了第" + (++n.j) + "个苹果");
				n.notifyAll();
			}
		}
	}
}

class Mult implements Runnable {
	private Num n;

	public Mult(Num n) {
		this.n = n;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (n) {
				while (n.j <= 0) {
					try {
						n.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread() + "又吃了第" + (n.j--) + "个苹果");
				n.notifyAll();
			}
		}
	}
}

public class AppTest1 {

	public static void main(String[] args) {
		Num n = new Num();
		Add a = new Add(n);
		Mult m = new Mult(n);
		Thread th = new Thread(a, "李宁");
	    Thread th1 = new Thread(a, "李宁的狗");
		Thread th2 = new Thread(m, "张力晨");
		Thread th3 = new Thread(m, "张力晨的喵");
		th.start();
		th1.start();
		th2.start();
		th3.start();
	}

}
