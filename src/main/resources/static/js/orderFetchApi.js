//URL
const URL_CREATE = "/orders/create";
const URL_ORDERS = "/orders";
const URL_ORDERS_STATUS = "/orders-status";
const DATA_ORDERS = "data-orders"
const URL_EDIT = "/orders/edit";
const URL_EDIT_STATUS = "/orders-status/edit";

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
const DATE_START_ORDER = "dateStart-order";
const DATE_END_ORDER = "dateEnd-order";

//ID modal window
const MODAL_CREATE = "addOrder";
const MODAL_DELETE = "deleteOrder";
const MODAL_EDIT = "editOrder";

//Load clients on client page

function loadOrders(url) {

    fetch(url)
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

function loadOrdersStatus(url) {

    fetch(url)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            if (data.length > 0) {
                data.forEach((order => {
                    addOrderStatusInTable(order);
                }))
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

function addOrderInTable(order) {
    let {table, tmp} = addOrderRow(order);
    tmp += "<td>" + order.status + "</td>";
    tmp += "<td><button onmousedown= \"fillFormUpdateOrderById("
        + order.id +
        ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#editOrder\">\n" +
        " Редактировать" +
        "</button></td>";
    tmp += "<td><button onmousedown= \"fillFormDeleteOrderById("
        + order.id +
        ")\" type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteOrder\">\n" +
        " Удалить" +
        "</button></td>";

    table.innerHTML += tmp;
}

function addOrderRow(order) {
    let table = document.getElementById(DATA_ORDERS);
    let tmp = EMPTY_VALUE;
    let discount = (order.discount != null) ? order.discount.type.name + " - " + order.discount.value + "%" : "";

    tmp += "<td id=" + ORDER_ID + ">" + order.id + "</td>";
    tmp += "<td>" + order.client.firstName + "</td>";
    tmp += "<td>" + order.client.lastName + "</td>";
    tmp += "<td>" + order.dateCreate + "</td>";
    tmp += "<td>" + discount + "</td>";
    tmp += "<td id=" + ORDER_TOTAL_PRICE + ">" + order.totalPrice + "</td>";
    tmp += "<td id=" + ORDER_COUNT + ">" + order.countItems + "</td>";
    return {table, tmp};
}

function addOrderStatusInTable(order) {
    let {table, tmp} = addOrderRow(order);
    let id = order.id;
    let status = order.status;
    let selectStatus = "<select id='edit-order-status-" + id + "' onchange='editOrderStatus(" + id + ")'>" +
        "<option value=" + status + " >" + status + "</option>" +
        "<option value='CREATED'>CREATED</option>" +
        "<option value='MODELED'>MODELED</option>" +
        "<option value='PAID'>PAID</option>" +
        "<option value='PRINTED'>PRINTED</option>" +
        "<option value='PACKED'>PACKED</option>" +
        "<option value='SENT'>SENT</option>" +
        "<option value='CANCELED'>CANCELED</option>" +
        "</select>";
    tmp += "<td>" + selectStatus + "</td>";

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
    let dateClosed = document.getElementById("dateClosed-" + ORDER + SUFFIX_SEARCH_FIELD);
    let dateClosedValue = (dateClosed == null) ? "" : dateClosed.value;
    let status = document.getElementById("status-" + ORDER + SUFFIX_SEARCH_FIELD);
    let selectedStatus = status.options[status.selectedIndex].value;
    let priceMin = document.getElementById("price-min-" + ORDER + SUFFIX_SEARCH_FIELD).value;
    let priceMax = document.getElementById("price-max-" + ORDER + SUFFIX_SEARCH_FIELD).value;
    let count = document.getElementById("count-" + ORDER + SUFFIX_SEARCH_FIELD).value;

    fetch(URL_ORDERS + "/search" + `?id=${id}&firstName=${firstName}&lastName=${lastName}&dateCreate=${dateCreate}&dateClosed=${dateClosedValue}&selectedStatus=${selectedStatus}&priceMin=${priceMin}&priceMax=${priceMax}&count=${count}`)
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

function searchOrdersStatistics() {
    let dateStart = document.getElementById(DATE_START_ORDER + SUFFIX_SEARCH_FIELD).value;
    let dateEnd = document.getElementById(DATE_END_ORDER + SUFFIX_SEARCH_FIELD).value;
    let tmp = EMPTY_VALUE;
    let table = document.getElementById(DATA_ORDERS + "-statistics");

    fetch(URL_ORDERS + "/statistics" + `?dateStart=${dateStart}&dateEnd=${dateEnd}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            table.innerHTML = EMPTY_VALUE;
            if (data.length > 0) {
                data.forEach((order => {
                    tmp += "<tr><td>" + order.year + "</td>";
                    tmp += "<td>" + order.month + "</td>";
                    tmp += "<td>" + order.countOrders + "</td>";
                    tmp += "<td>" + order.countItems + "</td>";
                    tmp += "<td>" + order.totalSum + "</td>";
                    tmp += "<td>" + Number(order.middleSumOfOrder).toFixed(1) + "</td>";
                    tmp += "<td>" + Number(order.middleCountOfItems).toFixed(1) + "</td>";
                    tmp += "<td>" + Number(order.middleSumOfItem).toFixed(1) + "</td>";
                }))
            }
            table.innerHTML = tmp;
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

    setTotalForUpdateItems(suffix);
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
    setTotalForUpdateItems(suffix);
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
    setTotalForUpdateItems(suffix);
}

function setPrice(price, count) {
    return (Number(price) * Number(count)).toFixed(1);
}

function setTotalForUpdateItems(suffix) {
    let {discount, totalPriceEl, price} = setTotalForItems(suffix);
    let discountSelected = discount.options[discount.selectedIndex];
    if (discountSelected !== undefined) {
        totalPriceEl.innerHTML = (parseDiscount(price, discountSelected)).toFixed(1);
    } else {
        totalPriceEl.innerHTML = (price).toFixed(1);
    }

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
    return {discount, totalPriceEl, price};
}

function setTotalForDeleteItems(suffix) {
    let {discount, totalPriceEl, price} = setTotalForItems(suffix);
    if (discount != null || discount != "") {
        totalPriceEl.innerHTML = (
            (100 - discount.innerText) / 100 * Number(price)
        ).toFixed(1);
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
    loadOrders(URL_ORDERS);
    document.getElementById(DATA_ORDERS).innerHTML = EMPTY_VALUE;
    hiddenForm(modal);
}

//Get data

function getDataOrder(suffix) {
    let clientId = document.querySelector(CLIENT_ID_ORDER + suffix).value;
    let discountId = document.querySelector(DISCOUNT_ID_ORDER + suffix).value;
    let totalPrice = document.querySelector(TOTAL_PRICE_ORDER + suffix).value;
    let article = document.getElementById(ARTICLE_ORDER + suffix);

    if (article != null) {
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

function editOrderStatus(id) {
    let status = document.getElementById("edit-order-status-" + id);
    let selectStatus = status.options[status.selectedIndex].value;

    fetchSendData(URL_EDIT_STATUS + `?id=${id}&status=${selectStatus}`, {
        id: id,
        status: selectStatus
    }, PUT)
        .then((data) => {
            console.log(data);
            document.getElementById(DATA_ORDERS).innerHTML = EMPTY_VALUE;
            loadOrdersStatus(URL_ORDERS_STATUS);
        });
}

function fillFormUpdateOrderById(id) {

    fetch(URL_ORDERS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (order) {
            console.log(order);
            let selectClients = document.getElementById(CLIENTS_FOUND + SUFFIX_EDIT_FIELD);
            let selectDiscount = document.getElementById(CLIENT_DISCOUNTS + SUFFIX_EDIT_FIELD);
            let tableItems = document.getElementById(CLIENT_ITEMS + SUFFIX_EDIT_FIELD);
            let tmp = tableItems.innerHTML;
            document.getElementById(ARTICLE_ORDER + SUFFIX_EDIT_FIELD).value = id;

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
                addItemToTableUpdate(item, tableItems, tmp, SUFFIX_EDIT_FIELD);
            });

            openForm(MODAL_EDIT);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

//Method DELETE

function fillFormDeleteOrderById(id) {

    fetch(URL_ORDERS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (order) {
            console.log(order);
            let inputClient = document.getElementById(CLIENTS_FOUND + SUFFIX_DELETE_FIELD);
            let inputDiscount = document.getElementById(CLIENT_DISCOUNTS + SUFFIX_DELETE_FIELD);
            let tableItems = document.getElementById(CLIENT_ITEMS + SUFFIX_DELETE_FIELD);
            document.getElementById(ORDER_ID + SUFFIX_DELETE_FIELD).value = id;

            let tmp = tableItems.innerHTML;

            let client = order.clientPl;
            let discount = order.discountPl;
            let items = order.itemPlList;

            inputClient.value = "№" + client.id + " - " + client.lastName + " " + client.firstName;
            inputClient.innerHTML = client.id;

            if (discount != null) {
                inputDiscount.value = discount.type.name + " - " + discount.value;
                inputDiscount.innerHTML = discount.id;
            }

            items.forEach(item => {
                addItemToTableDelete(item, tableItems, tmp, SUFFIX_DELETE_FIELD);
            });

            openForm(MODAL_DELETE);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

function deleteOrder() {
    let id = document.getElementById(ORDER_ID + SUFFIX_DELETE_FIELD).value;

    fetch(URL_ORDERS + "/" + id, {
        method: DELETE,
    }).then((id) => {
        fetchOrderThen(id, MODAL_DELETE)
    });
}