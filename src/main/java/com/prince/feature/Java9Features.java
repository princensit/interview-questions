package com.prince.feature;

/**
 * <pre>
 * 1. jshell- The Java Shell: read-eval-print loop (REPL) tool to learn Java
 * 2. Multi-Release JAR Files: Multi-Release JAR Files extends the JAR file format to allow multiple,
 *    Java-release-specific versions of class/resource files to coexist in the same archive.
 * 3. Remove Launch-Time JRE Version Selection
 * 4. Compile for Older Platform Versions: Using --release option
 * 5. @SafeVarargs Annotation Can Be Applied to Private Methods
 * 6. Improved Try-with-resources
 *
 *      JDK 7/8:
 *      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 *      // Original try-with-resources statement from JDK 7 or 8
 *      try (BufferedReader r1 = reader) {
 *          // do something with buffered reader
 *      } catch (IOException e) {
 * 	    //  exceptions handling
 *      }
 *
 *      Note: We can’t use resource which is declared outside the try() block within it. If resource is already
 *      declared outside, we should re-refer it with local redundant variable.
 *
 *      JDK 9:
 *      final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 *      try (reader) {
 * 	        // do somthing with the reader
 *      }catch (IOException e){
 * 	        // exceptions handling
 *      }
 * 7. Diamond Operator Extension: Now can be used with anonymous inner classes
 * 8. Underscore is completely removed from the set of legal identifier names.
 * 9. Private methods in interfaces to common out the codes present in default and static methods
 * 10. New static factory methods on the List, Set, and Map interfaces which make it simpler to create immutable
 *    instances of those collections.
 *    Ex- Set alphabet = Set.of("c", "o", "r", "e");
 * 11. Compact Strings:
 *    It does reduce the memory footprint of String to half in most of the cases and if required, it can be disabled
 *    by an -XX flag.
 *
 *    String in Java is internally represented by two objects. First object is the String object itself. Second one is
 *    the char array that handles the data contained by the String. And, every char is made up of 2 bytes because Java
 *    internally uses UTF-16. As this is UTF-16, it allows even representation of all the special characters.
 *
 *    The problem is, that the vast majority of the strings in applications can be expressed by just one byte using
 *    ISO-8859-1/Latin-1 as they contain no special characters. As a result, the leading 8 bits will all be 0 for every
 *    char, as an ASCII character can be represented using a single byte.
 *
 *    Instead of having char[] array, String is now represented as byte[] array. Depending on which characters it
 *    contains, it will either use UTF-16 or Latin-1, that is – either one or two bytes per character.
 *
 *    In other cases, if any character requires more than 8-bits to represent it, all the characters are stored using
 *    two bytes for each — UTF-16 representation. So basically, whenever possible, it’ll just use a single byte for
 *    each character.
 *
 *    public int length() {
 *      return value.length >> coder;
 *    }
 *
 *    If string contains only LATIN-1, then coder will be 0. If String is in UTF-16, then coder will be 1.
 *
 * </pre>
 * @author Prince Raj
 */
public class Java9Features {
}
