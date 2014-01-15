(use 'overtone.live)

(defsynth synthe1 [freq 440]
  (out 0 (sin-osc freq)))
(synthe1)
(stop)

(defsynth synthe2 [freq 440]
  (out 0 (sin-osc freq))
  (out 1 (saw freq)))

(synthe2)
(stop)

(odoc square)
(defsynth synthe3 [freq 440]
  (out 0 (* 0.5 (+ (square (* 0.5 freq)) (sin-osc freq))))
    )

(synthe3)
(stop)

(defsynth synthe4 [freq 440]
  (out 0 (* 0.5 (+ (square (* 0.5 freq)) (sin-osc freq))))
  (out 1 (* 0.5 (+ (square (* 0.5 freq)) (sin-osc freq))))
    )

(synthe4)
(stop)

(demo 10(lpf (sin-osc [440 660]) 600))
(stop)

(defsynth synthe5 [freq 440]
  (out 0 (* [0.5 0.5] (+ (square (* 0.5 freq)) (sin-osc freq)))))

(synthe3)

(defsynth synthe6 [freq 440]
  (out 0 (* 0.5 [(square (* 0.5 freq)) (sin-osc freq)])))
(synthe6)
