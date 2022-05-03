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

(defn- width-proportion
  "A proportion of the frame width."
  [p]
  (* (q/width)
     p))

(defn- height-proportion
  "A proportion of the frame height."
  [p]
  (* (q/height)
     p))

(defn- draw-ellipse
  "Create a 'slash' in the circles."
  []
  (q/with-translation [(width-proportion 0.5)
                       (height-proportion 0.5)]
    (q/with-rotation [(q/random (* q/PI 2))]
      (q/ellipse (q/random (- (width-proportion 0.5))
                           (width-proportion 0.5))
                 (q/random (- (height-proportion 0.5))
                           (height-proportion 0.5))
                 (q/random 799.0)
                 (q/random 11.0)))))

(defn- draw-ellipses
  "Create N slashes in the circles."
  [n]
  (q/fill 44 10 99 1.0)
  (q/stroke 44 10 99 1.0)
  (dotimes [_ n]
    (draw-ellipse)))

(defn- draw-circles
  "Draw N circles."
  [n]
  (q/with-translation [(width-proportion 0.5)
                       (height-proportion 0.5)]
    (dotimes [r n]
      (q/stroke 180 9 63 (q/random 1.0))
      (q/ellipse (q/random -5 5) (q/random -5 5) r r))))

(defn draw
  []
  (q/no-loop)
  (q/background 44 10 99)
  (q/no-fill)
  (draw-circles 535)
  (draw-ellipses 11)
  (save-frame-to-disk))

(defn initialise
  []
  (q/smooth)
  (q/color-mode :hsb 360 100 100 1.0))