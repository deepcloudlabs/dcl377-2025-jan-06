async function asyncRun(n){
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
async function main(){
    console.log("Application is just started...")
    let result = await Promise.all([asyncRun(17),asyncRun(21),asyncRun(23)]);
    console.log(result)
}
main().then(()=>{
    console.log("Application is just completed...")
})
