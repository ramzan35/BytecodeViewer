import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Suwadith on 7/4/2017.
 * Edited by MRamzan on 7/5/2017
 */
public class ReadFromJar {

    public static File folder, fileList[], jarList[];
    public static final String initialPath = "C:\\Users\\MRamzan\\Downloads";

    public static File[] loadFileNames(String path) {
        folder = new File(initialPath);
        return fileList = folder.listFiles();
    }

    public static File[] loadJAR(File[] list) throws ClassNotFoundException, IOException {

        for (int i = 0; i < list.length; i++) {
            if (list[i].toString().endsWith("jar")) {
                System.out.println(list[i]);
                try {
                    ZipInputStream zip = new ZipInputStream(new FileInputStream(list[i]));
                    for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                        if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                            // This ZipEntry represents a class. Now, what class does it represent?
                            String className = entry.getName().replace('/', '.'); // including ".class"
                            Class c = null;
                            try {
                                c = Class.forName(className.substring(0, className.length() - ".class".length()));
                            } catch (NoClassDefFoundError e) {

                            }

                            System.out.print("Class name    :  ");
                            System.out.println(c);
                            System.out.println();

                    /*
                     *this code snippet is for reading all the methods in a class
                     */

                            System.out.print("method names  :  ");
                            try {
                                //                    Class c = Class.forName(className);
                                Method[] m = c.getDeclaredMethods();
                                for (int j = 0; j < m.length; j++) {
                                    System.out.println(m[j].toString());
                                    System.out.print("                 ");
                                }
                            } catch (Throwable e) {
                                System.err.println(e);
                            }
                            System.out.println();


                    /*
                     *this code snippet is for reading the superclass
                     */

                            System.out.print("Super classes : ");
                            //                Class c1 = className.getClass();
                            while (c != null) {
                                System.out.println(c);
                                System.out.print("                ");
                                c = c.getSuperclass();
                            }
                            System.out.println();
                            System.out.println();
                            System.out.println();

                        }
                    }
                } catch (IOException e) {

                }
            }
        }
        return jarList;
    }


    public static void main(String[] args) {

        try {
            loadJAR(loadFileNames(initialPath));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
