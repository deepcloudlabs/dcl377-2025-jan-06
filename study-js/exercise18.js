function gun(n){
    console.log(`gun(): is just started...`)
    return new Promise((resolve,reject) => {
        console.log(`promise(): is just started...`)
        let sequence = [n];
        while(n>1){
            if (n%2 === 0){
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            //reject("something is wrong");
            sequence.push(n);
        }
        setTimeout(() => resolve(sequence), 5_000) ;
    });
}
console.log("Application is just started...")
gun(17).then(seq => console.log(seq));
console.log("Application is just completed...")
