package practice.lab5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileTypeParser {

//  public static Map<byte[], String> typeMap = new HashMap<>();

  public static byte[] png = int2Bytes(0x89504e47);
  public static byte[] zip = int2Bytes(0x504b0304);
  public static byte[] clazz = int2Bytes(0xcafebabe);

  public static void main(String[] args) {
//    for (byte b:png) {
//      System.out.printf("%02x ", Byte.toUnsignedInt(b));
//    }
    for (String arg : args) {
      System.out.println("Filename: " + arg);
      String path = "src/main/java/practice/lab5/" + arg;
      try (FileInputStream fis = new FileInputStream(path)) {
        byte[] header = new byte[4];
        int len = fis.read(header, 0, 4);
        System.out.print("File Header(Hex): [");

        for (int i = 0; i < len - 1; i++) {
          System.out.printf("%02x, ", Byte.toUnsignedInt(header[i]));
        }
        System.out.printf("%02x]\n", Byte.toUnsignedInt(header[len - 1]));

        if (Arrays.equals(header, png)) {
          System.out.println("File Type: png\n");
        } else if (Arrays.equals(header, zip)) {
          System.out.println("File Type: zip or jar\n");
        } else if (Arrays.equals(header, clazz)) {
          System.out.println("File Type: class\n");
        } else {
          System.out.println("File Type: others\n");
        }

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static byte[] int2Bytes(int val) {
    byte[] res = new byte[4];
    for (int i = 3; i >= 0; i--) {
      res[3 - i] = (byte) ((val >> (i * 8)) & 0xff);
    }
    return res;
  }

}
