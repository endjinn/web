(ns apply-for-something.views  
  (:require [apply-for-something.application :as a]
            [apply-for-something.responses :as r]))


(defn get-start-application [request]
  (r/render-html-template-to-response "01-start-application"))

(defn post-start-application [request]
  (let [application (a/create-application)]
    (r/redirect-to-next-section "about-you" (:id application))))

(defn get-about-you [id request]
  (r/render-html-template-to-response "02-about-you" {:application {:id id}}))

(defn post-about-you [id request]
  (r/redirect-to-next-section "where-you-live" id))

(defn get-where-you-live [id request]
  (r/render-html-template-to-response "03-where-you-live"))

(defn post-where-you-live [id request]
  (r/redirect-to-next-section "purpose-of-application" id))

(defn get-purpose-of-application [id request]
  (r/render-html-template-to-response "04-purpose-of-application"))

(defn post-purpose-of-application [id request]
  (r/redirect-to-next-section "confirmation" id))

(defn get-confirmation [id request]
  (r/render-html-template-to-response "05-confirmation"))

(defn post-confirmation [id request]
  (r/redirect-to-next-section "submitted" id))

(defn get-submitted [id request]
  (r/render-html-template-to-response "06-submitted"))
