def get_set_from_range_section(section: str) -> set:
  start, end = map(int, section.split('-'))
  return set(range(start, end + 1))


def set_overlaps(first: set, second: set) -> bool:
  return first.issubset(second) or second.issubset(first)


def set__range_overlaps(first: set, second: set) -> bool:
  return bool(first.intersection(second))


count_all_overlaps = 0
count_range_overlaps = 0

with open("input.txt") as f:
  for line in f:
    sections = line.strip().split(",")
    first_section = get_set_from_range_section(sections[0])
    second_section = get_set_from_range_section(sections[1])

    if (set_overlaps(first_section, second_section)):
      count_all_overlaps += 1
    if (set__range_overlaps(first_section, second_section)):
      count_range_overlaps += 1

print(count_all_overlaps)
print(count_range_overlaps)
