(ns apply-for-something.responses
  (:use [hbs.core]
        [hbs.helper]
        [ring.util.response])) 

(set-template-path! "/templates" "")
(register-js-helpers! "resources/templates/helpers.js")

(defn render-html-template-to-response
  ([template] (render-html-template-to-response template {}))
  ([template model]   
   (let [data (merge model {:stage template})]
     (-> (response (render-file template data))
         (content-type "text/html")))))

(defn redirect-to-next-section [section application-id]
  (redirect-after-post (format "/application-forms/%s/%s" application-id section)))
