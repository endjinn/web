(ns apply-for-something.views
  (:use [stencil.core]
        [ring.util.response]))

(stencil.loader/set-cache (clojure.core.cache/ttl-cache-factory {} :ttl 0))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn get-start-application [request]
  (-> (response (render-file "web-templates/start-application.html" {}))
      (content-type "text/html")))

(defn post-start-application [request]  
  (redirect-after-post (format "/application-forms/%s/about-you" (uuid))))
