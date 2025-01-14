export default function CheckBox({label, id, value, handleChange}) {
    return (
        <div className="form-check">
            <label htmlFor={id}>{label}
                <input type="checkbox"
                       className="form-check-input"
                       id={id}
                       name={id}
                       checked={value}
                       onChange={handleChange}></input>
            </label>
        </div>
    );
}
