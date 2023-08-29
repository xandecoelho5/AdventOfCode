import fs from "fs";

const range = (start, stop, step) => Array.from({ length: (stop - start) / step + 1 }, (_, i) => start + i * step);

const readFromFile = () => fs.readFileSync("input.txt", "utf-8").split("\r\n");

const lines = readFromFile();

const getSetFromRangeSection = (section) => {
  const [start, end] = section.split("-");
  return range(Number(start), Number(end), 1);
};

const doesSetOverlaps = (first, second) => first.every((e) => second.includes(e)) || second.every((e) => first.includes(e));

const doesRangeOverlaps = (first = [], second) => first.some((e) => second.includes(e));

var countAllOverlaps = 0;
var countRangeOverlaps = 0;

const calculateSetRanges = () => {
  for (const line of lines) {
    const [firstSection, secondSection] = line.split(",");
    const firstSectionRange = getSetFromRangeSection(firstSection);
    const secondSectionRange = getSetFromRangeSection(secondSection);
    if (doesSetOverlaps(firstSectionRange, secondSectionRange)) {
      countAllOverlaps++;
    }
    if (doesRangeOverlaps(firstSectionRange, secondSectionRange)) {
      countRangeOverlaps++;
    }
  }
  console.log(countAllOverlaps);
  console.log(countRangeOverlaps);
};

calculateSetRanges();
