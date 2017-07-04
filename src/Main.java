import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    static String className;

    public static void main(String[] args) throws Exception {
//        MyClassLoader cl = new MyClassLoader();
//        Class c = cl.findClass("zt.asm.model.Algorithm");
//        Method m = c.getDeclaredMethod("run");
//
//        Object algorithm = c.newInstance();
//        m.invoke(algorithm);

        List<String> classNames = new ArrayList<String>();
        ZipInputStream zip = new ZipInputStream(new FileInputStream("C:\\Users\\MRamzan\\Downloads\\asmdemo-master\\asmdemo-master\\asm-all-3.3.1.jar"));
        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                // This ZipEntry represents a class. Now, what class does it represent?
                className = entry.getName().replace('/', '.'); // including ".class"
                Class c = Class.forName(className.substring(0, className.length() - ".class".length()));
                System.out.println(c);

                /*String[] test = className.split("/");
//                classNames.add(className.substring(0, className.length() - ".class".length()));
//                System.out.println(className);
//                System.out.println(test[test.length-1]);
                String temp = test[test.length-1];
//                System.out.println(temp.substring(0, temp.length() - ".class".length()));*/


                /*
                 *this method is for reading all the methods in a class
                 */

                try {
//                    Class c = Class.forName(className);
                    Method[] m = c.getDeclaredMethods();
                    for (int i = 0; i < m.length; i++)
                        System.out.println(m[i].toString());
                } catch (Throwable e) {
                    System.err.println(e);
                }


                /*
                 *this method is for reading the superclass
                 */

//                Class c1 = className.getClass();
//                while (className != null) {
//                    System.out.println(className.getClass().getName());
//                    className = className.getClass().getSuperclass();
//                }

                break;
            }
        }

    }

}
