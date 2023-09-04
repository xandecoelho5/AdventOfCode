class Move:
  def __init__(self, quantity, fron, to):
    self.quantity = quantity
    self.fron = fron
    self.to = to

  @staticmethod
  def fromLine(line: str):
    split = line.split(" ")
    return Move(int(split[1]), int(split[3]) - 1, int(split[5]) - 1)

  def __str__(self) -> str:
    return str.format('Move: quantity={} fron={} to={}', self.quantity, self.fron, self.to)


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


def get_lines_from_file() -> [str]:
  with open("resources/day5.txt") as f:
    return [lines.replace("\n", "") for lines in f]


def get_quantity_of_stacks(line: str) -> int:
  result = 0
  i = 0
  while i < len(line):
    result += 1
    i += 4
  return result


def get_crate_mover_from_lines(lines: [str]) -> CrateMover:
  crate_mover = CrateMover(get_quantity_of_stacks(lines[0]))

  for line in filter(lambda line: '[' in line, lines):
    crate_mover.addCrateToStack(line)

  crate_mover.moves = map(lambda l: Move.fromLine(l),
                          filter(lambda line: line.startswith('move'), lines))
  return crate_mover


def do_move_9000(mover, move):
  mover.do_move_9000(move)


def do_move_9001(mover, move):
  mover.do_move_9001(move)


def proccess_move(crate_mover, move_function):
  for move in crate_mover.moves:
    move_function(crate_mover, move)
  return crate_mover


def print_top_crate_letters(crate_moves):
  print(''.join(stack[-1][1] for stack in crate_moves.stacks))


print_top_crate_letters(proccess_move(
  get_crate_mover_from_lines(get_lines_from_file()), do_move_9000))

print_top_crate_letters(proccess_move(
  get_crate_mover_from_lines(get_lines_from_file()), do_move_9001))
