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
             (GET "/" request (v/get-start-application request "01-start-application.html"))
             (POST "/" request (v/post-start-application request "about-you"))))

  (context "/application-forms/:id/about-you" [id]
           (defroutes about-you
             (GET "/" request (v/get-section id request "02-about-you.html"))
             (POST "/" request (v/post-section id request "where-you-live"))
             ))

   (context "/application-forms/:id/where-you-live" [id]
           (defroutes where-you-live
             (GET "/" request (v/get-section id request "03-where-you-live.html"))
             (POST "/" request (v/post-section id request "purpose-of-application"))
             ))

   (context "/application-forms/:id/purpose-of-application" [id]
           (defroutes purpose-of-application
             (GET "/" request (v/get-section id request "04-purpose-of-application.html"))
             (POST "/" request (v/post-section id request "confirmation"))
             ))

   (context "/application-forms/:id/confirmation" [id]
           (defroutes confirmation
             (GET "/" request (v/get-section id request "05-confirmation.html"))
             (POST "/" request (v/post-section id request "submitted"))
             ))

   (context "/application-forms/:id/submitted" [id]
           (defroutes submission
             (GET "/" request (v/get-section id request "06-submitted.html"))             
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

