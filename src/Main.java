import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    static String className;

    public static void main(String[] args) {
//        MyClassLoader cl = new MyClassLoader();
//        Class c = cl.findClass("zt.asm.model.Algorithm");
//        Method m = c.getDeclaredMethod("run");
//
//        Object algorithm = c.newInstance();
//        m.invoke(algorithm);

//        List<String> classNames = new ArrayList<String>();
        ZipInputStream zip = null;
        try {
            zip = new ZipInputStream(new FileInputStream("C:\\Users\\MRamzan\\Downloads\\jarFiles\\ASM-BO.jar"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    // This ZipEntry represents a class. Now, what class does it represent?
                    className = entry.getName().replace('/', '.'); // including ".class"
                    System.out.println("This is the class name : "+className);
                    Class c=null;
                    try {
                        c = Class.forName(className.substring(0, className.length() - ".class".length()));
                    }catch (NoClassDefFoundError e){

                    }

                    System.out.print("Class name    :  ");
                    System.out.println(c);
                    System.out.println();

                    /*String[] test = className.split("/");
    //                classNames.add(className.substring(0, className.length() - ".class".length()));
    //                System.out.println(className);
    //                System.out.println(test[test.length-1]);
    //                String temp = test[test.length-1];
    //                System.out.println(temp.substring(0, temp.length() - ".class".length()));*/


                    /*
                     *this code snippet is for reading all the methods in a class
                     */

                    System.out.print("method names  :  ");
                    try {
    //                    Class c = Class.forName(className);
                        Method[] m = c.getDeclaredMethods();
                        for (int i = 0; i < m.length; i++) {
                            System.out.println(m[i].toString());
                            System.out.print("                 ");
                        }
                    } catch (Throwable e) {
//                        System.err.println(e);
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

    //                break;
                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
        catch (Exception e){

        }

    }

}
