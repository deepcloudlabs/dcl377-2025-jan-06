let circle = {x:0, y:100, radius: 200, paint: { thickness: 4, style: "SOLID"}, fun: function(){}};
let circle2 = JSON.parse(JSON.stringify(circle));
console.log(circle)
circle2.paint.thickness++;
console.log(circle)
console.log(circle2)
for (let field in circle ){
    if (typeof(circle[field]) === "object"){

    }
}
