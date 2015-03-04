(ns apply-for-something.views  
  (:require [apply-for-something.application :as a]
            [apply-for-something.responses :as r]))

(defn get-start-application [request template]
  (r/render-html-template-to-response template))

(defn post-start-application [request next-section]
  (let [application (a/create-application)]
    (r/redirect-to-next-section next-section (:id application))))

(defn get-section [id request template]
  (r/render-html-template-to-response template ))

(defn post-section [id request next-section]
  (r/redirect-to-next-section next-section id))

(def sections-to-templates
  { :about-you "02-about-you.html"
   })

(defn get-section-new [id section request]
  (let [template (section sections-to-templates)]
    (r/render-html-template-to-response template)))


