from CrateMover import CrateMover
from Move import Move


def get_lines_from_file() -> [str]:
  with open("input.txt") as f:
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
