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
          )
  (:gen-class))

(defroutes app-routes
  (route/resources "/")

  (context "/" []
           (defroutes root
             (GET "/" request (redirect "/index.html"))))
  
  (context "/start-application" []
           (defroutes start-application
             (GET "/" request (v/get-start-application request))
             (POST "/" request (v/post-start-application request))))

  (context "/application-forms/:id/about-you" [id]
           (defroutes about-you
             (GET "/" request (v/get-about-you id request))
             (POST "/" request (v/post-about-you id request))
             ))

   (context "/application-forms/:id/where-you-live" [id]
           (defroutes where-you-live
             (GET "/" request (v/get-where-you-live id request))
             (POST "/" request (v/post-where-you-live id request))
             ))

   (context "/application-forms/:id/purpose-of-application" [id]
           (defroutes purpose-of-application
             (GET "/" request (v/get-purpose-of-application id request))
             (POST "/" request (v/post-purpose-of-application id request))
             ))

   (context "/application-forms/:id/confirmation" [id]
           (defroutes confirmaition
             (GET "/" request (v/get-confirmation id request))
             (POST "/" request (v/post-confirmation id request))
             ))

   (context "/application-forms/:id/submission" [id]
           (defroutes submission
             (GET "/" request (v/get-submission id request))             
             ))
   
   

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

