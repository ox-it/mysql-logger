MySQL Logging
-------------

This project adds some aditional support for logging MySQL statements from the JDBC connector.
This will add additional overhead as when profiling isn't enabled the connector shortcuts code concernced with creating the profiling events.
This has be written to support logging of a sample of SQL statements to log4j although the MySQL connector ships with support for logging to other logging frameworks.

To use this code add the following to your JDBC connection string:

    &logger=uk.ac.ox.it.Log4JLogger&profilerEventHandler=uk.ac.ox.it.StatementLogging&profileSQL=true

this will cause the Log4J logger to be used for MySQL logging and set the profiler to generate events for just executions.

To configure log4j if you're using >1.2.15 then you can add filters in your properties files and also add a filter so that a limited amount of SQL statements get logged:

    # MySQL setup
    log4j.appender.sample=org.apache.log4j.ConsoleAppender
    log4j.appender.sample.layout=org.apache.log4j.PatternLayout
    log4j.appender.sample.layout.ConversionPattern=%d{ISO8601} %5p %t %c - %m%n
    log4j.appender.sample.Encoding=UTF-8
    # Only log every 10th event on average.
    log4j.appender.sample.filter.1=uk.ac.ox.it.SamplingFilter
    log4j.appender.sample.filter.1.interval=10
    
    # The MySQL Connector uses the logger named MySQL
    log4j.logger.MySQL=INFO, sample
    log4j.additivity.MySQL=false


