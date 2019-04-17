package ui

abstract class Page<T: Page<T>> {
    abstract fun open(): T
    abstract fun getPage(): T
    abstract operator fun invoke(block: T.() -> Unit)
}
