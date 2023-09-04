def main():
  def get_marker(line, distinctCharacters):
    for i in range(0, len(line) - distinctCharacters - 1):
      if (len(set(line[i: i + distinctCharacters])) == distinctCharacters):
        return i + distinctCharacters

  with open('resources/day6.txt') as f:
    line = f.readline()

  print(get_marker(line, 4))
  print(get_marker(line, 14))


main()
