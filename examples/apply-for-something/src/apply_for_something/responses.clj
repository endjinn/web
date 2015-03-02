(ns apply-for-something.responses
  (:use [stencil.core]
        [ring.util.response])) 

(defn render-html-template-to-response
  ([template] (render-html-template-to-response template {}))
  ([template data]
   (-> (response (render-file (format "templates/%s.html" template) data))
       (content-type "text/html"))))

(defn redirect-to-next-section [section application-id]
  (redirect-after-post (format "/application-forms/%s/%s" application-id section)))
