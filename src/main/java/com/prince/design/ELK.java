package com.prince.design;

/**
 * @author Prince Raj
 */
public class ELK {

    public static void main(String[] args) {
        print();
    }

    private static void print() {
        System.out.println("E - Elastic Search");
        System.out.println("L - Logstash");
        System.out.println("K - Kibana");

        System.out.println("\nLogstash — Routing Your Log Data");
        System.out
                .println("Logstash is an open source, server-side data processing pipeline that ingests data from a multitude of sources simultaneously, transforms it, and then sends it to your favorite \"stash.\", say Elasticsearch");
        System.out.println("\nElasticsearch — The Amazing Log Search Tool");
        System.out.println("Benefits:");
        System.out.println("1. Real-time data and real-time analytics.");
        System.out.println("2. Scalable, high-availability, multi-tenant.");
        System.out
                .println("3. Full text search. The search features come with multi-language support, an extensive query language, geolocation support, and context-sensitive suggestions, and autocompletion.");
        System.out.println("4. Document orientation");
        System.out.println("\nKibana — Visualizing Your Log Data");
        System.out
                .println("Kibana is your log-data dashboard. Get a better grip on your large data stores with point-and-click pie charts, bar graphs, trendlines, maps and scatter plots. You can visualize trends and patterns for data");
    }
}
