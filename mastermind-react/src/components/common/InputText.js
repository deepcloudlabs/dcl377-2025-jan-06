export default function InputText({label,name, value, changeHandler, placeHolder}) {
    return (
        <>
            <label className="form-label" htmlFor={name}>{label}:</label>
            <input type="text"
                   id={name}
                   name={name}
                   className="form-control"
                   placeholder={placeHolder}
                   onChange={changeHandler}
                   value={value}/>
        </>
    )
}
