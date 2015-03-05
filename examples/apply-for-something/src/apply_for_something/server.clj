(ns apply-for-something.server
  (:use [compojure.core]
        [ring.util.response]
        [ring.middleware.json]
        [ring.middleware.params]
        )
  
  (:require [compojure.handler :as handler]          
            [compojure.route :as route]                        
            [ring.adapter.jetty :as jetty]
            [apply-for-something.views :as v]
            [prone.middleware :as prone]
          )
  (:gen-class))

(defroutes app-routes
  (route/resources "/")

  (context "/" []           
           (GET "/" request (redirect "/index.html")))
  
  (context "/start-application" []           
           (GET "/" request (v/get-start-application request))
           (POST "/" request (v/post-start-application request)))

  (context "/application-forms/:id/:stage" [id stage]
           (GET "/" request (v/get-stage id (keyword stage) request))
           (POST "/" request (v/post-stage id (keyword stage) request))))



(def app
  (-> (handler/api app-routes)
      (prone/wrap-exceptions)
      (wrap-json-body)
      (wrap-json-response)))


(defn -main [& args]
  (if (not (empty? args))
    (jetty/run-jetty app {:port (read-string (first args))})
    (jetty/run-jetty app {:port 8077})))

