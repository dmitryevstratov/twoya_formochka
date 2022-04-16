//URL
const URL_CREATE = "/orders/create";
const URL_ORDERS = "/orders";
const DATA_ORDERS = "data-orders"
const URL_EDIT = "/orders/edit";

//Client field
const CLIENTS_COUNT = "clients-count";
const CLIENTS_FOUND = "clients-found";
const CLIENT_DISCOUNTS = "client-discoun";

//Load clients on client page

function loadOrders() {

    fetch(URL_ORDERS)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            if (data.length > 0) {
                data.forEach((order => {
                    addOrderInTable(order);
                }))
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

loadOrders();

function addOrderInTable(order) {

}

//Fetch functions

async function fetchDiscountsFromClient(id) {
    return await fetch(URL_CLIENTS + "/" + id)
        .then(resp => resp.json())
}

//Method SEARCH

function searchClientToSelect() {
    let id = document.querySelector(ID_ID + SUFFIX_SEARCH_FIELD);
    let firstName = document.querySelector(FIRST_NAME_ID + SUFFIX_SEARCH_FIELD);
    let lastName = document.querySelector(LAST_NAME_ID + SUFFIX_SEARCH_FIELD);
    let telephone = document.querySelector(TELEPHONE_ID + SUFFIX_SEARCH_FIELD);

    fetch(URL_ORDERS + "/search" + `?id=${id.value}&firstName=${firstName.value}&lastName=${lastName.value}&telephone=${telephone.value}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(CLIENTS_COUNT).innerText = data.length;
            let select = document.getElementById(CLIENTS_FOUND);
            let tmp = EMPTY_VALUE;
            if (data.length > 0) {
                data.forEach((client => {
                    tmp += "<option value=" + client.id + "> №" + client.id + " - " + client.lastName + " " + client.firstName + "</option>";
                }))
                select.innerHTML = tmp;
                select.selectedIndex = -1;
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

//Method fill fields

function fillSelectDiscounts() {
    let selectClients = document.getElementById(CLIENTS_FOUND);
    let selectDiscounts = document.getElementById(CLIENT_DISCOUNTS);
    let id = selectClients.options[selectClients.selectedIndex].value;

    let client = fetchDiscountsFromClient(id);

    let tmp = EMPTY_VALUE;
    let discounts = client.discounts;
    if (discounts != null) {
        discounts.forEach(discount => {
            tmp += "<option value=" + discount.value + ">" + discount.type.name + " - " + discount.value + "</option>";
        })
        selectDiscounts.innerHTML = tmp;
        selectDiscounts.selectedIndex = discounts.length;
    } else {
        console.log("У клиента нет скидок")
    }
}