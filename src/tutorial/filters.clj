(use 'overtone.live)

(demo 10 (lpf (saw 100) (mouse-x 40 5000 EXP)))
(demo 10 (hpf (saw 100) (mouse-x 40 5000 EXP)))
(demo 10 (bpf (saw 100) (mouse-x 40 5000 EXP) (mouse-y 0.1 1 LIN)))

(let [freq 220]
  (demo (pluck (* (white-noise) (env-gen (perc 0.001 2) :action FREE)) 1 3 (/ 1 freq))))
