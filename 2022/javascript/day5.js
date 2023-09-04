const lines = require("fs").readFileSync("resources/day5.txt", "utf-8").split("\r\n");

class Move {
  constructor(quantity, from, to) {
    this.quantity = quantity;
    this.from = from;
    this.to = to;
  }

  static from(line) {
    const split = line.split(" ");
    return new Move(Number(split[1]), Number(split[3]) - 1, Number(split[5]) - 1);
  }
}

class CrateMover {
  constructor(numOfStacks, moves = []) {
    this.stacks = this.initializeStacks(numOfStacks);
    this.moves = moves;
  }

  initializeStacks(numOfStacks) {
    return Array.from({ length: numOfStacks }, () => []);
  }

  addCrateToStack(line) {
    for (let i = 0, j = 0; i <= line.length - 3; i += 3, j++) {
      const substr = line.substring(i, i + 3).trim();
      if (substr) {
        this.stacks[Math.floor((i - j) / 3)].unshift(substr);
      }
      i++;
    }
  }

  doMove9000(move) {
    const { quantity, from, to } = move;
    for (let i = 0; i < quantity; i++) {
      this.stacks[to].push(this.stacks[from].pop());
    }
  }

  doMove9001(move) {
    const { stacks } = this;
    const { quantity, from, to } = move;

    const movedItems = [];
    for (let i = 0; i < quantity; i++) {
      movedItems.push(stacks[from].pop());
    }

    for (let i = 0; i < quantity; i++) {
      stacks[to].push(movedItems.pop());
    }
  }
}

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
