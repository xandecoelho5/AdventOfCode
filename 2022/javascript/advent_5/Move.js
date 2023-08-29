export default class Move {
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
