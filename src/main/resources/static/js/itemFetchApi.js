//Item fields

const ITEM_ARTICLE = "#item-article";
const ITEM_NAME = "#item-name";
const ITEM_TYPE = "#item-type";
const ITEM_CATEGORY = "#item-category";
const ITEM_SIZE = "#item-size";
const ITEMS_COUNT = "items-count";
const ITEMS_FOUND = "items-found";
const ITEM = "item-";
const ITEM_DATA = "data-items";
const ITEM_ID_ID = "item-id";
const ITEM_NAME_ID = "item-name";
const ITEM_TYPE_ID = "item-type";
const ITEM_CATEGORY_ID = "item-category";
const ITEM_SIZE_ID = "item-size";
const ITEM_PRICE_ID = "item-price";
const ITEM_RESULT = "item-result";
const ITEMS_TYPE_COUNT = "item-types-count";
const ITEMS_TYPE_FOUND = "item-types-found";
const ITEMS_CATEGORY_COUNT = "item-categories-count";
const ITEMS_CATEGORY_FOUND = "item-categories-found";
const TYPE_NAME = "type-name";
const CATEGORY_NAME = "item-categories-name";

//URL
const URL_ITEMS = "/items";
const URL_ITEM_CREATE = "/items/create"
const URL_ITEM_EDIT = "/items/edit"

//MODAL

const MODAL_ITEM_CREATE = "addItem";
const MODAL_ITEM_EDIT = "editItem";
const MODAL_ITEM_DELETE = "deleteItem";

//Fetch functions

//Method GET

function loadItems(url) {

    fetch(url)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            if (data.length > 0) {
                data.forEach((item => {
                    addItemInTable(item);
                }))
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

function addItemInTable(item) {
    let table = document.getElementById(ITEM_DATA);
    let tmp = EMPTY_VALUE;

    tmp += "<tr><td id=" + ITEM_ID_ID + ">" + item.id + "</td>";
    tmp += "<td id=" + ITEM_NAME_ID + ">" + item.name + "</td>";
    tmp += "<td id=" + ITEM_TYPE_ID + ">" + item.type.name + "</td>";
    tmp += "<td id=" + ITEM_CATEGORY_ID + ">" + item.category.name + "</td>";
    tmp += "<td id=" + ITEM_SIZE_ID + ">" + item.size + "</td>";
    tmp += "<td id=" + ITEM_PRICE_ID + ">" + item.price + "</td>";
    tmp += "<td><button onmousedown= \"fillFormUpdateItemById("
        + item.id +
        ")\" type=\"button\" class=\"button button-update\" data-bs-toggle=\"modal\" data-bs-target=\"#editItem\">\n" +
        " Редактировать" +
        "</button></td>";
    tmp += "<td><button onmousedown= \"fillFormDeleteItemById("
        + item.id +
        ")\" type=\"button\" class=\"button button-delete\" data-bs-toggle=\"modal\" data-bs-target=\"#deleteItem\">\n" +
        " Удалить" +
        "</button></td>";

    table.innerHTML += tmp;
}

//Method SEARCH

function searchItemToSelect(suffix) {
    let id = document.querySelector(ITEM_ARTICLE + SUFFIX_SEARCH_FIELD + suffix);
    let name = document.querySelector(ITEM_NAME + SUFFIX_SEARCH_FIELD + suffix);
    let type = document.querySelector(ITEM_TYPE + SUFFIX_SEARCH_FIELD + suffix);
    let category = document.querySelector(ITEM_CATEGORY + SUFFIX_SEARCH_FIELD + suffix);
    let size = document.querySelector(ITEM_SIZE + SUFFIX_SEARCH_FIELD + suffix);

    fetch(URL_ITEMS + "/search" + `?id=${id.value}&name=${name.value}&type=${type.value}&category=${category.value}&size=${size.value}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(ITEMS_COUNT + suffix).innerText = data.length;
            let select = document.getElementById(ITEMS_FOUND + suffix);
            let tmp = EMPTY_VALUE;
            if (data.length > 0) {
                tmp += "<option value='-1'>" + "Нет" + "</option>";
                data.forEach((item => {
                    tmp += "<option value=" + item.id + "> №" + item.id + " - " + item.name + " - " + item.size + " см" + "</option>";
                }))
                select.innerHTML = tmp;
                select.selectedIndex = 0;
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

function searchItems() {
    let id = document.querySelector(ITEM_ARTICLE + SUFFIX_SEARCH_FIELD);
    let name = document.querySelector(ITEM_NAME + SUFFIX_SEARCH_FIELD);
    let type = document.querySelector(ITEM_TYPE + SUFFIX_SEARCH_FIELD);
    let category = document.querySelector(ITEM_CATEGORY + SUFFIX_SEARCH_FIELD);
    let size = document.querySelector(ITEM_SIZE + SUFFIX_SEARCH_FIELD);

    fetch(URL_ITEMS + "/search" + `?id=${id.value}&name=${name.value}&type=${type.value}&category=${category.value}&size=${size.value}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(ITEM_DATA).innerHTML = EMPTY_VALUE;
            if (data.length > 0) {
                data.forEach((item => {
                    addItemInTable(item);
                }))
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

function searchItemTypeToSelect(suffix) {
    let type = document.getElementById(TYPE_NAME + SUFFIX_SEARCH_FIELD + suffix).value;

    fetch(URL_ITEMS + "/search-type" + `?type=${type}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(ITEMS_TYPE_COUNT + suffix).innerText = data.length;
            let select = document.getElementById(ITEMS_TYPE_FOUND + suffix);
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

function searchItemCategoryToSelect(suffix) {
    let category = document.getElementById(CATEGORY_NAME + SUFFIX_SEARCH_FIELD + suffix).value;

    fetch(URL_ITEMS + "/search-category" + `?category=${category}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(ITEMS_CATEGORY_COUNT + suffix).innerText = data.length;
            let select = document.getElementById(ITEMS_CATEGORY_FOUND + suffix);
            let tmp = EMPTY_VALUE;
            if (data.length > 0) {
                tmp += "<option value='-1'>" + "Нет" + "</option>";
                data.forEach((category => {
                    tmp += "<option value=" + category.id + "> №" + category.id + " - " + category.name + "</option>";
                }))
                select.innerHTML = tmp;
                select.selectedIndex = 0;
            }
        })
        .catch(function (e) {
            console.log(e);
        })
}

function fillSelectItemTypes(suffix) {
    let type = document.getElementById(ITEM_TYPE_ID + FOR_RQ + suffix);
    let selectTypes = document.getElementById(ITEMS_TYPE_FOUND + suffix);
    let value = selectTypes.options[selectTypes.selectedIndex];
    let innerText = value.innerText;
    type.value = innerText.substring(6, innerText.length);
}

function fillSelectItemCategories(suffix) {
    let type = document.getElementById(ITEM_CATEGORY_ID + FOR_RQ + suffix);
    let selectTypes = document.getElementById(ITEMS_CATEGORY_FOUND + suffix);
    let value = selectTypes.options[selectTypes.selectedIndex];
    let innerText = value.innerText;
    type.value = innerText.substring(6, innerText.length);
}

//Method fill fields

function getMaxIndex(items) {
    let maxIndex = 0;

    items.forEach(item => {
        let index = item.id.split("-")[1];
        if (index >= maxIndex) {
            maxIndex = index;
        }
    })

    return maxIndex;
}

function fillSelectItems(suffix) {
    let clientItems = document.getElementById(CLIENT_ITEMS + suffix);
    let selectedItem = document.getElementById(ITEMS_FOUND + suffix);
    let id = selectedItem.options[selectedItem.selectedIndex].value;
    let tmp = clientItems.innerHTML;

    if (id != -1) {
        fetch(URL_ITEMS + "/" + id)
            .then(resp => resp.json())
            .then(function (item) {
                addItemToTable(item, clientItems, tmp, suffix);
            })
        selectedItem.selectedIndex = 0;
    }

}

//Method POST

function createItem() {
    fillFieldsForItem('');
    if (checkValidityItemForm('')) {
        fetchSendData(URL_ITEM_CREATE, getDataItem(''), POST)
            .then((data) => {
                fetchItemThen(data, MODAL_ITEM_CREATE)
            });
    }
}

function fillFieldsForItem(suffix) {
    let name = document.getElementById(ITEM_NAME_ID + FOR_RQ + suffix).value;
    let type = document.getElementById(ITEM_TYPE_ID + FOR_RQ + suffix).value;
    let category = document.getElementById(ITEM_CATEGORY_ID + FOR_RQ + suffix).value;
    let size = document.getElementById(ITEM_SIZE_ID + FOR_RQ + suffix).value;
    let price = document.getElementById(ITEM_PRICE_ID + FOR_RQ + suffix).value;

    let idInput = document.getElementById(ITEM_ID_ID + SEND + suffix);
    let nameInput = document.getElementById(ITEM_NAME_ID + SEND + suffix);
    let typeInput = document.getElementById(ITEM_TYPE_ID + SEND + suffix);
    let categoryInput = document.getElementById(ITEM_CATEGORY_ID + SEND + suffix);
    let sizeInput = document.getElementById(ITEM_SIZE_ID + SEND + suffix);
    let priceInput = document.getElementById(ITEM_PRICE_ID + SEND + suffix);

    if (suffix == SUFFIX_EDIT_FIELD) {
        idInput.value = document.getElementById(ITEM_ID_ID + FOR_RQ + suffix).value;
    }
    nameInput.value = name;
    typeInput.value = type;
    categoryInput.value = category;
    sizeInput.value = size;
    priceInput.value = price;
}

function checkValidityItemForm(suffix) {
    let nameInput = document.getElementById(ITEM_NAME_ID + SEND + suffix);
    let typeInput = document.getElementById(ITEM_TYPE_ID + SEND + suffix);
    let categoryInput = document.getElementById(ITEM_CATEGORY_ID + SEND + suffix);
    let sizeInput = document.getElementById(ITEM_SIZE_ID + SEND + suffix);
    let priceInput = document.getElementById(ITEM_PRICE_ID + SEND + suffix);
    let descriptionForResultHtml = document.getElementById(ITEM_RESULT + suffix);
    let alertContainer = document.createElement('div');
    let alertContent = EMPTY_VALUE;
    let success = false;

    descriptionForResultHtml.innerHTML = EMPTY_VALUE;

    if (nameInput.value === "") {
        alertContent += "<p>Введите название!</p>";
    } else if (typeInput.value === "") {
        alertContent += "<p>Введите тип!</p>";
    } else if (categoryInput.value === "") {
        alertContent += "<p>Введите категорию!</p>";
    } else if (sizeInput.value === "" || sizeInput.value === "0") {
        alertContent += "<p>Введите размер!</p>";
    } else if (priceInput.value === "" || priceInput.value === "0") {
        alertContent += "<p>Введите стоимость!</p>";
    } else {
        alertContent += "<p>Товар готов!</p>";
        success = true;
    }

    alertContainer.innerHTML = alertContent;
    descriptionForResultHtml.append(alertContainer);

    return success;
}

function fetchItemThen(data, modal) {
    console.log(data);
    loadItems(URL_ITEMS);
    document.getElementById(ITEM_DATA).innerHTML = EMPTY_VALUE;
    hiddenForm(modal);
}

function getDataItem(suffix) {
    let idInput;
    let nameInput = document.getElementById(ITEM_NAME_ID + SEND + suffix).value;
    let typeInput = document.getElementById(ITEM_TYPE_ID + SEND + suffix).value;
    let categoryInput = document.getElementById(ITEM_CATEGORY_ID + SEND + suffix).value;
    let sizeInput = document.getElementById(ITEM_SIZE_ID + SEND + suffix).value;
    let priceInput = document.getElementById(ITEM_PRICE_ID + SEND + suffix).value;

    if (suffix == SUFFIX_EDIT_FIELD) {
        idInput = document.getElementById(ITEM_ID_ID + SEND + suffix).value;
    }

    return {
        id: idInput,
        name: nameInput,
        type: {
            name: typeInput
        },
        category: {
            name: categoryInput
        },
        size: sizeInput,
        price: priceInput
    };
}

//Method PUT

function fillFormUpdateItemById(id) {
    fetch(URL_ITEMS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (item) {
            console.log(item);

            document.getElementById(ITEM_ID_ID + FOR_RQ + SUFFIX_EDIT_FIELD).value = item.id;
            document.getElementById(ITEM_NAME_ID + FOR_RQ + SUFFIX_EDIT_FIELD).value = item.name;
            document.getElementById(ITEM_TYPE_ID + FOR_RQ + SUFFIX_EDIT_FIELD).value = item.type.name;
            document.getElementById(ITEM_CATEGORY_ID + FOR_RQ + SUFFIX_EDIT_FIELD).value = item.category.name;
            document.getElementById(ITEM_SIZE_ID + FOR_RQ + SUFFIX_EDIT_FIELD).value = item.size;
            document.getElementById(ITEM_PRICE_ID + FOR_RQ + SUFFIX_EDIT_FIELD).value = item.price;

            openForm(MODAL_ITEM_EDIT);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

function editItem() {
    fillFieldsForItem(SUFFIX_EDIT_FIELD);
    if (checkValidityItemForm(SUFFIX_EDIT_FIELD)) {
        fetchSendData(URL_ITEM_EDIT, getDataItem(SUFFIX_EDIT_FIELD), PUT)
            .then((data) => {
                fetchItemThen(data, MODAL_ITEM_EDIT)
            });
    }
}

//Method DELETE

function fillFormDeleteItemById(id) {
    fetch(URL_ITEMS + "/" + id).then((
        resp => resp.json()
    )).then(
        function (item) {
            console.log(item);
            document.getElementById(ITEM_ID_ID + FOR_RQ + SUFFIX_DELETE_FIELD).value = item.id;
            document.getElementById(ITEM_NAME_ID + FOR_RQ + SUFFIX_DELETE_FIELD).value = item.name;
            document.getElementById(ITEM_TYPE_ID + FOR_RQ + SUFFIX_DELETE_FIELD).value = item.type.name;
            document.getElementById(ITEM_CATEGORY_ID + FOR_RQ + SUFFIX_DELETE_FIELD).value = item.category.name;
            document.getElementById(ITEM_SIZE_ID + FOR_RQ + SUFFIX_DELETE_FIELD).value = item.size;
            document.getElementById(ITEM_PRICE_ID + FOR_RQ + SUFFIX_DELETE_FIELD).value = item.price;
            openForm(MODAL_ITEM_DELETE);
        }
    ).catch(function (error) {
        console.log(error);
    });
}

function deleteItem() {
    let id = document.getElementById(ITEM_ID_ID + FOR_RQ + SUFFIX_DELETE_FIELD).value;

    fetch(URL_ITEMS + "/" + id, {
        method: DELETE,
    }).then((id) => {
        fetchItemThen(id, MODAL_ITEM_DELETE)
    });
}