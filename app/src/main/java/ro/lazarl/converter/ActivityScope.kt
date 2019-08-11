package ro.lazarl.converter

import javax.inject.Scope

/**
 * Identifies a type that the injector only instantiates once per activity (has the same lifetime as an activity).
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope