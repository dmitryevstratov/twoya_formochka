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