async function sun(n){
    let sequence = [n];
    while(n>1){
        if (n%2 === 0){
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }
        sequence.push(n);
    }
    return sequence;
}
console.log("Application is just started...")
sun(17).then(seq => console.log(seq));
console.log("Application is just completed...")
