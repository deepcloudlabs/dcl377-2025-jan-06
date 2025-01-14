export function generateRandomNumber(min, max){
    return Math.floor(Math.random() * (max - min + 1) + min);
}

export function generateOneLotteryNumber() {
    let numbers = [generateRandomNumber(1, 60)];
    while (numbers.length < 6) {
        let number = generateRandomNumber(1, 60);
        if (numbers.includes(number)) continue;
        numbers.push(number);
    }
    numbers.sort((x, y) => x - y);
    return numbers;
}

export function generateLotteryNumbers(column) {
    let numbers = [];
    for (let i = 0; i < column; i++) {
        numbers.push(generateOneLotteryNumber())
    }
    return numbers;
}
