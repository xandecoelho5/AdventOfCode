import fs from "fs";
import CrateMover from "./CrateMover.js";
import Move from "./Move.js";

const readFromFile = () => fs.readFileSync("input.txt", "utf-8").split("\r\n");

const lines = readFromFile();

const getQuantityOfStacks = (line = "") => {
  let res = 0;
  for (let i = 0; i < line.length; i += 3) {
    res++;
    i++;
  }
  return res;
};

const createCrateMover = (lines) => {
  const crateMover = new CrateMover(getQuantityOfStacks(lines[0]), []);

  for (const line of lines.filter((e) => e.includes("["))) {
    crateMover.addCrateToStack(line);
  }

  crateMover.moves = lines.filter((e) => e.startsWith("move")).map((e) => Move.from(e));

  return crateMover;
};

const printTopCratesLetters = (crateMover) => {
  console.log(crateMover.stacks.map((stack) => stack[stack.length - 1][1]).join(""));
};

const performMoves = (crateMover, moveFunction) => {
  for (const move of crateMover.moves) {
    moveFunction(crateMover, move);
  }
  return crateMover;
};

printTopCratesLetters(performMoves(createCrateMover(lines), (mover, move) => mover.doMove9000(move)));
printTopCratesLetters(performMoves(createCrateMover(lines), (mover, move) => mover.doMove9001(move)));
