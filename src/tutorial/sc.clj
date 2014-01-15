(use 'overtone.live)

(definst cottle []
       (let [trig (impulse:kr 5)
             freq (midicps (t-rand:kr [36 60] [72 86] trig))
             rate 5
             ratio 2
             env (env-gen:kr (perc 0 (/ 1 rate)) :gate trig)]
         (* (pm-osc freq (* freq ratio) (+ 3 (* 4 env))) env)))

(cottle)
(stop)