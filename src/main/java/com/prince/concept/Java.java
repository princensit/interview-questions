package com.prince.concept;

/**
 * RetentionPolicy.SOURCE: Discard during the compile. These annotations don't make any sense after
 * the compile has completed, so they aren't written to the bytecode.
 * Example: @Override, @SuppressWarnings
 *
 * RetentionPolicy.CLASS: Discard during class load. Useful when doing bytecode-level
 * post-processing. Somewhat surprisingly, this is the default. Appear in the decompiled class, but
 * can't be inspected at run-time with reflection with getAnnotations()
 *
 * RetentionPolicy.RUNTIME: Do not discard. The annotation should be available for reflection at
 * runtime. Example: @Deprecated
 *
 * @author Prince Raj
 */
public class Java {
}
