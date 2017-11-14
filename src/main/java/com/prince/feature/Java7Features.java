package com.prince.feature;

/**
 * @author Prince Raj
 */
public class Java7Features {

    public static void main(String[] args) {
        System.out.println("1. Strings in switch statement");
        System.out.println("2. Binary literals. Ex - int binary = 0b1000; //2^3 = 8");
        System.out.println("3. Underscore between literals. Ex - int oneMillion_ = 1_000_000;");
        System.out.println("4. Diamond Syntax. Ex - Map<String, String> map = new HashMap<>();");
        System.out.println("5. Multi-catch similar exceptions");
        System.out.println(" try {\n"
                + "        throw new FileNotFoundException(\"FileNotFoundException\");\n"
                + "    } catch (FileNotFoundException | IOException fnfo) {\n"
                + "        fnfo.printStackTrace();\n"
                + "    }");
        System.out.println("6. Try with resources i.e. Automatic resource management");
        System.out.println("7. ForkJoinPool added");
    }
}
