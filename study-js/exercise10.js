numbers = [42, 8, 15, 16, 23, 4];
// imperative programming
let total = 0
for (let number of numbers) {
    if(number % 2 === 0) {
        let cubed = number ** 3;
        total += cubed;
    }
}
console.log(total);
// functional programming
total =
numbers.filter( n => n % 2 === 0)
       .map( u => u ** 3)
       .reduce((acc, x) => acc + x, 0)
console.log(total);
