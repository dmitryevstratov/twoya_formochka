//URL
const URL_CREATE = "/clients/create";
const DATA_CLIENTS = "data-clients"
const URL_EDIT = "/clients/edit";

//Create client field
const SECOND_NAME_ID = "#secondname";
const BIRTHDAY_ID = "#birthday";
const EMAIL_ID = "#email";
const COUNTRY_ID = "#country";
const REGION_ID = "#region";
const LOCALITY_ID = "#locality";
const STREET_ID = "#street";
const ROOM_ID = "#room";
const INDEX_ID = "#index";
const ADD_INFO_ID = "#additionalInfo";

//Suffix
const SUFFIX_EDIT_FIELD = "-edit";
const SUFFIX_DELETE_FIELD = "-delete";

//ID modal window
const MODAL_CREATE = "addClient";
const MODAL_DELETE = "deleteClient";
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
            index: document.querySelector(INDEX_ID + suffix).value,
            additionalInfo: document.querySelector(ADD_INFO_ID + suffix).value
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
            "Квартира: " + checkEmptyField(client.address.room) + "<br>" +
            "Индекс: " + checkEmptyField(client.address.index) + "<br>" +
            "Дополнительная инфо: " + client.address.additionalInfo;
    }

    tmp += "<td>" + client.id + "</td>";
    tmp += "<td>" + client.firstName + "</td>";
    tmp += "<td>" + client.lastName + "</td>";
    tmp += "<td>" + client.secondName + "</td>";
    tmp += "<td>" + client.birthday + "</td>";
    tmp += "<td>" + client.email + "</td>";
    tmp += "<td>" + client.telephone + "</td>";
    tmp += "<td>" + address + "</td>";
    tmp += "<td><button onmousedown= \"fillFormClientById("
        + client.id + ', ' +
        +0 + ', ' +
        +0 +
        ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#editClient\">\n" +
        " Редактировать" +
        "</button></td>";
    tmp += "<td><button onmousedown= \"fillFormClientById("
        + client.id + ', ' +
        +1 + ', ' +
        +1 +
        ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteClient\">\n" +
        " Удалить" +
        "</button></td>";
    return tmp;
}

function addClientInTable(client) {
    let tmp = EMPTY_VALUE;
    tmp += "<tr id =" + "client-" + client.id + ">";
    tmp += contentClient(client);
    tmp += "</tr>";
    document.getElementById(DATA_CLIENTS).innerHTML += tmp;
}

function fillFormClientById(id, numSuffix, numModal) {

    let suffix;
    let modal;

    if (numSuffix === 0 && numModal === 0) {
        suffix = SUFFIX_EDIT_FIELD
        modal = MODAL_EDIT
    } else if (numSuffix === 1 && numModal === 1) {
        suffix = SUFFIX_DELETE_FIELD
        modal = MODAL_DELETE
    }

    fetch(URL_CLIENTS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (client) {
            document.querySelector(ID_ID + suffix).value = id;
            document.querySelector(FIRST_NAME_ID + suffix).value = client.firstName;
            document.querySelector(LAST_NAME_ID + suffix).value = client.lastName;
            document.querySelector(SECOND_NAME_ID + suffix).value = client.secondName;
            document.querySelector(BIRTHDAY_ID + suffix).value = client.birthday;
            document.querySelector(EMAIL_ID + suffix).value = client.email;
            document.querySelector(TELEPHONE_ID + suffix).value = client.telephone;
            document.querySelector(COUNTRY_ID + suffix).value = client.address.country;
            document.querySelector(REGION_ID + suffix).value = client.address.region;
            document.querySelector(LOCALITY_ID + suffix).value = client.address.locality;
            document.querySelector(STREET_ID + suffix).value = client.address.street;
            document.querySelector(ROOM_ID + suffix).value = client.address.room;
            document.querySelector(INDEX_ID + suffix).value = client.address.index;
            document.querySelector(ADD_INFO_ID + suffix).value = client.address.additionalInfo;
            openForm(modal);
        }
    ).catch(function (error) {
        console.log(error);
    });
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

//Fetch functions

async function fetchClient(url = '', data = {}, method) {
    const response = await fetch(url, {
        method: method,
        headers: {
            'Content-Type': CONTENT_TYPE_JSON
        },
        body: JSON.stringify(data)
    });
    return await response.json();
}

function fetchClientThen(data, idForm, modal) {
    console.log(data);
    document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
    loadClients();
    clearForm(idForm);
    hiddenForm(modal);
}

//Method POST

function createClient() {
    if (checkValidityForm(EMPTY_VALUE)) {
        fetchClient(URL_CREATE, getDataClient(""), "POST")
            .then((data) => {
                fetchClientThen(data, "addClientForm", MODAL_CREATE);
            });
    }
}

//Method PUT

function editClient() {
    if (checkValidityForm(SUFFIX_EDIT_FIELD)) {
        fetchClient(URL_EDIT, getDataClient(SUFFIX_EDIT_FIELD), "PUT")
            .then((data) => {
                fetchClientThen(data, null, MODAL_EDIT);
            })
    }
}

//Method DELETE

function deleteClient() {
    let id = document.querySelector(ID_ID + SUFFIX_DELETE_FIELD).value;
    fetch(URL_CLIENTS + "/" + id, {
        method: "DELETE"
    }).then(
        result => {
            console.log(result);
            document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
            loadClients();
            hiddenForm(MODAL_DELETE);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

//Method SEARCH

function searchClient() {
    let id = document.querySelector(ID_ID + SUFFIX_SEARCH_FIELD);
    let firstName = document.querySelector(FIRST_NAME_ID + SUFFIX_SEARCH_FIELD);
    let lastName = document.querySelector(LAST_NAME_ID + SUFFIX_SEARCH_FIELD);
    let secondName = document.querySelector(SECOND_NAME_ID + SUFFIX_SEARCH_FIELD);
    let birthday = document.querySelector(BIRTHDAY_ID + SUFFIX_SEARCH_FIELD);
    let email = document.querySelector(EMAIL_ID + SUFFIX_SEARCH_FIELD);
    let telephone = document.querySelector(TELEPHONE_ID + SUFFIX_SEARCH_FIELD);

    fetch(URL_CLIENTS + "/search" + `?id=${id.value}&firstName=${firstName.value}&lastName=${lastName.value}&secondName=${secondName.value}&birthday=${birthday.value}&email=${email.value}&telephone=${telephone.value}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
            if (data.length > 0) {
                data.forEach((client => {
                    addClientInTable(client);
                }))
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}