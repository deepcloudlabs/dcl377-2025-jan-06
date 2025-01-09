async function run(n){
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
    let seq = await run(17);
    console.log(seq)
}
main().then(()=>{
    console.log("Application is just completed...")
})
