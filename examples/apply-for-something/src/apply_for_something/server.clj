(ns apply-for-something.server
  (:use [compojure.core]
        [ring.util.response]
        [ring.middleware.json]
        [ring.middleware.params])
  
  (:require [compojure.handler :as handler]          
            [compojure.route :as route]                        
            [ring.adapter.jetty :as jetty]
          )
  (:gen-class))

(defroutes app-routes
  (route/resources "/")
  
)



(def app
  (-> (handler/api app-routes)   
      (wrap-json-body)
      (wrap-json-response)      
;;      (v/request-printer)
      ))


(defn -main [& args]

  (if (not (empty? args))
    (jetty/run-jetty app {:port (read-string (first args))})
    (jetty/run-jetty app {:port 8077}))  
)

