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

function addItemToTable(item, tableItems, tmp, suffix) {
    let index = getMaxIndex(tableItems.querySelectorAll(TAG_TR));
    index = Number(index) + Number(1);
    tmp += createItemRowForOrder(item, suffix, index);
    tableItems.innerHTML = tmp;
    setTotalForItems(suffix);
}

function addItemToTableUpdate(item, tableItems, tmp, suffix) {
    let index = getMaxIndex(tableItems.querySelectorAll(TAG_TR));
    index = Number(index) + Number(1);
    tmp += createItemRowForOrder(item, suffix, index);
    tableItems.innerHTML += tmp;
    setTotalForItems(suffix);
}

function createItemRowForOrder(item, suffix, index) {
    let row;
    row = "<tr id=" + ITEM + index + ">" + "<td class=" + ITEM_ARTICLE_CL + ">" + item.id + "</td>";
    row += "<td>" + item.name + "</td>";
    row += "<td>" + item.category.name + "</td>";
    row += "<td>" + item.type.name + "</td>";
    row += "<td>" + item.size + "</td>";
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