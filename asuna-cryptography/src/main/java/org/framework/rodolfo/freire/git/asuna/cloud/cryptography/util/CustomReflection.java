package org.framework.rodolfo.freire.git.asuna.cloud.cryptography.util;


import org.framework.rodolfo.freire.git.asuna.cloud.cryptography.util.exceptions.ExceptionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CustomReflection {

    private static Map<String, Class> classMap;

    public CustomReflection() {
    }

    public static Object getCompoundAttributeValue(Object object, String attribute) {
        Object attributeValue = object;

        for (StringTokenizer token = new StringTokenizer(attribute, "."); token.hasMoreTokens(); attributeValue = getAttributeValue(attributeValue, token.nextToken())) {
            if (attributeValue == null) {
                return null;
            }
        }

        return attributeValue;
    }

    private static Map<String, Class> getClassMap() {
        if (classMap == null) {
            classMap = new HashMap<>();
        }

        return classMap;
    }

    private static String getMethodBaseName(String attributeName) {
        return attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1, attributeName.length());
    }

    private static String getMethodNameGet(String attributeName) {
        return "get" + getMethodBaseName(attributeName);
    }

    private static String setMethodNameSet(String attributeName) {
        return "set" + getMethodBaseName(attributeName);
    }

    public static Object createInstance(String className) {
        return createInstance(getClasse(className));
    }

    public static <T> T createInstance(Class<T> tClass) {
        try {
            return tClass.getDeclaredConstructor().newInstance();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T createInstance(Class<T> tClass, Object... parameters) {
        try {
            Class[] classes = new Class[parameters.length];

            for (int i = 0; i < parameters.length; ++i) {
                classes[i] = parameters[i].getClass();
            }

            Constructor<T> tConstructor = tClass.getConstructor(classes);
            return tConstructor.newInstance(parameters);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static Class getClasse(String className) {
        return consultaCacheDeClasses(className);
    }

    private static Class consultaCacheDeClasses(String className) {
        Class aClass = getClassMap().get(className);
        if (aClass == null) {
            aClass = loadClassCache(className);
        }
        return aClass;
    }

    private static Class loadClassCache(String className) {
        try {
            Class aClass = Class.forName(className);
            getClassMap().put(className, aClass);
            return aClass;
        } catch (ClassNotFoundException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static Class[] getClassesOfParameters(Object[] parameters) {
        if (parameters == null) {
            return null;
        } else {
            Class[] aClass = new Class[parameters.length];

            for (int i = 0; i < aClass.length; ++i) {
                aClass[i] = parameters[i].getClass();
            }

            return aClass;
        }
    }

    public static Object executeStaticMethod(String className, String methodName, Object[] parameters) {
        Class aClass = getClasse(className);

        try {
            Method method = aClass.getMethod(methodName, getClassesOfParameters(parameters));
            return method.invoke(aClass, parameters);
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }

    public static Object executeMethod(String className, String methodName, Object[] parameters) {
        Object object = createInstance(className);

        try {
            Method method = object.getClass().getMethod(methodName, getClassesOfParameters(parameters));
            return method.invoke(object, parameters);
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }

    public static Object executeMethod(Object object, String methodName, Object[] parameters) {
        Method metodo = getMethod(object, methodName, parameters);

        try {
            return metodo.invoke(metodo, parameters);
        } catch (Exception var5) {
            throw new ExceptionUtil(var5);
        }
    }

    private static Method getMethod(Object object, String methodName, Object[] parameters) {
        Class[] typesParameters = getTypesParameters(parameters);

        try {
            return object.getClass().getMethod(methodName, typesParameters);
        } catch (Exception var5) {
            throw new ExceptionUtil(var5);
        }
    }

    private static Class[] getTypesParameters(Object[] parameters) {
        Class[] typesParameters = new Class[parameters.length];

        for (int i = 0; i < parameters.length; ++i) {
            typesParameters[i] = parameters[i].getClass();
        }

        return typesParameters;
    }

    public static void setAttributeValue(Object objectDestiny, String fieldName, Object objectValue) {
        Method method = getMethodSet(objectDestiny, fieldName);
        Object[] parameters = new Object[]{objectValue};
        executeMethod(objectDestiny, method, parameters);
    }

    public static Object getAttributeValue(Object object, String fieldName) {
        Method method = getMethodGet(object, fieldName);
        return executeMethod(object, method, null);
    }

    private static Method getMethodGet(Object object, String fieldName) {
        return createAttributeMethod(object, fieldName);
    }

    private static Method createAttributeMethod(Object object, String fieldName) {
        return createMethod(object.getClass(), getMethodNameGet(fieldName), null);
    }

    private static Object executeMethod(Object objectDestiny, Method method, Object[] parameters) {
        try {
            return method.invoke(objectDestiny, parameters);
        } catch (InvocationTargetException var4) {
            throw new RuntimeException(var4.getCause());
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }

    public static Method getMethodSet(Object objectDestiny, String fieldName) {
        Class aClass = discoverClassParameter(objectDestiny, fieldName);
        return createAttributeSetMethod(objectDestiny, fieldName, aClass);
    }

    private static Class discoverClassParameter(Object objectDestiny, String fieldName) {
        Class aClass = getMethodGet(objectDestiny, fieldName).getReturnType();
        if (aClass == null) {
            aClass = objectDestiny.getClass();
        }
        return aClass;
    }

    private static Method createAttributeSetMethod(Object objectDestiny, String fieldName, Class classParameters) {
        Class[] parametersClass = new Class[]{classParameters};
        return createMethod(objectDestiny.getClass(), setMethodNameSet(fieldName), parametersClass);
    }

    private static Method createMethod(Class<?> objectDestiny, String methodName, Class[] classParameters) {
        try {
            return objectDestiny.getMethod(methodName, classParameters);
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }

    public static boolean existGet(Object object, String attributeName) {
        if (attributeName != null) {
            try {
                object.getClass().getMethod(getMethodNameGet(attributeName));
                return true;
            } catch (SecurityException var3) {
                throw new RuntimeException(var3);
            } catch (NoSuchMethodException var4) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean existSet(Object object, String fieldName) {
        if (fieldName != null) {
            try {
                getMethodSet(object, fieldName);
                return true;
            } catch (SecurityException var3) {
                throw new RuntimeException(var3);
            } catch (RuntimeException var4) {
                return false;
            }
        } else {
            return false;
        }
    }

}
