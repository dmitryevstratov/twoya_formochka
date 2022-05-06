//Item fields

const ITEM_ARTICLE = "#item-article";
const ITEM_NAME = "#item-name";
const ITEM_TYPE = "#item-type";
const ITEM_CATEGORY = "#item-category";
const ITEM_SIZE = "#item-size";
const ITEMS_COUNT = "items-count";
const ITEMS_FOUND = "items-found";
const ITEM = "item-";

//URL
const URL_ITEMS = "/items";

//Fetch functions

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


