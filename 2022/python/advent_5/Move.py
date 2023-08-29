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
