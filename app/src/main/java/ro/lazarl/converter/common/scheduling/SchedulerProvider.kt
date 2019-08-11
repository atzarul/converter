package ro.lazarl.converter.common.scheduling

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun mainThread(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

}