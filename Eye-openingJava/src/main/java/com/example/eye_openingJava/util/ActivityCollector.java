package com.example.eye_openingJava.util;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/6 16:57
 *@Function:
 * 管理应用程序中所有Activity
*/
public class ActivityCollector {

    private static final Stack<WeakReference<Activity>> activitys = new Stack<>();

    /**
     * 将 Activity 压入应用程序栈
     *
     * @param task 要压入栈的 Activity 对象
     */
    public static void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 从栈中移除指定的 Activity 对象
     *
     * @param task 要移除的 Activity 对象
     */
    public static void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 根据索引从栈中移除 Activity
     *
     * @param taskIndex Activity 栈索引
     */
    public static void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex) {
            activitys.remove(taskIndex);
        }
    }

    /**
     * 将栈中的 Activity 移除到栈顶
     */
    public static void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            Activity mActivity = activitys.get(i).get();
            if (mActivity != null && !mActivity.isFinishing()) {
                mActivity.finish();
            }
        }
    }

    /**
     * 移除所有 Activity（用于整个应用程序退出）
     */
    public static void removeAll() {
        for (WeakReference<Activity> task : activitys) {
            Activity mActivity = task.get();
            if (mActivity != null && !mActivity.isFinishing()) {
                mActivity.finish();
            }
        }
    }
}
