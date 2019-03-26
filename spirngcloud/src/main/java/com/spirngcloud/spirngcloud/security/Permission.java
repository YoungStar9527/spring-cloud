package com.spirngcloud.spirngcloud.security;

public class Permission {
    private static ThreadLocal<String> holder = new ThreadLocal<>();

    public static String getUser() {
        return holder.get()==null?"NoUser":holder.get();
    }

    public static void setUser(String user) {
        holder.set(user);
    }
}
