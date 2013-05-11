(defproject pantry-server "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [io.pedestal/pedestal.service "0.1.2"]

                 ;; Remove this line and uncomment the next line to
                 ;; use Tomcat instead of Jetty:
                 [io.pedestal/pedestal.jetty "0.1.2"]
                 ;; [io.pedestal/pedestal.tomcat "0.1.2"]

                 ;; Parser
                 [the/parsatron "0.0.4"]

                 ;; Logging
                 [ch.qos.logback/logback-classic "1.0.7"]
                 [org.slf4j/jul-to-slf4j "1.7.2"]
                 [org.slf4j/jcl-over-slf4j "1.7.2"]
                 [org.slf4j/log4j-over-slf4j "1.7.2"]]
  :plugins [
            [lein-midje "3.0-alpha4"]
            ]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[midje "1.5-alpha10"]
                                  [ring-mock "0.1.3"]
                                  [com.stuartsierra/lazytest "1.2.3"]]}}
  :min-lein-version "2.0.0"
  :resource-paths ["config"]
  :repositories {"stuartsierra-releases" "http://stuartsierra.com/maven2"}
  :main ^{:skip-aot true} pantry-server.server)
