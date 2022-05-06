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
const ORDER = "order";
const ORDER_ID = "order-id";
const ORDER_COUNT = "order-count";
const ORDER_TOTAL_PRICE = "order-total-price";
const ARTICLE_ORDER = "article-order";

//ID modal window
const MODAL_CREATE = "addOrder";
const MODAL_DELETE = "deleteOrder";
const MODAL_EDIT = "editOrder";

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
    let table = document.getElementById(DATA_ORDERS);
    let tmp = EMPTY_VALUE;
    let discount = (order.discount != null) ? order.discount.type.name + " - " + order.discount.value + "%" : "";

    tmp += "<td id=" + ORDER_ID + ">" + order.id + "</td>";
    tmp += "<td>" + order.client.firstName + "</td>";
    tmp += "<td>" + order.client.lastName + "</td>";
    tmp += "<td>" + order.dateCreate + "</td>";
    tmp += "<td>" + order.dateClosed + "</td>";
    tmp += "<td>" + discount + "</td>";
    tmp += "<td id=" + ORDER_TOTAL_PRICE + ">" + order.totalPrice + "</td>";
    tmp += "<td id=" + ORDER_COUNT + ">" + order.countItems + "</td>";
    tmp += "<td>" + order.status + "</td>";
    tmp += "<td><button onmousedown= \"fillFormOrderById("
        + order.id + ', ' +
        +0 + ', ' +
        +0 +
        ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#editOrder\">\n" +
        " Редактировать" +
        "</button></td>";
    tmp += "<td><button onmousedown= \"fillFormOrderById("
        + order.id + ', ' +
        +1 + ', ' +
        +1 +
        ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteOrder\">\n" +
        " Удалить" +
        "</button></td>";

    table.innerHTML += tmp;
}

//Fetch functions

//Method SEARCH

function searchClientToSelect(suffix) {
    let id = document.querySelector(ID_ID + SUFFIX_SEARCH_FIELD + suffix);
    let firstName = document.querySelector(FIRST_NAME_ID + SUFFIX_SEARCH_FIELD + suffix);
    let lastName = document.querySelector(LAST_NAME_ID + SUFFIX_SEARCH_FIELD + suffix);
    let telephone = document.querySelector(TELEPHONE_ID + SUFFIX_SEARCH_FIELD + suffix);

    fetch(URL_CLIENTS + "/search" + `?id=${id.value}&firstName=${firstName.value}&lastName=${lastName.value}&telephone=${telephone.value}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(CLIENTS_COUNT + suffix).innerText = data.length;
            let select = document.getElementById(CLIENTS_FOUND + suffix);
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

function searchOrder() {
    let id = document.getElementById("id-" + ORDER + SUFFIX_SEARCH_FIELD).value;
    let firstName = capitalizeFirstLetter(document.getElementById("firstname-" + ORDER + SUFFIX_SEARCH_FIELD).value.toLowerCase());
    let lastName = capitalizeFirstLetter(document.getElementById("lastname-" + ORDER + SUFFIX_SEARCH_FIELD).value.toLowerCase());
    let dateCreate = document.getElementById("dateCreate-" + ORDER + SUFFIX_SEARCH_FIELD).value;
    let dateClosed = document.getElementById("dateClosed-" + ORDER + SUFFIX_SEARCH_FIELD).value;
    let status = document.getElementById("status-" + ORDER + SUFFIX_SEARCH_FIELD);
    let selectedStatus = status.options[status.selectedIndex].value;
    let priceMin = document.getElementById("price-min-" + ORDER + SUFFIX_SEARCH_FIELD).value;
    let priceMax = document.getElementById("price-max-" + ORDER + SUFFIX_SEARCH_FIELD).value;
    let count = document.getElementById("count-" + ORDER + SUFFIX_SEARCH_FIELD).value;

    fetch(URL_ORDERS + "/search" + `?id=${id}&firstName=${firstName}&lastName=${lastName}&dateCreate=${dateCreate}&dateClosed=${dateClosed}&selectedStatus=${selectedStatus}&priceMin=${priceMin}&priceMax=${priceMax}&count=${count}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(DATA_ORDERS).innerHTML = EMPTY_VALUE;
            if (data.length > 0) {
                data.forEach((client => {
                    addOrderInTable(client);
                }))
            }
        })
        .catch(function (e) {
            console.log(e);
        })


}

//Method fill fields

function fillSelectDiscounts(suffix) {
    let tmp = EMPTY_VALUE;
    let selectDiscounts = document.getElementById(CLIENT_DISCOUNTS + suffix);
    let selectClients = document.getElementById(CLIENTS_FOUND + suffix);
    let id = selectClients.options[selectClients.selectedIndex].value;

    if (id != -1) {
        fetch(URL_CLIENTS + "/" + id)
            .then(resp => resp.json()).then(
            function (client) {
                console.log(client)
                let discounts = client.discounts;
                if (discounts != null) {
                    tmp += "<option value='-1'>" + "Нет" + "</option>";
                    document.getElementById(CLIENT_DISCOUNTS_COUNT + suffix).innerText = discounts.length;
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

function deleteItemFromTable(id, suffix) {
    let tmp = EMPTY_VALUE;
    let clientItems = document.getElementById(CLIENT_ITEMS + suffix);
    clientItems.querySelectorAll(TAG_TR).forEach(el => {
        if (el.id !== ITEM + id) {
            tmp += "<tr id=" + el.id + ">" + el.innerHTML + "</tr>";
        }
        clientItems.innerHTML = tmp;
    })

    setTotalForItems(suffix);
}

function incrementItem(id, price, suffix) {
    let countEl = document.getElementById(ITEM_COUNT + id);
    let priceEl = document.getElementById(ITEM_PRICE + id);
    let count = Number(countEl.innerHTML);

    if (count >= 99) {
        countEl.innerHTML = 99;
    } else {
        countEl.innerHTML = ++count;
    }
    priceEl.innerHTML = setPrice(price, count);
    setTotalForItems(suffix);
}

function decrementItem(id, price, suffix) {
    let countEl = document.getElementById(ITEM_COUNT + id);
    let priceEl = document.getElementById(ITEM_PRICE + id);
    let count = Number(countEl.innerHTML);

    if (count <= 1) {
        countEl.innerHTML = 1;
    } else {
        countEl.innerHTML = --count;
    }
    priceEl.innerHTML = setPrice(price, count);
    setTotalForItems(suffix);
}

function setPrice(price, count) {
    return (Number(price) * Number(count)).toFixed(1);
}

function setTotalForItems(suffix) {
    let discount;
    let clientItems;
    let totalCountEl;
    let totalPriceEl;

    if (suffix != "null" && suffix != null) {
        discount = document.getElementById(CLIENT_DISCOUNTS + suffix);
        clientItems = document.getElementById(CLIENT_ITEMS + suffix);
        totalCountEl = document.getElementById(ITEMS_TOTAL + "count" + suffix);
        totalPriceEl = document.getElementById(ITEMS_TOTAL + "price" + suffix);
    } else {
        discount = document.getElementById(CLIENT_DISCOUNTS);
        clientItems = document.getElementById(CLIENT_ITEMS);
        totalCountEl = document.getElementById(ITEMS_TOTAL + "count");
        totalPriceEl = document.getElementById(ITEMS_TOTAL + "price");
    }

    let discountSelected = discount.options[discount.selectedIndex];
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

function fillFieldsForOrder(suffix) {
    let tmp = EMPTY_VALUE;

    let clientId = document.querySelector(CLIENT_ID_ORDER + suffix);
    let discountId = document.querySelector(DISCOUNT_ID_ORDER + suffix);
    let itemsIdCount = document.querySelector(ITEMS_ID_COUNT_ORDER + suffix);
    let totalPrice = document.querySelector(TOTAL_PRICE_ORDER + suffix);


    let clientSelected = document.getElementById(CLIENTS_FOUND + suffix);
    let discountSelected = document.getElementById(CLIENT_DISCOUNTS + suffix);

    let clientSelectedIndex = clientSelected.options[clientSelected.selectedIndex];
    let discountSelectedIndex = discountSelected.options[discountSelected.selectedIndex];
    let totalPriceSelected = document.getElementById(ITEMS_TOTAL + "price" + suffix).innerHTML;

    getAllItems(suffix).forEach(item => {
        if (item !== undefined) {
            tmp += item.returnString();
        }
    })

    clientId.value = (clientSelectedIndex === undefined || clientSelectedIndex.value == -1) ? "" : clientSelectedIndex.value;
    discountId.value = (discountSelectedIndex === undefined || discountSelectedIndex.value == -1) ? "" : discountSelectedIndex.value;
    itemsIdCount.value = tmp;
    totalPrice.value = totalPriceSelected;
}

function getAllItems(suffix) {
    let itemsRow = document.getElementById(CLIENT_ITEMS + suffix).querySelectorAll(TAG_TR);
    let itemsArray = new Array(itemsRow.length);

    itemsRow.forEach(item => {
        let id = item.querySelector("." + ITEM_ARTICLE_CL).innerHTML;
        let count = item.querySelector("." + ITEM_COUNT_CL).innerHTML;
        let i = new Item(id, count);
        itemsArray.push(i);
    })

    return itemsArray;
}

function checkValidityOrderForm(suffix) {
    let clientId = document.querySelector(CLIENT_ID_ORDER + suffix);
    let itemsIdCount = document.querySelector(ITEMS_ID_COUNT_ORDER + suffix);
    let totalPrice = document.querySelector(TOTAL_PRICE_ORDER + suffix);
    let descriptionForResultHtml = document.getElementById(ORDER_RESULT + suffix);
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
    fillFieldsForOrder('')
    if (checkValidityOrderForm('')) {
        fetchSendData(URL_CREATE, getDataOrder(''), POST)
            .then((data) => {
                fetchOrderThen(data, MODAL_CREATE)
            });
    }
}

//Fetch functions

function fetchOrderThen(data, modal) {
    console.log(data);
    loadOrders();
    document.getElementById(DATA_ORDERS).innerHTML = EMPTY_VALUE;
    hiddenForm(modal);
}

//Get data

function getDataOrder(suffix) {
    let clientId = document.querySelector(CLIENT_ID_ORDER + suffix).value;
    let discountId = document.querySelector(DISCOUNT_ID_ORDER + suffix).value;
    let totalPrice = document.querySelector(TOTAL_PRICE_ORDER + suffix).value;
    let article = document.getElementById(ARTICLE_ORDER + suffix);

    if(article != null){
        article = article.value;
    }

    return {
        idOrder: article,
        idClient: clientId,
        items: getAllItems(suffix),
        idDiscount: discountId,
        price: totalPrice
    }
}

//Form

function clearOrderForm(suffix) {
    let formClient = document.getElementById("form-choose-client-for-order" + suffix);
    let formItem = document.getElementById("form-choose-item-for-order" + suffix);
    let formOrder = document.getElementById("form-send-order" + suffix);
    let orderResult = document.getElementById(ORDER_RESULT + suffix);

    let table = document.getElementById(CLIENT_ITEMS + suffix);
    let totalCount = document.getElementById(ITEMS_TOTAL + "count" + suffix);
    let totalPrice = document.getElementById(ITEMS_TOTAL + "price" + suffix);

    let clientsCount = document.getElementById(CLIENTS_COUNT + suffix);
    let discountCount = document.getElementById(CLIENT_DISCOUNTS_COUNT + suffix);
    let itemsCount = document.getElementById(ITEMS_COUNT + suffix);

    let selectClient = document.getElementById(CLIENTS_FOUND + suffix);
    let selectDiscount = document.getElementById(CLIENT_DISCOUNTS + suffix);
    let selectItem = document.getElementById(ITEMS_FOUND + suffix);

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
    orderResult.innerText = EMPTY_VALUE;
    totalCount.innerHTML = 0;
    totalPrice.innerHTML = 0;

    selectClient.selectedIndex = 0;
    selectDiscount.selectedIndex = 0;
    selectItem.selectedIndex = 0;

    selectClient.innerHTML = "<option value='-1'>" + "Нет" + "</option>";
    selectDiscount.innerHTML = "<option value='-1'>" + "Нет" + "</option>";
    selectItem.innerHTML = "<option value='-1'>" + "Нет" + "</option>";

    clientsCount.innerHTML = 0;
    discountCount.innerHTML = 0;
    itemsCount.innerHTML = 0;

}

//Sorted

function sortOrderBy(direction, field) {
    let ordersRow = document.getElementById(DATA_ORDERS).querySelectorAll(TAG_TR);
    let ordersArray = new Array(ordersRow.length);
    let tmp = EMPTY_VALUE;

    ordersRow.forEach(tr => ordersArray.push(tr));

    ordersArray.sort(function (a, b) {
        let firstEl;
        let secondEl;

        try {
            firstEl = Number(a.querySelector("#" + field).innerHTML);
            secondEl = Number(b.querySelector("#" + field).innerHTML);
        } catch (e) {
            console.log(e);
        }

        if (direction == 1) {
            if (firstEl > secondEl) {
                return 1;
            }
            if (firstEl < secondEl) {
                return -1;
            }
            return 0;
        }

        if (direction == 2) {
            if (firstEl > secondEl) {
                return -1;
            }
            if (firstEl < secondEl) {
                return 1;
            }
            return 0;
        }
    })

    ordersArray.forEach(tr => {
        if (tr != undefined && tr != null) {
            tmp += tr.innerHTML + "</tr>";
        }
    })

    document.getElementById(DATA_ORDERS).innerHTML = tmp;

}

//Method PUT

function editOrder() {
    fillFieldsForOrder(SUFFIX_EDIT_FIELD);
    if (checkValidityOrderForm(SUFFIX_EDIT_FIELD)) {
        fetchSendData(URL_EDIT, getDataOrder(SUFFIX_EDIT_FIELD), PUT)
            .then((data) => {
                fetchOrderThen(data, MODAL_EDIT)
            });
    }
}

function fillFormOrderById(id, numSuffix, numModal) {
    let suffix;
    let modal;

    if (numSuffix === 0 && numModal === 0) {
        suffix = SUFFIX_EDIT_FIELD
        modal = MODAL_EDIT
    } else if (numSuffix === 1 && numModal === 1) {
        suffix = SUFFIX_DELETE_FIELD
        modal = MODAL_DELETE
    }

    fetch(URL_ORDERS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (order) {
            console.log(order);
            let selectClients = document.getElementById(CLIENTS_FOUND + suffix);
            let selectDiscount = document.getElementById(CLIENT_DISCOUNTS + suffix);
            let tableItems = document.getElementById(CLIENT_ITEMS + suffix);
            let tmp = tableItems.innerHTML;
            document.getElementById(ARTICLE_ORDER + suffix).value = id;

            let client = order.clientPl;
            let discount = order.discountPl;
            let items = order.itemPlList;

            selectClients.options[0].value = client.id;
            selectClients.options[0].innerHTML = "№" + client.id + " - " + client.lastName + " " + client.firstName;

            if (discount != null) {
                selectDiscount.options[0].value = discount.id;
                selectDiscount.options[0].innerHTML = discount.type.name + " - " + discount.value;
            }

            items.forEach(item => {
                addItemToTableUpdate(item, tableItems, tmp, suffix);
            });

            openForm(modal);
        }
    ).catch(function (error) {
        console.log(error);
    });
}