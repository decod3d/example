package com.company.idea;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.project.DumbAware;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Example implements AppLifecycleListener, DumbAware {

    /**
     * Called before an application frame is shown.
     */
    @Override
    public void appFrameCreated(@NotNull List<String> commandLineArgs) {
        test1();   // this does not work
        //test2(); // this works!
    }

    private static void test1() {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("com.intellij.ui.components.labels.LinkLabel");
            CtMethod cm = cc.getDeclaredMethod("getTextColor");
            cm.insertBefore("System.out.println(\"test1\");");
            cc.toClass();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
    }

    private static void test2() {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("com.intellij.openapi.wm.impl.StripeButtonUI");
            CtMethod cm = cc.getDeclaredMethod("update");
            cm.insertBefore("System.out.println(\"test2\");");
            cc.toClass();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
    }
}
