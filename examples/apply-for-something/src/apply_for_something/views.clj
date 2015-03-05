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
  (r/render-html-template-to-response (get-stage-template :start-application)))

(defn post-start-application [request]
  (let [application (a/create-application)]
    (r/redirect-to-next-stage (get-next-stage :start-application) (:id application))))

(defn get-stage [id stage request]  
  (r/render-html-template-to-response (get-stage-template stage)))

(defn post-stage [id stage request] 
  (r/redirect-to-next-stage (get-next-stage stage) id))


