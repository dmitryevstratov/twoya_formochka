function checkEmptyField(field) {
    if (field == null || field === "") {
        return "";
    } else {
        return field;
    }
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function getIndexForItem(tableItems) {
    let index = getMaxIndex(tableItems.querySelectorAll(TAG_TR));
    index = Number(index) + Number(1);
    return index;
}

function addItemToTable(item, tableItems, tmp, suffix) {
    let index = getIndexForItem(tableItems);
    tmp += createItemRowForOrder(item, suffix, index);
    tableItems.innerHTML = tmp;
    setTotalForUpdateItems(suffix);
}

function addItemToTableUpdate(item, tableItems, tmp, suffix) {
    let index = getIndexForItem(tableItems);
    tmp += createItemRowForOrder(item, suffix, index);
    tableItems.innerHTML += tmp;
    setTotalForUpdateItems(suffix);
}

function addItemToTableDelete(item, tableItems, tmp, suffix) {
    let index = getIndexForItem(tableItems);
    tmp += createItemRowForDeleteOrder(item, suffix, index);
    tableItems.innerHTML += tmp;
    setTotalForDeleteItems(suffix);
}

function createItemRowForOrder(item, suffix, index) {
    let row = getRowForTableItems(index, item);
    row += "<td>" + "<button type='button' onclick=incrementItem(" +
        index + ',' + item.price + ',' + "'" + suffix + "'" +
        ")>+</button><span class= " + ITEM_COUNT_CL + " id=" + ITEM_COUNT + index + ">1</span><button type='button' onclick=decrementItem(" +
        index + ',' + item.price + ',' + "'" + suffix + "'" + ")>-</button>" + "</td>";
    row += "<td class=" + ITEM_PRICE_CL + " id=" + ITEM_PRICE + index + ">" + item.price + "</td>";
    row += "<td>" + "<button onclick= \"deleteItemFromTable("
        + index + ',' + "'" + suffix + "'" +
        ")\" type=\"button\">\n" +
        " Удалить" +
        "</button>" + "</td></tr>";

    return row;
}

function getRowForTableItems(index, item) {
    let row;
    row = "<tr id=" + ITEM + index + ">" + "<td class=" + ITEM_ARTICLE_CL + ">" + item.id + "</td>";
    row += "<td>" + item.name + "</td>";
    row += "<td>" + item.category.name + "</td>";
    row += "<td>" + item.type.name + "</td>";
    row += "<td>" + item.size + "</td>";
    return row;
}

function createItemRowForDeleteOrder(item, suffix, index) {
    let row = getRowForTableItems(index, item);
    row += "<td><span class= " + ITEM_COUNT_CL + " id=" + ITEM_COUNT + index + ">1</span></td>";
    row += "<td class=" + ITEM_PRICE_CL + " id=" + ITEM_PRICE + index + ">" + item.price + "</td></tr>";

    return row;
}