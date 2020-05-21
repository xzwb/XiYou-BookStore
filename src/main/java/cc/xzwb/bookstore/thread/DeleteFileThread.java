package cc.xzwb.bookstore.thread;

import java.io.File;

/**
 * 删除文件的线程
 */
public class DeleteFileThread extends Thread {
    String src;

    public DeleteFileThread(String src) {
        this.src = src;
    }

    @Override
    public void run() {
        File file = new File(src);
        file.delete();
    }
}
