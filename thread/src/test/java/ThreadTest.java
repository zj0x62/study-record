import org.apache.tomcat.util.threads.TaskQueue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: zhoujing
 * @Date: 2025/2/18 15:07
 * @Description:
 */
public class ThreadTest {

    @Test
    public void test() throws Exception {
        TaskQueue taskQueue = new TaskQueue();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        String task = taskQueue.getTask();
                        System.out.println("execute task: " + task);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            thread.start();
            list.add(thread);
        }

        Thread add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                taskQueue.addTask(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread thread : list) {
            thread.interrupt();
        }
    }

    @Test
    public void blockedTest() {
        for (int i = 0; i < 10; i++) {
            blocked();
            System.out.println("------------------");
        }

        // 输出有两种结果：1. a:RUNNABLE b:BLOCKED (一个线程已获得锁，另一个被阻塞（BLOCKED）)
        //              2. a:RUNNABLE b:RUNNABLE (两个线程均未开始竞争锁，均处于 RUNNABLE 状态)
    }

    private void blocked() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        b.start();
        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }

    private synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class TaskQueue {
        Queue<String> queue = new LinkedList<>();

        public synchronized void addTask(String s) {
            this.queue.add(s);
            this.notifyAll();
        }

        public synchronized String getTask() throws InterruptedException {
            while (queue.isEmpty()) {
                this.wait();
            }
            return queue.remove();
        }
    }
}
