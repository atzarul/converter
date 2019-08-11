package ro.lazarl.converter.common.formatting

interface Formatter<in T> {
    fun format(value: T): String
}