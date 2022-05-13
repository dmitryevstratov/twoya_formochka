//Display values
const DISPLAY_NONE = "none";
const DISPLAY_BLOCK = "block";

//Class names
const CLASSNAME_FADE = "fade";
const CLASSNAME_SHOW = "show";
const MODAL_BACKDROP = "modal-backdrop";
const MODAL_OPEN = "modal-open";

function clearForm(idForm) {
    if (idForm != null) {
        let form = document.querySelector("#" + idForm);
        form.querySelectorAll("input").forEach(e => {
            if (e.type === "text" || e.type === "number" || e.type === "email" || e.type === "tel" || e.type === "date") {
                e.value = EMPTY_VALUE;
            }
        });
        form.querySelectorAll("textarea").forEach(e => e.value = EMPTY_VALUE);
    }
}

function hiddenForm(id) {
    document.body.classList.remove(MODAL_OPEN);
    document.querySelector("#" + id).style.display = DISPLAY_NONE;
    document.querySelector("#" + id).classList.remove(CLASSNAME_SHOW);
    document.body.style.overflow = "visible";

    document.querySelectorAll("." + MODAL_BACKDROP)
        .forEach(element => element.style.display = DISPLAY_NONE);

    switch (id) {
        case 'addOrder':
            clearOrderForm("");
            break;
        case 'addDiscount':
            clearDiscountForm("");
            break;
        case 'editOrder':
            clearOrderForm(SUFFIX_EDIT_FIELD);
            break;
        case 'editDiscount':
            clearDiscountForm(SUFFIX_EDIT_FIELD);
            break;
        case 'deleteOrder':
            clearOrderFormDelete(SUFFIX_DELETE_FIELD);
            break;
        default:
            clearForm(id);
    }
}

function openForm(id) {
    document.querySelector("#" + id).classList.add(CLASSNAME_SHOW);
    document.querySelector("#" + id).style.display = DISPLAY_BLOCK;

    if (document.querySelector("." + MODAL_BACKDROP) == null) {
        let div = document.createElement('div');
        div.style.display = DISPLAY_BLOCK;
        div.className = MODAL_BACKDROP;
        div.classList.add(CLASSNAME_SHOW);
        div.classList.add(CLASSNAME_FADE);
        document.body.append(div);
    } else {
        document.querySelector("." + MODAL_BACKDROP).style.display = DISPLAY_BLOCK;
    }

}

//Fetch

async function fetchSendData(url = '', data = {}, method) {
    const response = await fetch(url, {
        method: method,
        headers: {
            'Content-Type': CONTENT_TYPE_JSON
        },
        body: JSON.stringify(data)
    });
    return await response.json();
}

function clearOrderForm(suffix) {
    let formClient = document.getElementById("form-choose-client-for-order" + suffix);
    let formItem = document.getElementById("form-choose-item-for-order" + suffix);
    let formOrder = document.getElementById("form-send-order" + suffix);
    let orderResult = document.getElementById(ORDER_RESULT + suffix);

    let {table, totalCount, totalPrice} = totalForItemTable(suffix);

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

function totalForItemTable(suffix) {
    let table = document.getElementById(CLIENT_ITEMS + suffix);
    let totalCount = document.getElementById(ITEMS_TOTAL + "count" + suffix);
    let totalPrice = document.getElementById(ITEMS_TOTAL + "price" + suffix);
    return {table, totalCount, totalPrice};
}

function clearOrderFormDelete(suffix) {
    let {table, totalCount, totalPrice} = totalForItemTable(suffix);

    let client = document.getElementById(CLIENTS_FOUND + suffix);
    let discount = document.getElementById(CLIENT_DISCOUNTS + suffix);

    table.innerHTML = EMPTY_VALUE;
    totalCount.innerHTML = 0;
    totalPrice.innerHTML = 0;

    client.innerHTML = EMPTY_VALUE;
    discount.innerHTML = EMPTY_VALUE;
    client.value = EMPTY_VALUE;
    discount.value = EMPTY_VALUE;
}

function clearDiscountForm(suffix) {
    let discountSearch = document.getElementById(DISCOUNT + SUFFIX_SEARCH_FIELD + suffix);
    let discountFound = document.getElementById(DISCOUNT_FOUND + suffix);
    let typeDiscountForRq = document.getElementById(DISCOUNT_TYPE + DISCOUNT_FOR_RQ + suffix);
    let valueDiscountForRq = document.getElementById(DISCOUNT_VALUE + DISCOUNT_FOR_RQ + suffix);
    let discountCount = document.getElementById(DISCOUNT_COUNT + suffix);
    let result = document.getElementById(DISCOUNT_RESULT + suffix);

    discountFound.selectedIndex = 0;
    discountFound.innerHTML = "<option value='-1'>" + "Нет" + "</option>";

    discountSearch.value = EMPTY_VALUE;
    typeDiscountForRq.value = EMPTY_VALUE;
    valueDiscountForRq.value = EMPTY_VALUE;
    result.innerHTML = EMPTY_VALUE;
    discountCount.innerHTML = 0;
}