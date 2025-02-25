package com.example.demo.lock;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author: zhoujing
 * @Date: 2025/2/24 11:02
 * @Description: 管道
 */
public class Pipe {

    public static void main(String[] args) throws Exception {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();
        writer.connect(reader);

        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(1000);
        new Thread(new WriterThread(writer)).start();
    }

    static class ReaderThread implements Runnable {
        private PipedReader reader;

        public ReaderThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            try {
                while ((receive = reader.read()) != -1) {
                    System.out.println((char) receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable {
        private PipedWriter writer;

        public WriterThread(PipedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            int receive = 0;
            try {
                writer.write("test");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
