numbers = [42, 8, 15, 16, 23, 4];
console.log(numbers);
numbers.sort(
    function (a, b) {
        return b - a ;
    }
)
console.log(numbers);
