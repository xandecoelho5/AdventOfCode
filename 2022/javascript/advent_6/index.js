const readLine = require("fs").readFileSync("input.txt", "utf-8");

const getMarker = (line = "", distinctNumbers) => {
  for (let i = 0; i < line.length - distinctNumbers - 1; i++)
    if (new Set(line.substring(i, i + distinctNumbers)).size == distinctNumbers) return i + distinctNumbers;
};

console.log(getMarker(readLine, 4));
console.log(getMarker(readLine, 14));
