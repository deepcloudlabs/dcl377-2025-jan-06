class Circle {
    constructor(x, y, radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    area() {
        return Math.PI * this.radius * this.radius;
    }

}

let circle1 = new Circle(0, 0, 100);
console.log(circle1.area());
