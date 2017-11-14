package com.prince.oodesign.principles.liskov_substitution;

/**
 * Derived types must be completely substitutable for their base types.
 *
 * This principle is just an extension of the Open Close Principle in terms of behavior meaning that we must make sure
 * that new derived classes are extending the base classes without changing their behavior. The new derived classes
 * should be able to replace the base classes without any change in the code.
 *
 * We must make sure that the new derived classes just extend without replacing the functionality of old classes.
 * Otherwise the new classes can produce undesired effects when they are used in existing program modules.
 *
 * @author Prince Raj
 */
public class LiskovSubstitution {

}
