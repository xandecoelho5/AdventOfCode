sum = 0
list = []
with open('input.txt') as f:
  for line in f:
    if (line != '\n'):
      sum += int(line)
    else:
      list.append(sum)
      sum = 0

list.sort(reverse=True)
print(list)
print(list[0] + list[1] + list[2])