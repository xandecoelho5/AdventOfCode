def calculate_score_sum(map):
  list = []
  with open("resources/day2.txt") as f:
    for line in f:
      options = line.strip().split(" ")
      list.append(map[options[0]][options[1]])
  return sum(list)


score_map = {
  "A": {'X': 4, 'Y': 8, 'Z': 3},
  "B": {'X': 1, 'Y': 5, 'Z': 9},
  "C": {'X': 7, 'Y': 2, 'Z': 6}
}

updated_score_map = {
  "A": {'X': 3, 'Y': 4, 'Z': 8},
  "B": {'X': 1, 'Y': 5, 'Z': 9},
  "C": {'X': 2, 'Y': 6, 'Z': 7}
}

print(calculate_score_sum(score_map))
print(calculate_score_sum(updated_score_map))
