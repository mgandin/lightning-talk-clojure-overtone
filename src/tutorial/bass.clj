(use 'overtone.live)
(use 'overtone.inst.sampled-piano)
(demo 30
      (let [trig (coin-gate 0.5 (impulse:kr 2))
            note (demand trig 0 (dseq (shuffle (map midi->hz (conj (range 24 45) 22))) INF))
            sweep (lin-exp (lf-saw (demand trig 0 (drand [1 2 2 3 4 5 6 8 16] INF))) -1 1 40 5000)

            son (mix (lf-saw (* note [0.99 1 1.01])))
            son (lpf son sweep)
            son (normalizer son)
            son (+ son (bpf son 2000 2))

            ;;special flavours
            ;;hi manster
            son (select (< (t-rand:kr :trig trig) 0.05) [son (* 4 (hpf son 1000))])

            ;;sweep manster
            son (select (< (t-rand:kr :trig trig) 0.05) [son (* 4 (hpf son sweep))])

            ;;decimate
            son (select (< (t-rand:kr :trig trig) 0.05) [son (round son 0.1)])

            son (tanh (* son 5))
            son (+ son (* 0.3 (g-verb son 10 0.1 0.7)))
            son (* 0.3 son)]

        [son son]))

(stop)

