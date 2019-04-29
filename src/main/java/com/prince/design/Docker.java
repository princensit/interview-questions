package com.prince.design;

/**
 * @author Prince Raj
 */
public class Docker {

    /**
     * <pre>
     * Docker vs Virtual Machine:
     *
     * A full virtualized system gets its own set of resources allocated to it, and does minimal sharing. You get more
     * isolation, but it is much heavier (requires more resources).
     *
     * With Docker you get less isolation, but the containers are lightweight (require fewer resources). So you could
     * easily run thousands of containers on a host, and it won't even blink.
     *
     * A full virtualized system usually takes minutes to start, whereas Docker/LXC/runC containers take seconds, and
     * often even less than a second.
     *
     * There are pros and cons for each type of virtualized system. If you want full isolation with guaranteed resources,
     * a full VM is the way to go. If you just want to isolate processes from each other and want to run a ton of them
     * on a reasonably sized host, then Docker/LXC/runC seems to be the way to go.
     *
     * This is great for testing; let's say you have thousands of tests that need to connect to a database, and each
     * test needs a pristine copy of the database and will make changes to the data. The classic approach to this is to
     * reset the database after every test either with custom code or with tools like Flyway - this can be very
     * time-consuming and means that tests must be run serially. However, with Docker you could create an image of your
     * database and run up one instance per test, and then run all the tests in parallel since you know they will all be
     * running against the same snapshot of the database. Since the tests are running in parallel and in Docker
     * containers they could run all on the same box at the same time and should finish much faster. Try doing that with
     * a full VM.
     *
     * You start with a base image, and then make your changes, and commit those changes using docker, and it creates
     * an image. This image contains only the differences from the base. When you want to run your image, you also need
     * the base, and it layers your image on top of the base using a layered file system: as mentioned above, Docker uses
     * AUFS. AUFS merges the different layers together and you get what you want; you just need to run it. You can keep
     * adding more and more images (layers) and it will continue to only save the diffs. Since Docker typically builds
     * on top of ready-made images from a registry, you rarely have to "snapshot" the whole OS yourself.
     *
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
