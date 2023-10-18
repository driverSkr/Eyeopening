package com.example.eyeOpeningKotlin.event

/**
 * EventBus通知刷新界面消息。
 *
 * @author boge
 * @since  2023/10/18
 */
class RefreshEvent(val activityClass: Class<*>? = null): MessageEvent()