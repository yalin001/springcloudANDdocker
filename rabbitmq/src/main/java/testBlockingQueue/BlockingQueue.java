package testBlockingQueue;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue {
	
	static ArrayBlockingQueue<String> q = new ArrayBlockingQueue<String>(5);
	
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				System.out.println("输入：");
				String s = new Scanner(System.in).nextLine();
				q.offer(s);
			};
		}.start();
		
		new Thread() {
			public void run() {
				String s;
				try {
					s=q.take();//阻塞等待队列中的数据
					System.out.println("输出："+s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			};
		}.start();
		
	}
	
}
