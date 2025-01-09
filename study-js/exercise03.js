class ColorfulCircle extends Circle {
    constructor(x, y, radius,color) {
        super(x,y,radius);
        this.color = color;
    }

}

let circle1 = new ColorfulCircle(0, 0, 100, "Red");
console.log(circle1.area());
