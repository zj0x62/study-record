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
