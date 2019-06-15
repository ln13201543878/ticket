package four;
//�ĸ��߳�ִ����������
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
			System.out.println(Thread.currentThread() + "���˵�" + (++a.i) + "��ƻ��");
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
			System.out.println(Thread.currentThread() + "���˵�" + (a.i--) + "��ƻ��");
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
		Thread th = new Thread(b, "����");
		Thread th1 = new Thread(b, "����");
		Thread th2 = new Thread(s, "����");
		Thread th3 = new Thread(s, "����");
		th.start();
		th1.start();
		th2.start();
		th3.start();
	}

}
