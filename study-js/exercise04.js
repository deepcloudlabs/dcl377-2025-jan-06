class Customer{
    constructor(name){
        this.name = name;
        // this.sayHello = this.sayHello.bind(this);
    }

    sayHello = () => {
        console.log(`Hello, ${this.name}!`);
    }
}
let jack = new Customer("jack shephard");
setInterval(jack.sayHello,3_000);
// jack.sayHello(); // sayHello(jack)
