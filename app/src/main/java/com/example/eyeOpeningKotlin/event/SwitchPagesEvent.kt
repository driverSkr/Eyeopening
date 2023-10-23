package com.example.eyeOpeningKotlin.event

/**
 * EventBus通知Tab页切换界面。
 *
 * @author driverSkr
 * @since  2023/10/18
 */
class SwitchPagesEvent(val activityClass: Class<*>? = null): MessageEvent()