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

function searchItemToSelect() {
    let id = document.querySelector(ITEM_ARTICLE + SUFFIX_SEARCH_FIELD);
    let name = document.querySelector(ITEM_NAME + SUFFIX_SEARCH_FIELD);
    let type = document.querySelector(ITEM_TYPE + SUFFIX_SEARCH_FIELD);
    let category = document.querySelector(ITEM_CATEGORY + SUFFIX_SEARCH_FIELD);
    let size = document.querySelector(ITEM_SIZE + SUFFIX_SEARCH_FIELD);

    fetch(URL_ITEMS + "/search" + `?id=${id.value}&name=${name.value}&type=${type.value}&category=${category.value}&size=${size.value}`)
        .then((resp) => resp.json())
        .then(function (data) {
            console.log(data);
            document.getElementById(ITEMS_COUNT).innerText = data.length;
            let select = document.getElementById(ITEMS_FOUND);
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

function fillSelectItems() {
    let clientItems = document.getElementById(CLIENT_ITEMS);
    let selectedItem = document.getElementById(ITEMS_FOUND);
    let id = selectedItem.options[selectedItem.selectedIndex].value;
    let tmp = clientItems.innerHTML;

    if (id != -1) {
        fetch(URL_ITEMS + "/" + id)
            .then(resp => resp.json())
            .then(function (item) {
                let index = getMaxIndex(clientItems.querySelectorAll(TAG_TR));
                index = Number(index) + Number(1);
                tmp += "<tr id=" + ITEM + index + ">" + "<td class=" + ITEM_ARTICLE_CL + ">" + item.id + "</td>";
                tmp += "<td>" + item.name + "</td>";
                tmp += "<td>" + item.category.name + "</td>";
                tmp += "<td>" + item.type.name + "</td>";
                tmp += "<td>" + item.size + "</td>";
                tmp += "<td>" + "<button type='button' onclick=incrementItem(" +
                    index + ',' + item.price +
                    ")>+</button><span class= " + ITEM_COUNT_CL + " id=" + ITEM_COUNT + index + ">1</span><button type='button' onclick=decrementItem(" +
                    index + ',' + item.price
                    + ")>-</button>" + "</td>";
                tmp += "<td class=" + ITEM_PRICE_CL + " id=" + ITEM_PRICE + index + ">" + item.price + "</td>";
                tmp += "<td>" + "<button onclick= \"deleteItemFromTable("
                    + index +
                    ")\" type=\"button\">\n" +
                    " Удалить" +
                    "</button>" + "</td></tr>";
                clientItems.innerHTML = tmp;
                setTotalForItems();
            })
        selectedItem.selectedIndex = 0;
    }

}


