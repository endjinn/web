(ns apply-for-something.views
  (:use [apply-for-something.stages])
  (:require [apply-for-something.application :as a]
            [apply-for-something.responses :as r]))


(defstages
  [[:start-application      "01-start-application.html"]
   [:about-you              "02-about-you.html"]
   [:where-you-live         "03-where-you-live.html"]
   [:purpose-of-application "04-purpose-of-application.html"]
   [:confirmation           "05-confirmation.html"]
   [:submitted              "06-submitted.html"]])

(defn get-start-application [request]
  (r/render-html-template-to-response (first (:start-application *stages*))))

(defn post-start-application [request]
  (let [application (a/create-application)]
    (r/redirect-to-next-section (name (second (:start-application *stages*))) (:id application))))


(defn get-section [id section request]
  (let [template (first (section *stages*))]
    (r/render-html-template-to-response template)))

(defn post-section [id section request]
  (let [next-section (name (second (section *stages*)))]   
    (r/redirect-to-next-section next-section id)))


