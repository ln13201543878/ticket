package ticket;


//�ĸ��߳�ִ��һ������


class A implements Runnable {
	private int i = 1000;


	@Override
	public void run() {
		while (i >=1) {
			synchronized (this) {
				i--;
				System.out.println(Thread.currentThread() + "�����˵�" + i + "��Ʊ");
			}
		
	}
	}

}

public class AppTest {
	public static void main(String[] args) {
		
		A a = new A();
		Thread th = new Thread(a);
		Thread th1 = new Thread(a);
		Thread th2 = new Thread(a);
		Thread th3 = new Thread(a);
		th.start();
		th1.start();
		th2.start();
		th3.start();
	}

}
