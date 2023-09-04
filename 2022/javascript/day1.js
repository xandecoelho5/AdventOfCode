const readline = require("fs").readFileSync("resources/day1.txt", "utf-8");

try {
  let split = readline.split("\n\r");
  let sum = 0;
  const arr = [];
  for (let line of split) {
    for (let value of line.split("\n")) {
      sum += Number(value);
    }
    arr.push(sum);
    sum = 0;
  }
  arr.sort((a, b) => b - a);
  console.log(arr);
  console.log(arr[0] + arr[1] + arr[2]);
} catch (err) {
  console.error(err);
}
