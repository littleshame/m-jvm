package jvm.loader;

import jvm.clz.ClassFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 类装载器
 * Created by tjc on 2018/4/29.
 */
public class ClassFileLoader {

	private List<String> clzzPaths = new ArrayList<String>();

	public ClassFileLoader() {
	}

	/**
	 *
	 * @param className
	 * @return 类文件
	 */
	public ClassFile loadClass(String className) {
		byte[] code = readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();

		return parser.parse(code);
	}



	public void addClassPath(String path) {
		if (clzzPaths.contains(path)) {
			return;
		}
		clzzPaths.add(path);
	}

	public String getClassPath() {
		return StringUtils.join(this.clzzPaths, ";");
	}

	/**
	 *
	 * @param className 类名
	 * @return 类的二进制信息
	 */
	public byte[] readBinaryCode(String className) {

		byte[] codes = null;
		InputStream input;
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		className = className.replace('.', File.separatorChar) + ".class";
		for (String path : this.clzzPaths) {

			String clzFileName = path + File.separatorChar + className;
			codes = loadClassFile(clzFileName);
			if (codes != null) {
				return codes;
			}
		}
		return codes;
	}

	private byte[] loadClassFile(String clzFileName) {
		File f = new File(clzFileName);

		try {
			return IOUtils.toByteArray(new FileInputStream(f));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
