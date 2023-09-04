const lines = require("fs").readFileSync("resources/day7.txt", "utf-8").split("\r\n");

const sum = (arr) => arr.reduce((a, b) => a + b, 0);

const sizeArr = [];

class Dir {
  size = 0;
  constructor(name = "", parent, dirs = new Set()) {
    this.name = name;
    this.parent = parent;
    this.dirs = dirs;
  }
}

const updateDirectorySize = (directory) => {
  let totalSum = directory.size;
  if (directory.dirs.size) {
    for (const dir of directory.dirs.values()) {
      totalSum += updateDirectorySize(dir);
    }
  }
  directory.size = totalSum;
  return totalSum;
};

const calculateSizeOfDir = (directory, threshold, totalSize) => {
  if (directory.dirs.size) {
    for (const dir of directory.dirs.values()) {
      totalSize += calculateSizeOfDir(dir, threshold, totalSize);
    }
  }

  if (directory.size <= threshold) {
    sizeArr.push(directory.size);
    return directory.size + totalSize;
  }

  return totalSize;
};

const root = new Dir("/", null);
let currentDir = root;

const onChangeDirectory = (target = "") => {
  if (target == "/") {
    currentDir = root;
  } else if (target == "..") {
    currentDir = currentDir.parent;
  } else {
    for (const dir of currentDir.dirs.values()) {
      if (dir.name == target) {
        currentDir = dir;
      }
    }
  }
};

const addInfoToCurrentDirectory = (line = "") => {
  if (line.includes("dir")) {
    const name = line.split("dir ")[1];
    currentDir.dirs.add(new Dir(name, currentDir));
  } else {
    const [size] = line.split(" ");
    currentDir.size += Number(size);
  }
};

const findMinimumSizeForDirectory = (directory, requiredSpace, minimum) => {
  let min = minimum;
  if (directory.dirs.size) {
    for (const dir of directory.dirs.values()) {
      min = findMinimumSizeForDirectory(dir, requiredSpace, min);
    }
  }
  if (directory.size > requiredSpace) {
    min = Math.min(directory.size, min);
  }
  return min;
};

const calculateUsedSpace = (directory) => {
  let totalSum = 0;
  if (directory.dirs.size) {
    for (const dir of directory.dirs.values()) {
      totalSum += calculateUsedSpace(dir);
    }
  }
  return totalSum + directory.size;
};

const main = () => {
  for (const line of lines) {
    if (line.includes("$ cd")) {
      onChangeDirectory(line.split("cd ")[1]);
    } else if (!line.includes("$ ls")) {
      addInfoToCurrentDirectory(line);
    }
  }

  const usedSpace = calculateUsedSpace(root);

  updateDirectorySize(root);
  calculateSizeOfDir(root, 100000, 0);
  console.log(sum(sizeArr));

  const availableSpace = 70000000;
  const neededSpace = 30000000;
  const unusedSpace = availableSpace - usedSpace;
  const requiredSpace = neededSpace - unusedSpace;
  const min = findMinimumSizeForDirectory(root, requiredSpace, availableSpace);
  console.log(min);
};

main();
