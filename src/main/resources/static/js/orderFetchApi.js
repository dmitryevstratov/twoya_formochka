//URL
const URL_CREATE = "/orders/create";
const URL_ORDERS = "/orders";
const DATA_ORDERS = "data-orders"
const URL_EDIT = "/orders/edit";

//Client field
const CLIENTS_COUNT = "clients-count";
const CLIENTS_FOUND = "clients-found";
const CLIENT_DISCOUNTS_COUNT = "clients-discounts-count";

//Item field
const ITEMS_TOTAL = "items-total-";

//Order field
const CLIENT_ID_ORDER = "#client-id-order";
const ITEMS_ID_COUNT_ORDER = "#items-id-count-order";
const DISCOUNT_ID_ORDER = "#discount-id-order";
const TOTAL_PRICE_ORDER = "#total-price-order";
const ORDER_RESULT = "order-result";

//ID modal window
const MODAL_CREATE = "addOrder";

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

//Method SEARCH

function searchClientToSelect() {
    let id = document.querySelector(ID_ID + SUFFIX_SEARCH_FIELD);
    let firstName = document.querySelector(FIRST_NAME_ID + SUFFIX_SEARCH_FIELD);
    let lastName = document.querySelector(LAST_NAME_ID + SUFFIX_SEARCH_FIELD);
    let telephone = document.querySelector(TELEPHONE_ID + SUFFIX_SEARCH_FIELD);

    fetch(URL_CLIENTS + "/search" + `?id=${id.value}&firstName=${firstName.value}&lastName=${lastName.value}&telephone=${telephone.value}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(CLIENTS_COUNT).innerText = data.length;
            let select = document.getElementById(CLIENTS_FOUND);
            let tmp = EMPTY_VALUE;
            if (data.length > 0) {
                tmp += "<option value='-1'>" + "Нет" + "</option>";
                data.forEach((client => {
                    tmp += "<option value=" + client.id + "> №" + client.id + " - " + client.lastName + " " + client.firstName + "</option>";
                }))
                select.innerHTML = tmp;
                select.selectedIndex = 0;
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

//Method fill fields

function fillSelectDiscounts() {
    let tmp = EMPTY_VALUE;
    let selectDiscounts = document.getElementById(CLIENT_DISCOUNTS);
    let selectClients = document.getElementById(CLIENTS_FOUND);
    let id = selectClients.options[selectClients.selectedIndex].value;

    if (id != -1) {
        fetch(URL_CLIENTS + "/" + id)
            .then(resp => resp.json()).then(
            function (client) {
                console.log(client)
                let discounts = client.discounts;
                if (discounts != null) {
                    tmp += "<option value='-1'>" + "Нет" + "</option>";
                    document.getElementById(CLIENT_DISCOUNTS_COUNT).innerText = discounts.length;
                    discounts.forEach(discount => {
                        tmp += "<option value=" + discount.id + ">" + discount.type.name + " - " + discount.value + "</option>";
                    })
                    selectDiscounts.selectedIndex = 0;
                } else {
                    console.log("У клиента нет скидок")
                }
                selectDiscounts.innerHTML = tmp;
            }
        )
    }
}

function deleteItemFromTable(id) {
    let tmp = EMPTY_VALUE;
    let clientItems = document.getElementById(CLIENT_ITEMS);

    clientItems.querySelectorAll(TAG_TR).forEach(el => {
        if (el.id !== ITEM + id) {
            tmp += "<tr id=" + el.id + ">" + el.innerHTML + "</tr>";
        }
        clientItems.innerHTML = tmp;
    })

    setTotalForItems();
}

function incrementItem(id, price) {
    let countEl = document.getElementById(ITEM_COUNT + id);
    let priceEl = document.getElementById(ITEM_PRICE + id);
    let count = Number(countEl.innerHTML);

    if (count >= 99) {
        countEl.innerHTML = 99;
    } else {
        countEl.innerHTML = ++count;
    }
    priceEl.innerHTML = setPrice(price, count);
    setTotalForItems();
}

function decrementItem(id, price) {
    let countEl = document.getElementById(ITEM_COUNT + id);
    let priceEl = document.getElementById(ITEM_PRICE + id);
    let count = Number(countEl.innerHTML);

    if (count <= 1) {
        countEl.innerHTML = 1;
    } else {
        countEl.innerHTML = --count;
    }
    priceEl.innerHTML = setPrice(price, count);
    setTotalForItems();
}

function setPrice(price, count) {
    return (Number(price) * Number(count)).toFixed(1);
}

function setTotalForItems() {
    let discount = document.getElementById(CLIENT_DISCOUNTS);
    let discountSelected = discount.options[discount.selectedIndex];
    let clientItems = document.getElementById(CLIENT_ITEMS);
    let totalCountEl = document.getElementById(ITEMS_TOTAL + "count");
    let totalPriceEl = document.getElementById(ITEMS_TOTAL + "price");
    let count = 0;
    let price = 0;

    clientItems.querySelectorAll(TAG_TR).forEach(row => {
        row.querySelectorAll("." + ITEM_COUNT_CL).forEach(spanCount => {
            count += Number(spanCount.innerHTML);
        })
        row.querySelectorAll("." + ITEM_PRICE_CL).forEach(tdPrice => {
            price += Number(tdPrice.innerHTML);
        })
    })

    totalCountEl.innerHTML = count;
    if (discountSelected !== undefined) {
        totalPriceEl.innerHTML = (parseDiscount(price, discountSelected)).toFixed(1);
    } else {
        totalPriceEl.innerHTML = (price).toFixed(1);
    }

}

function parseDiscount(price, discountHtml) {
    if (discountHtml.value == -1) {
        return price;
    } else {
        let discount = discountHtml.innerHTML.split("-")[1].replace(" ", "");
        return (100 - discount) / 100 * Number(price);
    }
}

function fillFieldsForOrder() {
    let tmp = EMPTY_VALUE;

    let clientId = document.querySelector(CLIENT_ID_ORDER);
    let discountId = document.querySelector(DISCOUNT_ID_ORDER);
    let itemsIdCount = document.querySelector(ITEMS_ID_COUNT_ORDER);
    let totalPrice = document.querySelector(TOTAL_PRICE_ORDER);


    let clientSelected = document.getElementById(CLIENTS_FOUND);
    let discountSelected = document.getElementById(CLIENT_DISCOUNTS);

    let clientSelectedIndex = clientSelected.options[clientSelected.selectedIndex];
    let discountSelectedIndex = discountSelected.options[discountSelected.selectedIndex];
    let totalPriceSelected = document.getElementById(ITEMS_TOTAL + "price").innerHTML;

    getAllItems().forEach(item => {
        if (item !== undefined) {
            tmp += item.returnString();
        }
    })

    clientId.value = (clientSelectedIndex === undefined || clientSelectedIndex.value == -1) ? "" : clientSelectedIndex.value;
    discountId.value = (discountSelectedIndex === undefined || discountSelectedIndex.value == -1) ? "" : discountSelectedIndex.value;
    itemsIdCount.value = tmp;
    totalPrice.value = totalPriceSelected;
}

function getAllItems() {
    let itemsRow = document.getElementById(CLIENT_ITEMS).querySelectorAll(TAG_TR);
    let itemsArray = new Array(itemsRow.length);

    itemsRow.forEach(item => {
        let id = item.querySelector("." + ITEM_ARTICLE_CL).innerHTML;
        let count = item.querySelector("." + ITEM_COUNT_CL).innerHTML;
        let i = new Item(id, count);
        itemsArray.push(i);
    })

    return itemsArray;
}

function checkValidityOrderForm() {
    let clientId = document.querySelector(CLIENT_ID_ORDER);
    let itemsIdCount = document.querySelector(ITEMS_ID_COUNT_ORDER);
    let totalPrice = document.querySelector(TOTAL_PRICE_ORDER);
    let descriptionForResultHtml = document.getElementById(ORDER_RESULT);
    let alertContainer = document.createElement('div');
    let alertContent = EMPTY_VALUE;
    let success = false;

    descriptionForResultHtml.innerHTML = EMPTY_VALUE;

    if (clientId.value === "" || clientId.value === "-1") {
        alertContent += "<p>Выберите клиента!</p>";
    } else if (itemsIdCount.value === "" || totalPrice.value === "0") {
        alertContent += "<p>Выберите минимум 1 товар!</p>";
    } else {
        alertContent += "<p>Заказ готов!</p>";
        success = true;
    }

    alertContainer.innerHTML = alertContent;
    descriptionForResultHtml.append(alertContainer);

    return success;
}

//Method POST

function createOrder() {
    fillFieldsForOrder()
    if (checkValidityOrderForm()) {
        fetchSendData(URL_CREATE, getDataOrder(), POST)
            .then((data) => {
                fetchOrderThen(data, MODAL_CREATE)
            });
    }
}

//Fetch functions

function fetchOrderThen(data, modal) {
    console.log(data);
    clearOrderForm();
    hiddenForm(modal);
}

//Classes

class Item {
    constructor(id, count) {
        this.id = id;
        this.count = count;
    }

    returnString() {
        return this.id + ":" + this.count + ";";
    }
}

//Get data

function getDataOrder() {
    let clientId = document.querySelector(CLIENT_ID_ORDER).value;
    let discountId = document.querySelector(DISCOUNT_ID_ORDER).value;
    let totalPrice = document.querySelector(TOTAL_PRICE_ORDER).value;

    return {
        idClient: clientId,
        items: getAllItems(),
        idDiscount: discountId,
        price: totalPrice
    }
}

//Form

function clearOrderForm() {
    let formClient = document.getElementById("form-choose-client-for-order");
    let formItem = document.getElementById("form-choose-item-for-order");
    let formOrder = document.getElementById("form-send-order");

    let table = document.getElementById(CLIENT_ITEMS);
    let totalCount = document.getElementById(ITEMS_TOTAL + "count");
    let totalPrice = document.getElementById(ITEMS_TOTAL + "price");

    let clientsCount = document.getElementById(CLIENTS_COUNT);
    let discountCount = document.getElementById(CLIENT_DISCOUNTS_COUNT);
    let itemsCount = document.getElementById(ITEMS_COUNT);

    let selectClient = document.getElementById(CLIENTS_FOUND);
    let selectDiscount = document.getElementById(CLIENT_DISCOUNTS);
    let selectItem = document.getElementById(ITEMS_FOUND);

    formClient.querySelectorAll(TAG_INPUT).forEach(input => {
        input.value = EMPTY_VALUE;
    })
    formItem.querySelectorAll(TAG_INPUT).forEach(input => {
        input.value = EMPTY_VALUE;
    })
    formOrder.querySelectorAll(TAG_INPUT).forEach(input => {
        input.value = EMPTY_VALUE;
    })

    table.innerHTML = EMPTY_VALUE;
    totalCount.innerHTML = 0;
    totalPrice.innerHTML = 0;

    selectClient.selectedIndex = 0;
    selectDiscount.selectedIndex = 0;
    selectItem.selectedIndex = 0;

    selectClient.innerHTML = EMPTY_VALUE;
    selectDiscount.innerHTML = EMPTY_VALUE;
    selectItem.innerHTML = EMPTY_VALUE;

    clientsCount.innerHTML = 0;
    discountCount.innerHTML = 0;
    itemsCount.innerHTML = 0;

}