package io.renren;

import java.util.HashMap;

import javassist.NotFoundException;

public class Test {
	public static void main(String[] args) throws NotFoundException {
//		ClassPool pool = new ClassPool(true);
//		pool.appendClassPath("D:\\tool\\eclipse\\workspace\\spring-boot-admin\\target\\classes");
//		ClassLoader classLoader = pool.getClassLoader();
//		CtClass ctClass = pool.getCtClass("admin.modules.databaseinfo.controller.DatabaseInfoController");
//		CtMethod[] methods = ctClass.getMethods();
//		try {
//			methods[5].insertAt(59, true, "System.out.println(\"1111111111111111\");\n");
//			methods[5].insertAt(61, true, "System.out.println(\"1111111111111111\");");
//			methods[5].insertAfter("System.out.println(\"1111111111111111\");");
//			methods[5].insertBefore("System.out.println(\"1111111111111111\");");
//		} catch (CannotCompileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			ctClass.writeFile("C:\\Users\\honghuiwang\\Desktop");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (CannotCompileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.print(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		HashMap map = new HashMap();
		map.put("a", "a");
		map.put("a", "a");
	}
}
