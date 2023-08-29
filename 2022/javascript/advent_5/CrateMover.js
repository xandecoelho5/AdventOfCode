export default class CrateMover {
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
