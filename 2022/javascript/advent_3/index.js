import fs from "fs";

const readLinesFromFile = () => fs.readFileSync("input.txt", "utf-8").split("\r\n");

function getCharValue(c) {
  if (c >= "A" && c <= "Z") return c.charCodeAt(0) - 38;
  return c.charCodeAt(0) - 96;
}

function calculatePriority(callback) {
  const data = readLinesFromFile();
  return callback(data);
}

function calculateTotalItemPriority(lines) {
  const calculateItemPriority = (line) => {
    let half = line.length / 2;
    let first = line.substring(0, half);
    let second = line.substring(half);

    const getCommonItem = () => {
      for (let c of first) if (second.indexOf(c) > -1) return c;
    };

    return getCharValue(getCommonItem(first, second));
  };

  return lines.map((e) => calculateItemPriority(e)).reduce((a, b) => a + b, 0);
}

function calculateTotalBadgePriority(lines) {
  const calculateBadgePriority = (subLines) => {
    subLines.sort((a, b) => b.length - a.length);

    const commonBadge = Array.from(subLines[0]).find((c) => subLines[1].includes(c) && subLines[2].includes(c));

    return getCharValue(commonBadge);
  };

  const resultArr = [];

  while (lines.length > 0) {
    resultArr.push(calculateBadgePriority(lines.splice(0, 3)));
  }

  return resultArr.reduce((a, b) => a + b, 0);
}

console.log(calculatePriority(calculateTotalItemPriority));
console.log(calculatePriority(calculateTotalBadgePriority));
