package org.example;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

public class ClassAnalyzer {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Test {
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a file or directory path as an argument.");
            return;
        }

        String path = args[0];
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("The specified file or directory does not exist.");
            return;
        }

        List<Class<?>> classes = new ArrayList<>();

        if (file.isDirectory()) {
            exploreDirectory(file, classes);
        } else if (file.isFile() && file.getName().endsWith(".jar")) {
            try {
                exploreJarFile(file, classes);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("Invalid file format. Please provide a directory or a JAR file.");
            return;
        }

        for (Class<?> clazz : classes) {
            printClassPrototype(clazz);
            invokeTestMethods(clazz);
        }

        printTestStatistics();
    }

    private static void exploreDirectory(File directory, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                exploreDirectory(file, classes);
            } else if (file.getName().endsWith(".class")) {
                String className = getClassNameFromFile(file);
                if (className != null) {
                    loadClass(className, classes);
                }
            }
        }
    }

    private static String getClassNameFromFile(File file) {
        String absolutePath = file.getAbsolutePath();
        String classPath = absolutePath.substring(absolutePath.indexOf("classes") + 8, absolutePath.lastIndexOf('.'));
        return classPath.replace(File.separator, ".");
    }

    private static void exploreJarFile(File jarFile, List<Class<?>> classes) throws IOException {
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL()})) {
            String jarFilePath = jarFile.getAbsolutePath();

            // Extract the jar file path excluding the file name
            String jarPath = jarFilePath.substring(0, jarFilePath.lastIndexOf(File.separator) + 1);

            // Iterate over the jar file entries
            for (URL url : classLoader.getURLs()) {
                File entryFile = new File(url.getFile());
                if (entryFile.getAbsolutePath().startsWith(jarPath) && entryFile.getName().endsWith(".class")) {
                    String className = getClassNameFromFile(entryFile);
                    if (className != null) {
                        loadClass(className, classes, classLoader);
                    }
                }
            }
        }
    }

    private static void loadClass(String className, List<Class<?>> classes) {
        try {
            Class<?> clazz = Class.forName(className);
            classes.add(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadClass(String className, List<Class<?>> classes, ClassLoader classLoader) {
        try {
            Class<?> clazz = classLoader.loadClass(className);
            classes.add(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printClassPrototype(Class<?> clazz) {
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

            System.out.println();
        }
    }

    private static void invokeTestMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.setAccessible(true);

                    // Generate mock values for method arguments
                    Object[] arguments = generateMockArguments(method.getParameterTypes());

                    // Invoke the test method with mock arguments
                    method.invoke(Objects.requireNonNull(clazz.getDeclaredConstructor()).newInstance(), arguments);

                    System.out.println("Test passed: " + method.getName());
                } catch (Exception e) {
                    System.out.println("Test failed: " + method.getName() + ", Reason: " + e.getMessage());
                }
            }
        }
    }

    private static Object[] generateMockArguments(Class<?>[] parameterTypes) {
        Object[] arguments = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];

            if (parameterType.equals(int.class) || parameterType.equals(Integer.class)) {
                arguments[i] = 0; // Mock integer value as 0
            } else if (parameterType.equals(String.class)) {
                arguments[i] = "mock"; // Mock string value as "mock"
            } else {
                arguments[i] = null; // Other types are mocked as null
            }
        }
        return arguments;
    }

    private static void printTestStatistics() {
        // Implement your logic to track and print test statistics here
        System.out.println("Test statistics:");
        // Example:
        System.out.println("Total tests: 2");
        System.out.println("Passed tests: 1");
        System.out.println("Failed tests: 1");
    }
}