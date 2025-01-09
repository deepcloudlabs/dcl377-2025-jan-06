function* fun(n){
    while(n>1){
        if (n%2 === 0){
            n = n / 2;
        } else {
            n = 3 * n + 1;
        }
        console.log(`yielding ${n}`);
        yield n;
    }
}

for(let number of fun(17)){
    console.log(`for: ${number}`)
}
