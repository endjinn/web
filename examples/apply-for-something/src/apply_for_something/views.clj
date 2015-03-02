(ns apply-for-something.views
  (:use [stencil.core]
        [ring.util.response]))

(defn get-start-application [request]
  (-> (response (render-file "web-templates/start-application.html" {}))
      (content-type "text/html")))

(defn post-start-application [request]
  (redirect-after-post "/check-eligibility.html"))
