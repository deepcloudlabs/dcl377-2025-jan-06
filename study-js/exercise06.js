numbers = []
numbers.push(4);
numbers.push(8);
numbers.push(15);
numbers.push(16);
numbers.push(23);
numbers.push(42);
console.log(numbers);
numbers = [4, 8, 15, 16, 23, 42];
console.log(numbers);
console.log(numbers[0]);
console.log(numbers[5]);
numbers[0] = 400;
console.log(numbers);
console.log(numbers.length);
numbers[100] = 100;
console.log(numbers.length);
console.log(numbers[99]);
numbers[-1] = -1;
console.log(numbers.length);
console.log(numbers[-1]);
for (let i = 0; i < numbers.length; i++) {
    if (numbers[i])
      console.log(`${i} ---> ${numbers[i]}`);
}
console.log("============");
for (let i in numbers) {
    console.log(`${i} ---> ${numbers[i]}`);
}
console.log("============");
for (let number of numbers) {
    console.log(number);
}
console.log("============");
numbers.forEach((number,i) => console.log(`${i} ---> ${numbers[i]}`))
console.log("============");
numbers.forEach(console.log)
