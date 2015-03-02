(ns apply-for-something.responses
  (:use [hbs.core]
        [ring.util.response])) 

(set-template-path! "/templates" ".html")

(defn render-html-template-to-response
  ([template] (render-html-template-to-response template {}))
  ([template model]   
   (let [data (merge model {:applicationProgress {:stage template}})]
     (-> (response (render-file template data))
         (content-type "text/html")))))

(defn redirect-to-next-section [section application-id]
  (redirect-after-post (format "/application-forms/%s/%s" application-id section)))
