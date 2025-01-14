export default function Button({buttonClass,label,clickHandler}) {
    return (
        <button className={"btn ".concat(buttonClass)}
                onClick={clickHandler}>**{label}**</button>
    )
}
