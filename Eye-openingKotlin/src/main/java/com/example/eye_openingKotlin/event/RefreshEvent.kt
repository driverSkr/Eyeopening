package com.example.eye_openingKotlin.event

/**
 * EventBus通知刷新界面消息。
 *
 * @author vipyinzhiwei
 * @since  2020/5/19
 */
class RefreshEvent(val activityClass: Class<*>? = null) : MessageEvent()