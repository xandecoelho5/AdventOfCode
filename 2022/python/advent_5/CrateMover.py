from Move import Move


class CrateMover:
  def __init__(self, num_of_stacks: int, moves: [Move] = []):
    self.stacks = self.initialize_stacks(num_of_stacks)
    self.moves = moves

  def initialize_stacks(self, num_of_stacks: int):
    return [[] for _ in range(num_of_stacks)]

  def addCrateToStack(self, line: str):
    j = 0
    i = 0
    while i <= len(line) - 3:
      x = line[i:i + 3].strip()
      substr = x
      if (substr):
        self.stacks[int((i - j) / 3)].insert(0, substr)
      i += 4
      j += 1

  def do_move_9000(self, move: Move):
    for _ in range(move.quantity):
      self.stacks[move.to].append(self.stacks[move.fron].pop())

  def do_move_9001(self, move: Move):
    num_to_move = move.quantity
    aux_stack = [self.stacks[move.fron].pop() for _ in range(num_to_move)]
    self.stacks[move.to].extend(aux_stack[::-1])

  def __str__(self) -> str:
    return str.format("stacks {}, moves {}", self.stacks, self.moves)
