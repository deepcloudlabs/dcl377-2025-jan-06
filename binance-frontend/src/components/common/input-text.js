import React from "react";

export default function InputText({htmlFor,label,value,handleInput}){
    return (
        <>
            <label htmlFor={htmlFor} className="control-label">{label}:</label>
            <input type="text"
                   id={htmlFor}
                   name={htmlFor}
                   className="form-control"
                   onChange={handleInput}
                   value={value}/>
        </>
)
    ;
}
