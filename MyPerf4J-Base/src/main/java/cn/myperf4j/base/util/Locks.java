package cn.myperf4j.base.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.util.function.Supplier;

/**
 * Locks.
 *
 * <p>
 * Locks created at 2021/8/30 11:40.
 * </p>
 *
 * @author <a href="mailto:snsyzf@qq.com">yzf</a>
 * @version 1.0
 */
public abstract class Locks {

	public static <T> T lockTmpdir(String filename, Supplier<T> r) {
		File tmp = Paths.get(System.getProperty("java.io.tmpdir")).resolve(filename).toFile();
		try (RandomAccessFile rf = new RandomAccessFile(tmp, "rw"); FileChannel channel = rf.getChannel()) {
			final FileLock lock = channel.lock();
			try {
				return r.get();
			} catch (Throwable e) {
				throw new RuntimeException(e);
			} finally {
				lock.release();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
