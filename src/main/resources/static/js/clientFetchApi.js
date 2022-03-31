//URL
const URL_CREATE = "/create/client";
const URL_CLIENTS = "/clients";
const DATA_CLIENTS = "data-clients"

//Method name and headers
const POST = "POST";
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

//EmptyValue
const EMPTY_VALUE = "";

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
        country: document.querySelector(COUNTRY_ID + suffix).value,
        region: document.querySelector(REGION_ID + suffix).value,
        locality: document.querySelector(LOCALITY_ID + suffix).value,
        street: document.querySelector(STREET_ID + suffix).value,
        room: document.querySelector(ROOM_ID + suffix).value,
        index: document.querySelector(INDEX_ID + suffix).value
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
    tmp += "<td><button>Редактировать</button></td>";
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

//Method POST

function createClient() {
    if (checkValidityFormCreateClient()) {
        fetchCreateClient(URL_CREATE, getDataClient(""))
            .then((data) => {
                console.log(data);
                document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
                loadClients(data);
                clearForm(EMPTY_VALUE);
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

function checkValidityFormCreateClient() {
    let firstname = document.querySelector(FIRST_NAME_ID);
    let lastname = document.querySelector(LAST_NAME_ID);
    let birthday = document.querySelector(BIRTHDAY_ID);
    let country = document.querySelector(COUNTRY_ID);
    let region = document.querySelector(REGION_ID);
    let locality = document.querySelector(LOCALITY_ID);
    let street = document.querySelector(STREET_ID);

    if (!firstname.validity.valid) {
        firstname.focus();
        return false;
    }
    if (!lastname.validity.valid) {
        lastname.focus();
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