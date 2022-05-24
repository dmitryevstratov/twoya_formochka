//URL
const URL_CREATE_CLIENT = "/clients/create";
const DATA_CLIENTS = "data-clients"
const DATA_CLIENTS_DISCOUNTS = "data-clients-with-discounts"
const URL_EDIT_CLIENT = "/clients/edit";
const URL_CLIENTS_DISCOUNTS = "/clients-discounts";

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
const DATA_CLIENT_ALL_DISCOUNTS = "data-client-all-discounts";
const CLIENT_ID = "client-id";

//Discount field
const DISCOUNT_ARTICLE_ID = "discount-article";
const DISCOUNT_NAME_ID = "discount-name";
const DATA_DISCOUNT_ARTICLE = "data-discount-article";
const DATA_DISCOUNT_NAME = "data-discount-name";
const DATA_DISCOUNT_VALUE = "data-discount-value";

//ID modal window
const MODAL_CREATE_CLIENT = "addClient";
const MODAL_DELETE_CLIENT = "deleteClient";
const MODAL_EDIT_CLIENT = "editClient";

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

function loadClientsWithDiscounts() {
    fetch(URL_CLIENTS_DISCOUNTS)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            if (data.length > 0) {
                data.forEach((client => {
                    addClientWithDiscountInTable(client);
                }))
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

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

function addClientWithDiscountInTable(client) {
    let tmp = EMPTY_VALUE;
    tmp += "<tr id =" + "client-" + client.id + ">";
    tmp += "<td>" + client.id + "</td>";
    tmp += "<td>" + client.firstName + "</td>";
    tmp += "<td>" + client.lastName + "</td>";
    tmp += "<td id =" + "discounts-" + client.id + ">" + addDiscountsForClient(client.discounts) + "</td>";
    tmp += "<td>" + "<button onmousedown= \"fillFormClientDiscountById("
        + client.id + ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#editClient\">\n" +
        " Редактировать" +
        "</button>" + "</td>";
    tmp += "</tr>";
    document.getElementById(DATA_CLIENTS_DISCOUNTS).innerHTML += tmp;
}

function parseDiscountName(discountName) {
    let nameTmp = EMPTY_VALUE;
    let name = discountName.split(" ");
    name.forEach(el => {
        nameTmp += el + "_";
    })
    return nameTmp;
}

function fillFormClientDiscountById(id) {
    let table = document.getElementById(DATA_CLIENT_ALL_DISCOUNTS);
    let tmp = EMPTY_VALUE;

    fetch(URL_CLIENTS + "/" + id).then(
        result => result.json()
    ).then(
        function (client) {
            console.log(client);
            document.getElementById(DATA_CLIENT_ALL_DISCOUNTS).innerHTML = EMPTY_VALUE;
            document.getElementById(CLIENT_ID).value = id;
            let discounts = client.discounts;
            if (discounts != null) {
                let i;
                for (i = 1; i <= discounts.length; i++) {
                    tmp += "<tr data-discount-article=" + i + "><td>" + i + "</td>";
                    tmp += "<td data-discount-name=" + parseDiscountName(discounts[i - 1].type.name) + ">" + discounts[i - 1].type.name + "</td>";
                    tmp += "<td data-discount-value=" + discounts[i - 1].value + ">" + discounts[i - 1].value + "</td>";
                    tmp += "<td>" + "<button onmousedown= \"deleteDiscountFromClientById("
                        + i + ")\" type=\"button\">\n" +
                        " Удалить" +
                        "</button>" + "</td>";
                }
                table.innerHTML = tmp;
            }
        }
    ).catch(function (error) {
        console.log(error);
    });

}

function deleteDiscountFromClientById(id) {
    let tmp = EMPTY_VALUE;
    let table = document.getElementById(DATA_CLIENT_ALL_DISCOUNTS);
    let discountsForDelete = document.querySelectorAll("[" + DATA_DISCOUNT_ARTICLE + "]");

    discountsForDelete.forEach(discount => {
        let attribute = discount.getAttribute(DATA_DISCOUNT_ARTICLE);
        if (attribute != id) {
            tmp += "<tr data-discount-article=" + attribute + ">" + discount.innerHTML;
        }
    })
    table.innerHTML = tmp;
}

function addDiscountsForClient(discounts) {
    let tmp = EMPTY_VALUE;
    if (discounts.length > 0) {
        discounts.forEach(discount => {
            tmp += discount.type.name + " - " + discount.value + "<br>";
        });
    }
    return tmp;
}

function fillFormClientById(id, numSuffix, numModal) {

    let suffix;
    let modal;

    if (numSuffix === 0 && numModal === 0) {
        suffix = SUFFIX_EDIT_FIELD
        modal = MODAL_EDIT_CLIENT
    } else if (numSuffix === 1 && numModal === 1) {
        suffix = SUFFIX_DELETE_FIELD
        modal = MODAL_DELETE_CLIENT
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

function fetchClientThen(data, idForm, modal) {
    console.log(data);
    document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
    loadClients();
    hiddenForm(modal);
}

function fetchClientDiscountsThen(data, modal) {
    console.log(data);
    document.getElementById(DATA_CLIENTS_DISCOUNTS).innerHTML = EMPTY_VALUE;
    loadClientsWithDiscounts();
    hiddenForm(modal);
}

//Method POST

function createClient() {
    if (checkValidityForm(EMPTY_VALUE)) {
        fetchSendData(URL_CREATE_CLIENT, getDataClient(""), POST)
            .then((data) => {
                fetchClientThen(data, "addClientForm", MODAL_CREATE_CLIENT);
            });
    }
}

//Method PUT

function editClient() {
    if (checkValidityForm(SUFFIX_EDIT_FIELD)) {
        fetchSendData(URL_EDIT_CLIENT, getDataClient(SUFFIX_EDIT_FIELD), PUT)
            .then((data) => {
                fetchClientThen(data, null, MODAL_EDIT_CLIENT);
            })
    }
}

//Method DELETE

function deleteClient() {
    let id = document.querySelector(ID_ID + SUFFIX_DELETE_FIELD).value;
    fetch(URL_CLIENTS + "/" + id, {
        method: DELETE
    }).then(
        result => {
            console.log(result);
            document.getElementById(DATA_CLIENTS).innerHTML = EMPTY_VALUE;
            loadClients();
            hiddenForm(MODAL_DELETE_CLIENT);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

//Method SEARCH

function searchClient() {
    let id = document.querySelector(ID_ID + SUFFIX_SEARCH_FIELD).value;
    let firstName = capitalizeFirstLetter(document.querySelector(FIRST_NAME_ID + SUFFIX_SEARCH_FIELD).value.toLowerCase());
    let lastName = capitalizeFirstLetter(document.querySelector(LAST_NAME_ID + SUFFIX_SEARCH_FIELD).value.toLowerCase());
    let secondName = capitalizeFirstLetter(document.querySelector(SECOND_NAME_ID + SUFFIX_SEARCH_FIELD).value.toLowerCase());
    let birthday = document.querySelector(BIRTHDAY_ID + SUFFIX_SEARCH_FIELD).value;
    let email = document.querySelector(EMAIL_ID + SUFFIX_SEARCH_FIELD).value;
    let telephone = document.querySelector(TELEPHONE_ID + SUFFIX_SEARCH_FIELD).value;

    fetch(URL_CLIENTS + "/search" + `?id=${id}&firstName=${firstName}&lastName=${lastName}&secondName=${secondName}&birthday=${birthday}&email=${email}&telephone=${telephone}`)
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

function searchClientWithDiscount() {
    let id = document.querySelector(ID_ID + SUFFIX_SEARCH_FIELD).value;
    let firstName = document.querySelector(FIRST_NAME_ID + SUFFIX_SEARCH_FIELD).value;
    let lastName = document.querySelector(LAST_NAME_ID + SUFFIX_SEARCH_FIELD).value;
    let discountName = document.getElementById(DISCOUNT + SUFFIX_SEARCH_FIELD).value;

    fetch(URL_CLIENTS_DISCOUNTS + "/search" + `?id=${id}&firstName=${firstName}&lastName=${lastName}&discountName=${discountName}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(DATA_CLIENTS_DISCOUNTS).innerHTML = EMPTY_VALUE;
            if (data.length > 0) {
                data.forEach((client => {
                    addClientWithDiscountInTable(client);
                }))
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

function searchClientDiscountToSelect() {
    let id = document.getElementById(DISCOUNT_ARTICLE_ID + SUFFIX_SEARCH_FIELD).value;
    let type = document.getElementById(DISCOUNT_NAME_ID + SUFFIX_SEARCH_FIELD).value;

    fetch(URL_DISCOUNTS + "/search" + `?id=${id}&type=${type}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(DISCOUNT_COUNT + SUFFIX_SEARCH_FIELD).innerText = data.length;
            let select = document.getElementById(DISCOUNT_FOUND + SUFFIX_SEARCH_FIELD);
            let tmp = EMPTY_VALUE;
            if (data.length > 0) {
                tmp += "<option value='-1'>" + "Нет" + "</option>";
                data.forEach((discount => {
                    tmp += "<option value=" + discount.id + ">" + discount.id + "-" + discount.type.name + "-" + discount.value + "</option>";
                }))
                select.innerHTML = tmp;
                select.selectedIndex = 0;
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

function fillSelectClientDiscounts() {
    let tmp = EMPTY_VALUE;
    let table = document.getElementById(DATA_CLIENT_ALL_DISCOUNTS);
    let selectDiscount = document.getElementById(DISCOUNT_FOUND + SUFFIX_SEARCH_FIELD);
    let discount = selectDiscount.options[selectDiscount.selectedIndex].innerHTML;

    let discountParse = discount.split("-");

    tmp += "<tr data-discount-article=" + discountParse[0] + "><td>" + discountParse[0] + "</td>";
    tmp += "<td data-discount-name=" + parseDiscountName(discountParse[1]) + ">" + discountParse[1] + "</td>";
    tmp += "<td data-discount-value=" + discountParse[2] + ">" + discountParse[2] + "</td>";
    tmp += "<td>" + "<button onmousedown= \"deleteDiscountFromClientById("
        + discountParse[0] + ")\" type=\"button\">\n" +
        " Удалить" +
        "</button>" + "</td>";

    table.innerHTML += tmp;
}

function editClientDiscount() {
    let id = document.getElementById(CLIENT_ID).value;
    let discountsForDelete = document.querySelectorAll("[" + DATA_DISCOUNT_ARTICLE + "]");
    let data = {};
    let discounts = [];

    discountsForDelete.forEach(discount => {
        let name = discount.querySelector("[" + DATA_DISCOUNT_NAME + "]").innerHTML;
        let value = discount.querySelector("[" + DATA_DISCOUNT_VALUE + "]").innerHTML;
        let type = new DiscountType(name);
        let disc = new Discount(type, value);
        discounts.push(disc);
    })

    fetch(URL_CLIENTS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (rs) {
            data = {
                id: rs.id,
                discounts: discounts
            };
            console.log(data);
            fetchSendData(URL_CLIENTS_DISCOUNTS + "/edit", data, PUT)
                .then((data) => {
                    fetchClientDiscountsThen(data, MODAL_EDIT_CLIENT);
                })
        }
    ).catch(function (error) {
        console.log(error);
    });
}