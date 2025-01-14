export default function Badge({bgColor,value,label}) {
    return (
        <div className="mb-3">
            {label}:<span className={"badge ".concat(bgColor)}>{value}</span>
        </div>
    )
}
