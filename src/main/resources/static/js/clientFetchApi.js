//URL
const URL_CREATE = "/clients/create";
const URL_CLIENTS = "/clients";
const DATA_CLIENTS = "data-clients"
const URL_EDIT = "/clients/edit";

//Method name and headers
const POST = "POST";
const DELETE = "DELETE";
const PUT = "PUT";
const CONTENT_TYPE_JSON = "application/json";

//Create client field
const ID_ID = "#id";
const FIRST_NAME_ID = "#firstname";
const LAST_NAME_ID = "#lastname";
const SECOND_NAME_ID = "#secondname";
const BIRTHDAY_ID = "#birthday";
const EMAIL_ID = "#email";
const TELEPHONE_ID = "#telephone";
const COUNTRY_ID = "#country";
const REGION_ID = "#region";
const LOCALITY_ID = "#locality";
const STREET_ID = "#street";
const ROOM_ID = "#room";
const INDEX_ID = "#index";

//Empty value
const EMPTY_VALUE = "";
const DISPLAY_NONE = "none";
const DISPLAY_BLOCK = "block";

//Class names
const CLASSNAME_FADE = "fade";
const CLASSNAME_SHOW = "show";
const MODAL_BACKDROP = "modal-backdrop";
const MODAL_OPEN = "modal-open";

//Edit user field
const SUFFIX_EDIT_FIELD = "-edit";

//ID modal window
const MODAL_CREATE = "addClient";
const MODAL_DELETE = "delete";
const MODAL_EDIT = "editClient";

//Load clients on client page

function loadClients() {
    fetch(URL_CLIENTS)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            if (data.length > 0) {
                data.forEach((client => {
                    addClientInTable(client);
                }))
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

loadClients();

function getDataClient(suffix) {
    let id = document.querySelector(ID_ID + suffix);
    if (id == null) {
        id = "";
    } else {
        id = document.querySelector(ID_ID + suffix).value;
    }
    return {
        id: id,
        firstName: document.querySelector(FIRST_NAME_ID + suffix).value,
        lastName: document.querySelector(LAST_NAME_ID + suffix).value,
        secondName: document.querySelector(SECOND_NAME_ID + suffix).value,
        birthday: document.querySelector(BIRTHDAY_ID + suffix).value,
        email: document.querySelector(EMAIL_ID + suffix).value,
        telephone: document.querySelector(TELEPHONE_ID + suffix).value,
        address: {
            id: id,
            country: document.querySelector(COUNTRY_ID + suffix).value,
            region: document.querySelector(REGION_ID + suffix).value,
            locality: document.querySelector(LOCALITY_ID + suffix).value,
            street: document.querySelector(STREET_ID + suffix).value,
            room: document.querySelector(ROOM_ID + suffix).value,
            index: document.querySelector(INDEX_ID + suffix).value
        }
    };
}

function contentClient(client) {
    let tmp = EMPTY_VALUE;
    let address = "";

    if (client.address != null) {
        address =
            "Страна: " + client.address.country + "<br>" +
            "Регион: " + client.address.region + "<br>" +
            "Населенный пункт: " + client.address.locality + "<br>" +
            "Улица: " + client.address.street + "<br>" +
            "Квартира: " + client.address.room + "<br>" +
            "Индекс: " + client.address.index;
    }

    tmp += "<td>" + client.id + "</td>";
    tmp += "<td>" + client.firstName + "</td>";
    tmp += "<td>" + client.lastName + "</td>";
    tmp += "<td>" + client.secondName + "</td>";
    tmp += "<td>" + client.birthday + "</td>";
    tmp += "<td>" + client.email + "</td>";
    tmp += "<td>" + client.telephone + "</td>";
    tmp += "<td>" + address + "</td>";
    tmp += "<td><button onmousedown= \"fillFormEditClientById(" + client.id + ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#editClient\">\n" +
        " Редактировать" +
        "</button></td>";
    tmp += "<td><button>Удалить</button></td>";
    return tmp;
}

function addClientInTable(client) {
    let tmp = EMPTY_VALUE;
    tmp += "<tr id =" + "client-" + client.id + ">";
    tmp += contentClient(client);
    tmp += "</tr>";
    document.getElementById(DATA_CLIENTS).innerHTML += tmp;
}

function clearForm(suffix) {
    document.querySelector(FIRST_NAME_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(LAST_NAME_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(SECOND_NAME_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(BIRTHDAY_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(EMAIL_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(TELEPHONE_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(COUNTRY_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(REGION_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(LOCALITY_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(STREET_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(ROOM_ID + suffix).value = EMPTY_VALUE;
    document.querySelector(INDEX_ID + suffix).value = EMPTY_VALUE;
}

function hiddenForm(id) {
    document.body.classList.remove(MODAL_OPEN);
    document.querySelector("#" + id).style.display = DISPLAY_NONE;
    document.querySelector("#" + id).classList.remove(CLASSNAME_SHOW);
    document.body.style.overflow = "visible";

    document.querySelectorAll("." + MODAL_BACKDROP)
        .forEach(element => element.style.display = DISPLAY_NONE);
}

function openForm(id) {
    document.querySelector("#" + id).classList.add(CLASSNAME_SHOW);
    document.querySelector("#" + id).style.display = DISPLAY_BLOCK;

    if (document.querySelector("." + MODAL_BACKDROP) == null) {
        let div = document.createElement('div');
        div.style.display = DISPLAY_BLOCK;
        div.className = MODAL_BACKDROP;
        div.classList.add(CLASSNAME_SHOW);
        div.classList.add(CLASSNAME_FADE);
        document.body.append(div);
    } else {
        document.querySelector("." + MODAL_BACKDROP).style.display = DISPLAY_BLOCK;
    }

}

function parsingDate(date) {
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }

    return year + "-" + month + "-" + day;
}

//Method POST

function createClient() {
    if (checkValidityForm(EMPTY_VALUE)) {
        fetchCreateClient(URL_CREATE, getDataClient(""))
            .then((data) => {
                console.log(data);
                document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
                loadClients();
                clearForm(EMPTY_VALUE);
                hiddenForm(MODAL_CREATE);
            });
    }
}

async function fetchCreateClient(url = '', data = {}) {
    try {
        const response = await fetch(url, {
            method: POST,
            headers: {
                'Content-Type': CONTENT_TYPE_JSON
            },
            body: JSON.stringify(data)
        });
        return await response.json();
    } catch (e) {
        console.log(e)
    }
}

function checkValidityForm(suffix) {
    let firstname = document.querySelector(FIRST_NAME_ID + suffix);
    let lastname = document.querySelector(LAST_NAME_ID + suffix);
    let email = document.querySelector(EMAIL_ID + suffix);
    let birthday = document.querySelector(BIRTHDAY_ID + suffix);
    let country = document.querySelector(COUNTRY_ID + suffix);
    let region = document.querySelector(REGION_ID + suffix);
    let locality = document.querySelector(LOCALITY_ID + suffix);
    let street = document.querySelector(STREET_ID + suffix);

    if (!firstname.validity.valid) {
        firstname.focus();
        return false;
    }
    if (!lastname.validity.valid) {
        lastname.focus();
        return false;
    }
    if (!email.validity.valid) {
        email.focus();
        return false;
    }
    if (!birthday.validity.valid) {
        birthday.focus();
        return false;
    }
    if (!country.validity.valid) {
        country.focus();
        return false;
    }
    if (!region.validity.valid) {
        region.focus();
        return false;
    }
    if (!locality.validity.valid) {
        locality.focus();
        return false;
    }
    if (!street.validity.valid) {
        street.focus();
        return false;
    }

    return true;
}

//Method PUT

function fillFormEditClientById(id) {
    fetch(URL_CLIENTS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (client) {
            document.querySelector(ID_ID + SUFFIX_EDIT_FIELD).value = id;
            document.querySelector(FIRST_NAME_ID + SUFFIX_EDIT_FIELD).value = client.firstName;
            document.querySelector(LAST_NAME_ID + SUFFIX_EDIT_FIELD).value = client.lastName;
            document.querySelector(SECOND_NAME_ID + SUFFIX_EDIT_FIELD).value = client.secondName;
            document.querySelector(BIRTHDAY_ID + SUFFIX_EDIT_FIELD).value = parsingDate(new Date(client.birthday));
            document.querySelector(EMAIL_ID + SUFFIX_EDIT_FIELD).value = client.email;
            document.querySelector(TELEPHONE_ID + SUFFIX_EDIT_FIELD).value = client.telephone;
            document.querySelector(COUNTRY_ID + SUFFIX_EDIT_FIELD).value = client.address.country;
            document.querySelector(REGION_ID + SUFFIX_EDIT_FIELD).value = client.address.region;
            document.querySelector(LOCALITY_ID + SUFFIX_EDIT_FIELD).value = client.address.locality;
            document.querySelector(STREET_ID + SUFFIX_EDIT_FIELD).value = client.address.street;
            document.querySelector(ROOM_ID + SUFFIX_EDIT_FIELD).value = client.address.room;
            document.querySelector(INDEX_ID + SUFFIX_EDIT_FIELD).value = client.address.index;
            openForm(MODAL_EDIT);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

async function fetchEditClient(url = '', data = {}) {
    const response = await fetch(url, {
        method: PUT,
        headers: {
            'Content-Type': CONTENT_TYPE_JSON
        },
        body: JSON.stringify(data)
    });
    return await response.json();
}

function editClient() {
    if (checkValidityForm(SUFFIX_EDIT_FIELD)) {
        fetchEditClient(URL_EDIT, getDataClient(SUFFIX_EDIT_FIELD))
            .then((obj) => {
                console.log(obj);
                document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
                loadClients();
                clearForm(SUFFIX_EDIT_FIELD);
                hiddenForm(MODAL_EDIT);
            })
    }
}