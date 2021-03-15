package admin.common.utils;

import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author wanghonghui
 * @description jar包加载器
 * @Date Created in 10:11-2019/9/10
 */
public class JarLoaderUtils {

    /**
     * 功能描述: 扫描一个文件夹下面的所有jar，不包含子文件夹和子jar
     *
     * @param directoryPath
     * @return:java.util.Map<java.lang.String,java.lang.Class<?>>
     * @since: v1.0
     * @Author:wanghonghui
     * @Date: 2019/9/12-15:21
     */
    public static Map<String, Class<?>> loadAllJarFromAbsolute(String directoryPath) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        File directory = new File(directoryPath);
        // 判断是否为文件夹，如果是文件,直接用单个jar解析的方法去解析
        if (!directory.isDirectory()) {
            // 添加jar扫描路径
            addUrl(directory);
            return null;
//            return loadJarFromAbsolute(directoryPath);
        }
        // 如果是文件夹，则需要循环加载当前文件夹下面的所有jar
        Map<String, Class<?>> clazzMap = new HashMap<>(16);
        File[] jars = directory.listFiles();
        if (jars != null && jars.length > 0) {
            List<String> jarPath = new LinkedList<>();
            for (File file : jars) {
                String fPath = file.getPath();
                // 只加载jar
                if (fPath.endsWith(".jar")) {
                    addUrl(file);
                    jarPath.add(fPath);
                }
            }
            if (jarPath.size() > 0) {
                for (String path : jarPath) {
//                    clazzMap.putAll(loadJarFromAbsolute(path));
                }
            }
        }
        return clazzMap;
    }

    /**
     * 功能描述: 从绝对路径中加载jar包中的类
     * 扫描指定jar包前需要将这个jar的地址加入了系统类加载器的扫描列表中
     * 注意，这里只支持单个jar的加载，如果这个jar还引入了其他jar依赖，会加载失败
     * 所以只能用来加载对其他jar包没有依赖的简单对象类信息
     *
     * @return:java.util.Map<java.lang.String,java.lang.Class<?>>
     * @since: v1.0
     * @Author:wanghonghui
     * @Date: 2019/9/12-14:14
     */
    public static Map<String, Class<?>> loadJarFromAbsolute() throws IOException {
        JarFile jar = new JarFile("");
        Enumeration<JarEntry> entryEnumeration = jar.entries();
        Map<String, Class<?>> clazzMap = new HashMap<>(16);
        while (entryEnumeration.hasMoreElements()) {
            JarEntry entry = entryEnumeration.nextElement();
            // 先获取类的名称，符合条件之后再做处理，避免处理不符合条件的类
            String clazzName = entry.getName();
            if (clazzName.endsWith(".class")) {
                // 去掉文件名的后缀
                clazzName = clazzName.substring(0, clazzName.length() - 6);
                // 替换分隔符
                clazzName = clazzName.replace("/", ".");
                // 加载类,如果失败直接跳过
                try {
//                    Class<?> clazz = Class.forName(clazzName);
                    // 将类名称作为键，类Class对象作为值存入mao
                    // 因为类名存在重复的可能，所以这里的类名是带包名的
//                    clazzMap.put(clazzName, clazz);
                } catch (Throwable e) {
                    // 这里可能出现有些类是依赖不全的，直接跳过，不做处理，也没法做处理
                }
            }
        }
        return clazzMap;
    }

    public static HashMap<String,String> loadJarFromAbsolute(String path) throws IOException {
        HashMap<String,String> hashMap = new HashMap<String,String>();
        TreeSet set = new TreeSet();
        try {
            Class<?> clazz = Class.forName(path);
            hashMap.put("jsfName",clazz.getSimpleName()+"RpcImp");
            hashMap.put("ijsfName",clazz.getSimpleName()+"Rpc");

            // 将类名称作为键，类Class对象作为值存入mao
            // 因为类名存在重复的可能，所以这里的类名是带包名的
            Method[] methods = clazz.getMethods();
            StringBuilder sb = new StringBuilder();
            StringBuilder implementSb = new StringBuilder();
            sb.append("import com.jd.ump.annotation.JProEnum;\n");
            sb.append("import com.jd.ump.annotation.JProfiler;\n");
            for (Method Method:methods) {
                Class<?>[] parameterTypes = Method.getParameterTypes();

                if(!Method.getReturnType().getName().startsWith("java.lang")){
                    if(set.add(Method.getReturnType().getName())){
                        sb.append("import ");
                        sb.append(Method.getReturnType().getName());
                        sb.append(";\n");

                        implementSb.append("import ");
                        implementSb.append(Method.getReturnType().getName());
                        implementSb.append(";\n");
                    }
                }
                for (Class c:parameterTypes) {
                    if(set.add(c.getName())&&!c.getName().startsWith("java.lang")){
                        sb.append("import ");
                        sb.append(c.getName());
                        sb.append(";\n");

                        implementSb.append("import ");
                        implementSb.append(c.getName());
                        implementSb.append(";\n");
                    }
                }
            }
            implementSb.append("\n\n");




            sb.append("\n");
            sb.append("\n");
            sb.append("public class ");
            sb.append(clazz.getSimpleName());
            sb.append("RpcImp");
            sb.append(" implements ");
            sb.append(clazz.getSimpleName());
            implementSb.append("public interface ");
            implementSb.append(clazz.getSimpleName());
            implementSb.append("Rpc{\n\t\t");
            for (Method Method:methods) {
                Class<?>[] parameterTypes = Method.getParameterTypes();
                implementSb.append("public "+Method.getReturnType().getSimpleName()+" "+Method.getName()+"(");
                for (Class c:parameterTypes) {
                    implementSb.append(c.getSimpleName()+" " + WordUtils.uncapitalize(c.getSimpleName()));
                    implementSb.append(" ,");
                }
                implementSb.deleteCharAt(implementSb.length()-1);
                implementSb.append(");\n\t\t");
            }
            implementSb.deleteCharAt(implementSb.length()-1);
            implementSb.deleteCharAt(implementSb.length()-1);
            implementSb.append("}\n");

            sb.append("{\n\t\t");

            sb.append("private ");
            sb.append(clazz.getSimpleName());
            sb.append(" ");
            sb.append(WordUtils.uncapitalize(clazz.getSimpleName()));
            sb.append(";");
            sb.append("\n\n\t\t");

            for (Method Method:methods) {
                Class<?>[] parameterTypes = Method.getParameterTypes();




                sb.append("@Override");
                sb.append("\n\t\t");
                sb.append("@MethodLog(\"方法描述\")");
                sb.append("\n\t\t");
                sb.append("@JProfiler(jKey = \""+clazz.getSimpleName()+"JSF."+Method.getName()+"\", mState = {JProEnum.TP, JProEnum.FunctionError})");
                sb.append("\n\t\t");

                sb.append("public "+Method.getReturnType().getSimpleName()+" "+Method.getName()+"(");
                for (Class c:parameterTypes) {
                    sb.append(c.getSimpleName()+" " + WordUtils.uncapitalize(c.getSimpleName()));
                    sb.append(" ,");
                }
                sb.deleteCharAt(sb.length()-1);
                sb.append(")");
                sb.append("{");
                sb.append("\n\t\t\t");

                sb.append(Method.getReturnType().getSimpleName());
                sb.append(" ");
                sb.append(WordUtils.uncapitalize(Method.getReturnType().getSimpleName()));
                sb.append(" = ");
                sb.append(WordUtils.uncapitalize(clazz.getSimpleName()));
                sb.append(".");
                sb.append(Method.getName());
                sb.append(" (");
                for (Class c:parameterTypes) {
                    sb.append(WordUtils.uncapitalize(c.getSimpleName()));
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length()-1);
                sb.append(" );");
                sb.append("\n\t\t\t");
                sb.append("return "+WordUtils.uncapitalize(Method.getReturnType().getSimpleName()));
                sb.append(";");
                sb.append("\n\t\t");
                sb.append("}");
                sb.append("\n\n\t\t");
                sb.append("");



//                System.out.println(Method);
//                System.out.println(Method.getName());
//                System.out.println(Method.getReturnType().getName());
//                System.out.println(Method.getGenericReturnType());
            }
            hashMap.put("jsfContent",sb.toString());
            hashMap.put("ijsfContent",implementSb.toString());
            System.out.println(sb.toString());
            System.out.println(implementSb.toString());

//              clazzMap.put(clazzName, clazz);
        } catch (Throwable e) {
            // 这里可能出现有些类是依赖不全的，直接跳过，不做处理，也没法做处理
        }
        return hashMap;
    }

    /**
     * 功能描述: 添加需要扫描的jar包
     *
     * @param jarPath
     * @return:void
     * @since: v1.0
     * @Author:wanghonghui
     * @Date: 2019/9/12-15:21
     */
    public static void addUrl(File jarPath) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, MalformedURLException {

        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        // 反射获取类加载器中的addURL方法，并将需要加载类的jar路径
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        URL url = jarPath.toURI().toURL();
        // 把当前jar的路径加入到类加载器需要扫描的路径
        method.invoke(classLoader, url);
    }

    public static void main(String[] args) {
        try {
            loadAllJarFromAbsolute("D:\\tool\\eclipse\\wodkspace\\dlb_trade_component\\trade_component.deploy\\target\\trade_component\\WORLDS-INF\\lib");
            loadJarFromAbsolute("com.duolabao.trade.rpc.pay.PayQueryRpc");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}