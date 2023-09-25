package com.example.eye_openingKotlin.event

/**
 * EventBus通知Tab页切换界面。
 *
 * @author vipyinzhiwei
 * @since  2020/5/19
 */
class SwitchPagesEvent(val activityClass: Class<*>? = null) : MessageEvent()