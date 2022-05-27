//URL
const URL_CREATE = "/discounts/create";
const URL_DISCOUNT_TYPES = "/discount-types";
const DATA_DISCOUNTS = "data-discounts"
const URL_EDIT = "/discounts/edit";

//ID modal window
const MODAL_CREATE = "addDiscount";
const MODAL_DELETE = "deleteDiscount";
const MODAL_EDIT = "editDiscount";

//Discount field
const DISCOUNT_ID = "id-discount";
const DISCOUNT_TYPE = "type-discount";
const DISCOUNT_VALUE = "value-discount";
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
    tmp += "<td><button type=\"button\" onmousedown= \"fillFormUpdateDiscountById("
        + discount.id +
        ")\" data-bs-toggle=\"modal\" class=\"button button-update\" data-bs-target=\"#editDiscount\">\n" +
        " Редактировать" +
        "</button></td>";
    tmp += "<td><button type=\"button\" onmousedown= \"fillFormDeleteDiscountById("
        + discount.id +
        ")\" data-bs-toggle=\"modal\" class=\"button button-delete\" data-bs-target=\"#deleteDiscount\">\n" +
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

function searchDiscountTypeToSelect(suffix) {
    let type = document.getElementById(DISCOUNT + SUFFIX_SEARCH_FIELD + suffix).value;

    fetch(URL_DISCOUNT_TYPES + "/search" + `?type=${type}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(DISCOUNT_COUNT + suffix).innerText = data.length;
            let select = document.getElementById(DISCOUNT_FOUND + suffix);
            let tmp = EMPTY_VALUE;
            if (data.length > 0) {
                tmp += "<option value='-1'>" + "Нет" + "</option>";
                data.forEach((type => {
                    tmp += "<option value=" + type.id + "> №" + type.id + " - " + type.name + "</option>";
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

function fillSelectDiscountsType(suffix) {
    let selectDiscount = document.getElementById(DISCOUNT_FOUND + suffix);
    let id = selectDiscount.options[selectDiscount.selectedIndex].value;
    let rq = document.getElementById(DISCOUNT_TYPE + DISCOUNT_FOR_RQ + suffix);

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
    let numValue = Number(value);

    descriptionForResultHtml.innerHTML = EMPTY_VALUE;

    if (type === "" || type === "-1") {
        alertContent += "<p>Укажите тип скидки!</p>";
    } else if (numValue === "" || numValue <= 0 || numValue >= 100) {
        alertContent += "<p>Укажите корректно значение скидки!</p>";
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

//Method UPDATE

function fillFormUpdateDiscountById(id) {
    fetch(URL_DISCOUNTS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (discount) {
            console.log(discount);
            document.getElementById(DISCOUNT_TYPE + DISCOUNT_FOR_RQ + SUFFIX_EDIT_FIELD).value = discount.type.name;
            document.getElementById(DISCOUNT_VALUE + DISCOUNT_FOR_RQ + SUFFIX_EDIT_FIELD).value = discount.value;
            document.getElementById(DISCOUNT_ID + SUFFIX_EDIT_FIELD).value = id;
            openForm(MODAL_EDIT);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

function editDiscount() {
    let type = document.getElementById(DISCOUNT_TYPE + DISCOUNT_FOR_RQ + SUFFIX_EDIT_FIELD).value;
    let value = document.getElementById(DISCOUNT_VALUE + DISCOUNT_FOR_RQ + SUFFIX_EDIT_FIELD).value;
    let id = document.getElementById(DISCOUNT_ID + SUFFIX_EDIT_FIELD).value;
    if (checkValidityDiscountForm(type, value)) {
        fetchSendData(URL_EDIT, {
            id: id,
            type: {
                name: type
            },
            value: value
        }, PUT).then((data) => {
            fetchDiscountThen(data, MODAL_EDIT)
        });
    }
}

//Method DELETE

function deleteDiscount() {
    let id = document.getElementById(DISCOUNT_ID + SUFFIX_DELETE_FIELD).value;

    fetch(URL_DISCOUNTS + "/" + id, {
        method: DELETE,
    }).then((id) => {
        fetchDiscountThen(id, MODAL_DELETE)
    });
}

function fillFormDeleteDiscountById(id) {
    fetch(URL_DISCOUNTS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (discount) {
            console.log(discount);
            document.getElementById(DISCOUNT_TYPE + DISCOUNT_FOR_RQ + SUFFIX_DELETE_FIELD).value = discount.type.name;
            document.getElementById(DISCOUNT_VALUE + DISCOUNT_FOR_RQ + SUFFIX_DELETE_FIELD).value = discount.value;
            document.getElementById(DISCOUNT_ID + SUFFIX_DELETE_FIELD).value = id;
            openForm(MODAL_DELETE);
        }
    ).catch(function (error) {
        console.log(error);
    });
}