let circle = {x:0, y:100, radius: 200, paint: { thickness: 4, style: "SOLID"}};
let circle2 = {...circle};
circle2.paint = {...circle.paint}
console.log(circle)
circle2.paint.thickness++;
console.log(circle)
