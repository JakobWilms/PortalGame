package com.github.jakobwilms.portalgame;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Utils {
    public static void print(@Nullable Class<?> c, @NotNull String s) {
        print(c, null, s);
    }

    public static void print(@Nullable Class<?> c, @Nullable StackTraceElement[] t, @NotNull String s) {
        String cl = c != null ? "[" + c.getSimpleName() + "]" : "[Static]";
        String ln = (t != null) ? ((t[1] != null) ? ("[" + t[1].getLineNumber() + "]") : "[NA]") : "[NA]";
        String ti = "[" + System.currentTimeMillis() + "]";
        String th = "[" + Thread.currentThread() + "]";
        System.out.println("|| " + ti + cl + ln + th + " { " + s + " } ||");
    }
}
