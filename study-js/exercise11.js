numbers = [42, 8, 15, 16, 23, 4];
let [first,second,...rest] = numbers;
console.log(first);
console.log(second);
console.log(rest);
let numbers2 = [...numbers];
console.log(numbers[0]);
numbers2[0]++;
console.log(numbers[0]);
