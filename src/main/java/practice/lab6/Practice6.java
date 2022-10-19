package practice.lab6;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Practice6 {

  static FileInputStream inputStream;

  public static void readZip(String path) throws IOException {
    inputStream = new FileInputStream(path);
    ZipInputStream zin = new ZipInputStream(inputStream);
    ZipEntry ze;
    List<String> fileList = new LinkedList<>();
    while ((ze = zin.getNextEntry()) != null) {
      String name = ze.getName();
      if ((name.startsWith("java/io/") || name.startsWith("java/nio/")) && name.endsWith("java")) {
        fileList.add(name);
      }
    }
    System.out.println("# of .java files in java.io/java.nio: " + fileList.size());
    for (String name : fileList) {
      System.out.println(name);
    }

  }

  public static void readJar(String path) throws IOException {
    inputStream = new FileInputStream(path);
    JarInputStream jin = new JarInputStream(inputStream);
    JarEntry je;
    List<String> fileList = new LinkedList<>();
    while ((je = jin.getNextJarEntry()) != null) {
      String name = je.getName();
      if ((name.startsWith("java/io/") || name.startsWith("java/nio/")) && name.endsWith("class")) {
        fileList.add(name);
      }
    }
    System.out.println("# of .class files in java.io/java.nio: " + fileList.size());
    for (String name : fileList) {
      System.out.println(name);
    }
  }

  public static void main(String[] args) {
    String zipPath = "src/main/java/practice/lab6/src.zip";
    String jarPath = "src/main/java/practice/lab6/rt.jar";
    try {
      readZip(zipPath);
      readJar(jarPath);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
