(defproject apply-for-something "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"
                 "ixcode-repo" "http://repo.ixcode.org/public"}

  
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/algo.graph "0.1.0-SNAPSHOT"]
                 [com.novemberain/monger "1.4.1"]
                 [cheshire "5.0.0"]
                 [clj-time "0.4.4"]
                 [clj-http "0.7.8"]
                 [midje "1.4.0"]
                 [clj-yaml "0.4.0"]
                 [metrics-clojure "1.0.1"]
                 [clj-http "0.6.3"]
                 [compojure "1.1.1"]
                 [ring-mock "0.1.3"]
                 ;;    [org.clojure/tools.logging "0.2.6"]
                 ;;    [clj-logging-config "1.9.11-SNAPSHOT"]
                 [ring/ring-json "0.1.2"]
                 [ring/ring-jetty-adapter "1.1.7" :exclusions [org.slf4j/slf4j-nop
                                                               org.slf4j/slf4j-log4j12]]
                 [endjinn "0.1.0-SNAPSHOT"] 
                 [dk.ative/docjure "1.6.0"]
                 [rewrite-clj "0.2.0"]
                 ]
  :ring {:handler apply-for-something.server/app}
  :main apply-for-something.server
  :aot [apply-for-something.server]
  :plugins [[lein-ring "0.8.6"]]
  :profiles {:dev {
                   :dependencies [[midje "1.6-alpha2"]]}}

)
