(use 'overtone.live)
(definst foo[] (saw 220))
(foo)
(stop)

(definst bar [freq 440] (saw freq))
(bar)
(bar 220)
(bar 110)
(stop)

(definst qix [freq 440 low 0.3] (* low (saw freq)))
(qix)
(qix 220 3)
(qix 440 1)
(ctl qix :freq 660 :low 0.5)
(stop)

(odoc saw)
(odoc line)
(odoc sin-osc)

(definst trem [freq 440 depth 10 rate 6 length 3]
  (* 0.3
     (line:kr 0 1 length FREE)
     (saw (+ freq (* depth (sin-osc:kr rate))))))
(trem 220 60 0.8)
(trem 60 30 0.2)
(stop)

(demo 7 (lpf (mix (saw [50 (line 100 1600 5) 101 100.5]))
                  (lin-lin (lf-tri (line 2 20 5)) -1 1 400 4000)))
