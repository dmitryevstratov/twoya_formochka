//URL
const URL_CREATE = "/discounts/create";
const URL_ORDERS = "/discounts";
const DATA_ORDERS = "data-discounts"
const URL_EDIT = "/discounts/edit";

//ID modal window
const MODAL_CREATE = "addDiscount";
const MODAL_DELETE = "deleteOrder";
const MODAL_EDIT = "editOrder";

//Load clients on client page

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