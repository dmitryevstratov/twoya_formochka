//URL
const URL_CREATE = "/discounts/create";
const URL_DISCOUNTS = "/discounts";
const DATA_DISCOUNTS = "data-discounts"
const URL_EDIT = "/discounts/edit";

//ID modal window
const MODAL_CREATE = "addDiscount";
const MODAL_DELETE = "deleteOrder";
const MODAL_EDIT = "editOrder";

//Discount field
const DISCOUNT_ID = "id-discount";
const DISCOUNT_TYPE = "type-discount";
const DISCOUNT_VALUE = "value-discount";
const DISCOUNT = "discount";
const DISCOUNT_COUNT = "discount-count";
const DISCOUNT_FOUND = "discount-found";
const DISCOUNT_FOR_RQ = "-for-rq";
const DISCOUNT_RESULT = "discount-result";

//Method GET

function loadDiscounts(url) {

    fetch(url)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            if (data.length > 0) {
                data.forEach((order => {
                    addDiscountInTable(order);
                }))
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

function addDiscountInTable(discount) {
    let table = document.getElementById(DATA_DISCOUNTS);
    let tmp = EMPTY_VALUE;

    tmp += "<td id=" + DISCOUNT_ID + ">" + discount.id + "</td>";
    tmp += "<td id=" + DISCOUNT_TYPE + ">" + discount.type.name + "</td>";
    tmp += "<td id=" + DISCOUNT_VALUE + ">" + discount.value + "</td>";
    tmp += "<td><button type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#editDiscount\">\n" +
        " Редактировать" +
        "</button></td>";
    tmp += "<td><button type=\"button\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteDiscount\">\n" +
        " Удалить" +
        "</button></td>";

    table.innerHTML += tmp;
}

//Method SEARCH

function searchDiscount() {
    let id = document.getElementById(DISCOUNT_ID + SUFFIX_SEARCH_FIELD).value;
    let type = document.getElementById(DISCOUNT_TYPE + SUFFIX_SEARCH_FIELD).value.toLowerCase();

    fetch(URL_DISCOUNTS + "/search" + `?id=${id}&type=${type}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(DATA_DISCOUNTS).innerHTML = EMPTY_VALUE;
            if (data.length > 0) {
                data.forEach((discount => {
                    addDiscountInTable(discount);
                }))
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

function searchDiscountToSelect() {
    let type = document.getElementById(DISCOUNT + SUFFIX_SEARCH_FIELD).value;

    fetch(URL_DISCOUNTS + "/search" + `?type=${type}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(DISCOUNT_COUNT).innerText = data.length;
            let select = document.getElementById(DISCOUNT_FOUND);
            let tmp = EMPTY_VALUE;
            if (data.length > 0) {
                tmp += "<option value='-1'>" + "Нет" + "</option>";
                data.forEach((discount => {
                    tmp += "<option value=" + discount.id + "> №" + discount.id + " - " + discount.type.name + "</option>";
                }))
                select.innerHTML = tmp;
                select.selectedIndex = 0;
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

//Method GET

function fillSelectDiscountsType() {
    let selectDiscount = document.getElementById(DISCOUNT_FOUND);
    let id = selectDiscount.options[selectDiscount.selectedIndex].value;
    let rq = document.getElementById(DISCOUNT_TYPE + DISCOUNT_FOR_RQ);

    if (id != -1) {
        fetch(URL_DISCOUNTS + "/" + id)
            .then(resp => resp.json()).then(
            function (discount) {
                console.log(discount)
                rq.value = discount.type.name;
            }
        )
    }
}

//Method POST

function checkValidityDiscountForm(type, value) {
    let descriptionForResultHtml = document.getElementById(DISCOUNT_RESULT);
    let alertContainer = document.createElement('div');
    let alertContent = EMPTY_VALUE;
    let success = false;

    descriptionForResultHtml.innerHTML = EMPTY_VALUE;

    if (type === "" || type === "-1") {
        alertContent += "<p>Укажите тип скидки!</p>";
    } else if (value === "" || value === "0") {
        alertContent += "<p>Укажите значение скидки!</p>";
    } else {
        alertContent += "<p>Скидка готова!</p>";
        success = true;
    }

    alertContainer.innerHTML = alertContent;
    descriptionForResultHtml.append(alertContainer);

    return success;
}

function createDiscount() {
    let type = document.getElementById(DISCOUNT_TYPE + DISCOUNT_FOR_RQ).value;
    let value = document.getElementById(DISCOUNT_VALUE + DISCOUNT_FOR_RQ).value;
    if (checkValidityDiscountForm(type, value)) {
        fetchSendData(URL_CREATE, {
            type: {
                name: type
            },
            value: value
        }, POST).then((data) => {
            fetchDiscountThen(data, MODAL_CREATE)
        });
    }
}

function fetchDiscountThen(data, modal) {
    console.log(data);
    loadDiscounts(URL_DISCOUNTS);
    document.getElementById(DATA_DISCOUNTS).innerHTML = EMPTY_VALUE;
    hiddenForm(modal);
}