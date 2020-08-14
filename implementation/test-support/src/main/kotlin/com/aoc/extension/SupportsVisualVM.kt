package com.aoc.extension

import com.aoc.annotation.WaitForVisualVM
import com.aoc.log.AdventLogger
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.support.AnnotationSupport

/**
 * Indicates that a test suite class supports sampling or profiling with VisualVM.
 * @see WaitForVisualVM
 */
class SupportsVisualVM : BeforeTestExecutionCallback {
    override fun beforeTestExecution(context: ExtensionContext?) {
        val annotationType = WaitForVisualVM::class.java
        val testMethod = context!!.requiredTestMethod
        if (AnnotationSupport.isAnnotated(testMethod, annotationType)) {
            val seconds = testMethod.getAnnotation(annotationType).seconds
            AdventLogger.debug("Waiting ${seconds}s for VisualVM to attach...\n")
            Thread.sleep((seconds * 1000).toLong())
        }
    }
}