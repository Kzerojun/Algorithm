SELECT COUNT(ID) as COUNT
FROM ecoli_data

WHERE (GENOTYPE & 2) = 0  
  AND ((GENOTYPE & 1) != 0
       OR (GENOTYPE & 4) != 0);
       