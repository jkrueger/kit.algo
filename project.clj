(defproject vupt/kit.algo "0.1.0-SNAPSHOT"
  :dependencies
    [[org.clojure/clojure "1.6.0"]
     [org.clojure/clojurescript "0.0-2665"]
     [com.cemerick/piggieback "0.1.5"]]
  
  :node-dependencies [[source-map-support "0.2.8"]]
  
  :plugins
    [[lein-cljsbuild "1.0.4"]
     [lein-npm "0.4.0"]]
  
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :source-paths ["src" "target/classes"]

  :cljsbuild {
    :builds
      [{:id "dev"
        :source-paths ["src"]
        :compiler {
          :output-dir     "target"
          :output-to      "kit.algo.repl.js"
          :optimizations  :none
          :cache-analysis true
          :source-map     true}}]})
