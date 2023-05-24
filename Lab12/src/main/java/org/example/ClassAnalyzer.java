package org.example;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassAnalyzer {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Test {
    }

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length == 0) {
            System.out.println("Please provide the fully qualified class name as an argument.");
            return;
        }

        String className = args[0];
        Class<?> clazz = Class.forName(className);

        // Extract package information
        Package pkg = clazz.getPackage();
        System.out.println("Package: " + pkg.getName());

        // Extract class information
        System.out.println("Class: " + clazz.getSimpleName());
        System.out.println("Modifiers: " + Modifier.toString(clazz.getModifiers()));
        System.out.println();

        // Extract method information
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
            System.out.println("Modifiers: " + Modifier.toString(method.getModifiers()));

            // Check if method has @Test annotation and is static
            if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers())) {
                try {
                    method.setAccessible(true);
                    method.invoke(null); // Invoke the static method with no arguments
                    System.out.println("Test passed!");
                } catch (Exception e) {
                    System.out.println("Test failed: " + e.getMessage());
                }
            }

            System.out.println();
        }

        // Invoke test methods
        invokeTestMethods(clazz);
    }

    @Test
    public static void test1() {

    }

    @Test
    public static void test2() {

    }

    public static void invokeTestMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers())) {
                try {
                    method.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
