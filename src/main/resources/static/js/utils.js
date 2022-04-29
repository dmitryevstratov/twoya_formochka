function checkEmptyField(field) {
    if (field == null || field === "") {
        return "";
    }else {
        return field;
    }
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}