(ns config.environments.development
  (:import [org.apache.log4j ConsoleAppender FileAppender Level Logger PatternLayout]
           [org.apache.log4j.varia LevelRangeFilter]))

; Sets up the logger for development mode.
(def output-pattern (new PatternLayout "%d{ISO8601} %-5p [%c]: %m%n"))

(def file-appender (new FileAppender output-pattern "log/development.log"))
(.addFilter file-appender 
  (doto (new LevelRangeFilter)
    (.setLevelMin (. Level ALL))))

(def console-appender (new ConsoleAppender output-pattern))
(.addFilter console-appender 
  (doto (new LevelRangeFilter)
    (.setLevelMin (. Level ALL))))

(doto (. Logger getRootLogger)
  (.setLevel (. Level ALL))
  (.addAppender file-appender)
  (.addAppender console-appender))