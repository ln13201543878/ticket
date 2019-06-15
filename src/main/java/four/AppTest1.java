package four;
//�ĸ��߳�ִ����������
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
				System.out.println(Thread.currentThread() + "�����˵�" + (++n.j) + "��ƻ��");
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
				System.out.println(Thread.currentThread() + "�ֳ��˵�" + (n.j--) + "��ƻ��");
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
		Thread th = new Thread(a, "����");
	    Thread th1 = new Thread(a, "�����Ĺ�");
		Thread th2 = new Thread(m, "������");
		Thread th3 = new Thread(m, "����������");
		th.start();
		th1.start();
		th2.start();
		th3.start();
	}

}
