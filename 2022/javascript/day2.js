const readline = require("fs").readFileSync("resources/day2.txt", "utf-8");

const scoreMap = {
  A: { X: 4, Y: 8, Z: 3 },
  B: { X: 1, Y: 5, Z: 9 },
  C: { X: 7, Y: 2, Z: 6 },
};

const updatedScoreMap = {
  A: { X: 3, Y: 4, Z: 8 },
  B: { X: 1, Y: 5, Z: 9 },
  C: { X: 2, Y: 6, Z: 7 },
};

const calculateScoreSum = (map) =>
  readline
    .split("\r\n")
    .map((e) => {
      const options = e.split(" ");
      return Number(map[options[0]][options[1]]);
    })
    .reduce((acc, curr) => acc + curr, 0);

console.log(calculateScoreSum(scoreMap));
console.log(calculateScoreSum(updatedScoreMap));
