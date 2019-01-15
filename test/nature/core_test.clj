(ns nature.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as csa]
            [nature.core :as nature]
            [nature.spec :as s]
            [nature.genetic-operators :as go]
            [nature.population-presets :as pp]))

(deftest evolve-test
  (testing "Check that evolution is successfull"
    (let [result (nature/evolve pp/binary-genome
                                pp/default-sequence-length
                                pp/default-population-size
                                pp/default-generation-count
                                pp/sum-alleles
                                [(go/crossover pp/sum-alleles)]
                                [(partial go/mutation-operator pp/sum-alleles pp/binary-genome 1)])]
      (is (csa/valid? ::s/population result))
      (is (= 1 (count result))))))
