def calculate_char_value(c: str) -> int:
  if 'A' <= c <= 'Z':
    return ord(c) - 38
  return ord(c) - 96


def calculate_priority(callback):
  with open("input.txt") as f:
    lines = [line.strip() for line in f]
    return sum(callback(lines))


def calculate_badge_priority(lines: list[str]):
  def calculate_badge_value(lines: list[str]):
    def get_common_badge(first: str, second: str, third: str) -> str:
      return [char for char in first if char in second and char in third][0]

    lines.sort(key=lambda a: len(a), reverse=True)
    return calculate_char_value(get_common_badge(lines[0], lines[1], lines[2]))

  return [calculate_badge_value(lines[i:i + 3]) for i in range(0, len(lines), 3)]


def calculate_item_priority(lines: list[str]):
  def calculate_item_value(rucksack: str) -> int:
    def get_common_item(first: str, second: str) -> str:
      return [char for char in first if char in second][0]

    half = int(len(rucksack) / 2)
    first = rucksack[:half]
    second = rucksack[half:len(rucksack)]
    item = get_common_item(first, second)
    return calculate_char_value(item)
  return map(calculate_item_value, lines)


print(calculate_priority(calculate_item_priority))
print(calculate_priority(calculate_badge_priority))
