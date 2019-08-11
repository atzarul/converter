package ro.lazarl.converter

import javax.inject.Scope

/**
 * Identifies a type that the injector only instantiates once per fragment (has the same lifetime as an fragment).
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope