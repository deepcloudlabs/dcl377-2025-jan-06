export default function Photo({label, id, value, handleChange}) {
    function handleInputFileChange(event){
        const fileName = event.target.files[0];
        const fileReader = new FileReader();
        fileReader.onload = (e) => {
            handleChange(e.target.result);
        }
        fileReader.readAsDataURL(fileName);
    }
    return (
        <div className="input-group">
            <label htmlFor={id}>{label}:</label>
            <img className="img-thumbnail"
                 src={value} alt=""></img>
            <label>

                <input type="file"
                       style={{display: "none"}}
                       id={id}
                       name={id}
                       onChange={handleInputFileChange}></input>
                <span className="btn btn-success">File</span>
            </label>
        </div>
    );
}
