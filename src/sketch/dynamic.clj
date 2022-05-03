(ns sketch.dynamic
  (:require [clojure.pprint :as pp]
            [quil.core :as q]))

(defn save-frame-to-disk
  ([]
   (q/save-frame (pp/cl-format nil
                               "frames/~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-####.jpeg"
                               (q/year) (q/month) (q/day)
                               (q/hour) (q/minute) (q/seconds))))
  ([state _]
   (save-frame-to-disk)
   state))

(defn- half-width
  []
  (* (q/width)
     0.5))

(defn- half-height
  []
  (* (q/height)
     0.5))

(defn- draw-ellipse
  []
  (q/with-translation [(half-width)
                       (half-height)]
                      (q/with-rotation [(q/random (* q/PI 2))]
                                       (q/ellipse (q/random (- (half-width))
                                                            (half-width))
                                                  (q/random (- (half-height))
                                                            (half-height))
                                                  (q/random 799)
                                                  (q/random 11.0)))))

(defn- draw-ellipses
  []
  (q/fill 44 10 99 1.0)
  (q/stroke 44 10 99 1.0)
  (dotimes [_ 11]
    (draw-ellipse)))

(defn- draw-circles
  []
  (q/with-translation [(half-width)
                       (half-height)]
                      (dotimes [r 535]
                        (q/stroke 180 9 63 (q/random 1.0))
                        (q/ellipse (q/random -5 5) (q/random -5 5) r r))))

(defn draw
  []
  (q/no-loop)
  (q/background 44 10 99)
  (q/no-fill)
  (draw-circles)
  (draw-ellipses)
  (save-frame-to-disk))

(defn initialise
  []
  (q/smooth)
  (q/color-mode :hsb 360 100 100 1.0))